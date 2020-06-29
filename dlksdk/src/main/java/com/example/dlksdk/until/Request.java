package com.example.dlksdk.until;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.dlksdk.App;
import com.example.dlksdk.Content.Content;
import com.example.dlksdk.http.HttpUtils;
import com.example.dlksdk.http.callback.CommonCallback;
import com.example.dlksdk.http.entity.AllDevicesEntity;
import com.example.dlksdk.http.entity.BaseEntity;
import com.example.dlksdk.http.entity.LightDevicesByAreaEntity;
import com.example.dlksdk.http.entity.RoomDevicesByAreaEntity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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

    private AllDevicesEntity entity = new AllDevicesEntity();

    private Gson gson = new Gson();

    private int time = App.pass;



    public Map objectRoom;
    public Map objectLight;

    private Handler handler = new Handler() {


        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.i("----", "handleMessage: isControl=="+isControl);
            Log.i("----", "handleMessage: isControl=="+allData);
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
            entity.getRoom().setRoom("0");
            result(entity);
            return;
        }
        HttpUtils.doPost(Content.getUrl(Content.TYPE.SEARCH_ALL_AREA_DEVICES), getBoday(BaseData.getJson(Content.TYPE.SEARCH_LIGHT_DEVICES_AREA)
                ,objectLight), new CommonCallback<String>() {
            @Override
            protected void success(String result) {
                Log.i("---->>>>", "success: " + result);
                isLightBack = true;
                entity.setLight(gson.fromJson(result, LightDevicesByAreaEntity.class));
                if (isRoomBack) {
                    allData=true;
                    result(entity);
                }


            }

            @Override
            protected void failure(String reason) {

                reason(reason);
            }
        });

        HttpUtils.doPost(Content.getUrl(Content.TYPE.SEARCH_ALL_AREA_DEVICES), getBoday(BaseData.getJson(Content.TYPE.SEARCH_ROOM_AREA_DEVICES)
                ,objectRoom), new CommonCallback<String>() {
            @Override
            protected void success(String result) {
                Log.i("---->>>>", "success: " + result);
                isRoomBack = true;
                entity.setRoom(gson.fromJson(result, RoomDevicesByAreaEntity.class));
                if (isLightBack) {
                    allData=true;
                    result(entity);
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
        this.objectLight=light;
        this.objectRoom=room;
        request(App.pass);
    }

    protected abstract void result(String result);


    protected abstract void result(AllDevicesEntity result);

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


}
