package com.example.dlksdk.until;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.example.dlksdk.App;
import com.example.dlksdk.BQSDK;
import com.example.dlksdk.Content.Content;
import com.example.dlksdk.http.HttpUtils;
import com.example.dlksdk.http.callback.CommonCallback;
import com.example.dlksdk.http.entity.AllDevicesEntity;
import com.example.dlksdk.http.entity.DevicesEntity;
import com.example.dlksdk.http.entity.LightDevicesByAreaEntity;
import com.example.dlksdk.http.entity.RoomDevicesByAreaEntity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.dlksdk.BQSDK.isControl;

public abstract class Request {


    /**
     * 灯控是否返回
     */
    private boolean isLightBack = false;

    /**
     * 客控是否返回
     */
    private boolean isRoomBack = false;


    public static boolean allData = false;

    public static boolean change = false;

    private AllDevicesEntity entity = new AllDevicesEntity();
    public static List<DevicesEntity> list_devices = new ArrayList<>();
    private List<DevicesEntity> list_devices_changes = new ArrayList<>();
    private List<DevicesEntity> list_devices_room = new ArrayList<>();
    private List<DevicesEntity> list_devices_light = new ArrayList<>();

    private String data = "";
    private Gson gson = new Gson();

    private int time = App.pass;


    public Map objectRoom;
    public Map objectLight;

    private Handler handler = new Handler() {


        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (!isControl) {
                all();
            }


        }
    };

    public void request(int times) {
        this.time = times;
        handler.sendEmptyMessage(0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, time);
                handler.sendEmptyMessage(0);
            }
        }, time);

    }


    private void all() {
        isLightBack = false;
        isRoomBack = false;
        if (objectLight == null) {
            result(list_devices_changes);
            return;
        }
        HttpUtils.doPost(Content.getUrl(Content.TYPE.SEARCH_ALL_AREA_DEVICES), getBoday(BaseData.getJson(Content.TYPE.SEARCH_LIGHT_DEVICES_AREA)
                , objectLight), new CommonCallback<String>() {
            @Override
            protected void success(String result) {
                Log.i("---->>>>", "success: " + result);
                isLightBack = true;
                list_devices_light.clear();
                LightDevicesByAreaEntity ent = gson.fromJson(result, LightDevicesByAreaEntity.class);
                if (ent.getDatas().size() != 0) {
                    for (int i = 0; i < ent.getDatas().size(); i++) {
                        if (ent.getDatas().get(i).getDevices().size() != 0) {
                            for (int j = 0; j < ent.getDatas().get(i).getDevices().size(); j++) {
                                DevicesEntity entity = new DevicesEntity();
                                entity.setAreaId(ent.getDatas().get(i).getId());
                                entity.setType(ent.getDatas().get(i).getDevices().get(j).getType());
                                entity.setName(ent.getDatas().get(i).getName());
                                entity.setId(ent.getDatas().get(i).getDevices().get(j).getId());
                                DevicesEntity.StateBean bean = new DevicesEntity.StateBean();
                                bean.setValue(ent.getDatas().get(i).getDevices().get(j).getState().getValue());
                                entity.setState(bean);
                                list_devices_light.add(entity);
                            }
                        }
                    }
                }
                if (isRoomBack) {
                    allData = true;
                    if (list_devices.size() == 0) {
                        list_devices.addAll(list_devices_light);
                        list_devices.addAll(list_devices_room);
                        list_devices_room.clear();
                        list_devices_light.clear();
                        result(list_devices);
                    } else {

                        list_devices_changes.clear();
                        for (int i = 0; i < list_devices.size(); i++) {
                            for (int j = 0; j < list_devices_light.size(); j++) {
                                if (list_devices_light.get(j).getAreaId() == list_devices.get(i).getAreaId() &&
                                        list_devices_light.get(j).getId() == list_devices.get(i).getId() &&
                                        list_devices_light.get(j).getType().equals(list_devices.get(i).getType()) &&
                                        TextUtils.isEmpty(list_devices.get(i).getRoom()) &&
                                        !list_devices_light.get(j).getState().getValue().equals(list_devices.get(i).getState().getValue())
                                ) {
                                    list_devices_changes.add(list_devices_light.get(j));
                                    Log.i("----", "success: //////////-----------------------------------room");
                                    Log.i("----", "success: //////////---" + JSON.toJSONString(list_devices_light.get(j)));
                                    Log.i("----", "success: //////////---" + JSON.toJSONString(list_devices.get(i)));
                                }
                            }
                            for (int j = 0; j < list_devices_room.size(); j++) {
                                if (list_devices_room.get(j).getAreaId() == list_devices.get(i).getAreaId() &&
                                        list_devices_room.get(j).getId() == list_devices.get(i).getId() &&
                                        list_devices_room.get(j).getType().equals(list_devices.get(i).getType()) &&
                                        list_devices_room.get(j).getRoom().equals(list_devices.get(i).getRoom())) {
                                    if (
                                            !list_devices_room.get(j).getState().getValue().equals(list_devices.get(i).getState().getValue()) ||
                                                    !list_devices_room.get(j).getState().getMode().equals(list_devices.get(i).getState().getMode()) ||
                                                    !list_devices_room.get(j).getState().getSwitchs().equals(list_devices.get(i).getState().getSwitchs()) ||
                                                    list_devices_room.get(j).getState().getSetT() != (list_devices.get(i).getState().getSetT())
                                    ) {

                                        list_devices_changes.add(list_devices_room.get(j));
                                    }
                                }
                            }
                        }
                        list_devices.clear();
                        list_devices.addAll(list_devices_light);
                        list_devices.addAll(list_devices_room);
                        list_devices_room.clear();
                        list_devices_light.clear();
                        Log.i(">>>>", "result: 实际数据list_devices_changes=" + list_devices_changes.size());
                        if (list_devices_changes.size() > 0) {
                            result(list_devices_changes);
                        }
                    }
                }
            }

            @Override
            protected void failure(String reason) {

                reason(reason);
            }
        });

        HttpUtils.doPost(Content.getUrl(Content.TYPE.SEARCH_ALL_AREA_DEVICES), getBoday(BaseData.getJson(Content.TYPE.SEARCH_ROOM_AREA_DEVICES)
                , objectRoom), new CommonCallback<String>() {
            @Override
            protected void success(String result) {
                Log.i("---->>>>room", "success: " + result);
                isRoomBack = true;
                list_devices_room.clear();
                RoomDevicesByAreaEntity ent = gson.fromJson(result, RoomDevicesByAreaEntity.class);
                if (ent.getDatas().size() != 0) {
                    for (int i = 0; i < ent.getDatas().size(); i++) {
                        if (ent.getDatas().get(i).getDevices().size() != 0) {
                            for (int j = 0; j < ent.getDatas().get(i).getDevices().size(); j++) {
                                DevicesEntity entity = new DevicesEntity();
                                entity.setAreaId(ent.getDatas().get(i).getId());
                                entity.setType(ent.getDatas().get(i).getDevices().get(j).getType());
                                entity.setName(ent.getDatas().get(i).getName());
                                entity.setRoom(ent.getRoom());
                                DevicesEntity.StateBean bean = new DevicesEntity.StateBean();
                                bean.setValue(ent.getDatas().get(i).getDevices().get(j).getState().getValue());
                                bean.setSwitchs(ent.getDatas().get(i).getDevices().get(j).getState().getSwitchs());
                                bean.setSetT(ent.getDatas().get(i).getDevices().get(j).getState().getSetT());
                                entity.setId(ent.getDatas().get(i).getDevices().get(j).getId());
                                bean.setMode(ent.getDatas().get(i).getDevices().get(j).getState().getMode());
                                bean.setFan((ent.getDatas().get(i).getDevices().get(j).getState().getFan()));
                                bean.setCurrentT((ent.getDatas().get(i).getDevices().get(j).getState().getCurrentT()));
                                entity.setState(bean);
                                list_devices_room.add(entity);
                            }
                        }
                    }
                }
                if (isLightBack) {
                    allData = true;
                    if (list_devices.size() == 0) {
                        list_devices.addAll(list_devices_light);
                        list_devices.addAll(list_devices_room);
                        list_devices_room.clear();
                        list_devices_light.clear();
                        result(list_devices);
                    } else {
                        list_devices_changes.clear();
                        for (int i = 0; i < list_devices.size(); i++) {
                            for (int j = 0; j < list_devices_light.size(); j++) {
                                if (list_devices_light.get(j).getAreaId() == list_devices.get(i).getAreaId() &&
                                        list_devices_light.get(j).getId() == list_devices.get(i).getId() &&
                                        TextUtils.isEmpty(list_devices.get(i).getRoom()) &&
                                        list_devices_light.get(j).getType().equals(list_devices.get(i).getType()) &&
                                        !list_devices_light.get(j).getState().getValue().equals(list_devices.get(i).getState().getValue())
                                ) {
                                    Log.i("----", "success: //////////-----------------------------------room");
                                    Log.i("----", "success: //////////---" + JSON.toJSONString(list_devices_light.get(j)));
                                    Log.i("----", "success: //////////---" + JSON.toJSONString(list_devices.get(i)));
                                    list_devices_changes.add(list_devices_light.get(j));
                                }
                            }
                            for (int j = 0; j < list_devices_room.size(); j++) {
                                if (list_devices_room.get(j).getAreaId() == list_devices.get(i).getAreaId() &&
                                        list_devices_room.get(j).getId() == list_devices.get(i).getId() &&
                                        list_devices_room.get(j).getType().equals(list_devices.get(i).getType())&&
                                        list_devices_room.get(j).getRoom().equals(list_devices.get(i).getRoom())
                                ) {
                                    if (!list_devices_room.get(j).getState().getValue().equals(list_devices.get(i).getState().getValue()) ||
                                            !list_devices_room.get(j).getState().getMode().equals(list_devices.get(i).getState().getMode()) ||
                                            !list_devices_room.get(j).getState().getSwitchs().equals(list_devices.get(i).getState().getSwitchs()) ||
                                            list_devices_room.get(j).getState().getSetT() != (list_devices.get(i).getState().getSetT())) {

                                        list_devices_changes.add(list_devices_room.get(j));
                                    }
                                }
                            }
                        }
                        list_devices.clear();
                        list_devices.addAll(list_devices_light);
                        list_devices.addAll(list_devices_room);
                        list_devices_room.clear();
                        list_devices_light.clear();
                        Log.i(">>>>", "result: 实际数据 list_devices_changes=" + list_devices_changes.size());
                        if (list_devices_changes.size() > 0) {
                            result(list_devices_changes);
                        }
                    }
                }
            }

            @Override
            protected void failure(String reason) {
                reason(reason);
            }
        });
    }

    public void request(String body, Content.TYPE type) {
        Log.i(">>>>", "request: >>>>" + body);
        HttpUtils.doPost(Content.getUrl(type), body, new CommonCallback<String>() {
            @Override
            protected void success(String result) {
                Log.i("---->>>>", "success: " + result);
                result(result);

            }

            @Override
            protected void failure(String reason) {
                reason(reason);
            }
        });
    }


    /**
     * 接口通了  在优化
     */
    public void requestAll(Map<String, Object> room, Map<String, Object> light) {
        this.objectLight = light;
        this.objectRoom = room;
        request(App.pass);
    }

    protected abstract void result(String result);


//    protected abstract void result(AllDevicesEntity result);

    protected abstract void result(List<DevicesEntity> result);

    protected abstract void reason(String reason);


    public String getBoday(JSONObject o, Map<String, Object> map) {
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                o.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return o.toString();
    }

    public Request request;

    public Request getRequest() {
        if (request == null) {
//            request=new Re
        }
        return request;
    }

    public void setList_devices(List<DevicesEntity> list_devices) {
        this.list_devices = list_devices;
    }

    public void addList() {

    }
}
