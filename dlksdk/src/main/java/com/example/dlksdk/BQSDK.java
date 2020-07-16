package com.example.dlksdk;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.example.dlksdk.Content.Content;
import com.example.dlksdk.http.entity.DevicesEntity;
import com.example.dlksdk.until.BaseData;
import com.example.dlksdk.until.BaseJsonEntity;
import com.example.dlksdk.until.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class BQSDK extends Request {
    public static BQSDK sdk;
    public static boolean isControl;
    public static String BASE_URL = "";
    public Context context;
    public Content.TYPE type;
    public JSONObject object;
    public Map objectRoom;
    public Map objectLight;
    public long passTime = 5 * 1000;
    public DataBackListener dataBackListener;
    public AllDataListener allDataListener;
    private int during = 5;

    private int ariTimeCount = 5;
    private int lightTimeCount = 3;

    private Handler handler = new Handler();

    private Runnable runnable;


    public BQSDK setAriTimeCount(int ariTimeCount) {
        this.ariTimeCount = ariTimeCount;
        return this;
    }

    public BQSDK setLightTimeCount(int lightTimeCount) {
        this.lightTimeCount = lightTimeCount;
        return this;
    }


    public static BQSDK Init(String url) {
        if (sdk == null) {
            sdk = new BQSDK();
        }
        BASE_URL = url;
        return sdk;
    }

    public static BQSDK Init() {
        if (sdk == null) {
            sdk = new BQSDK();
        }
        return sdk;
    }

    public BQSDK Search(Content.TYPE type) {
        this.type = type;
        object = BaseData.getJson(type);
        return this;
    }

    public BQSDK setPassTime(long passTime) {
        this.passTime = passTime;
        return this;
    }

    public BQSDK Control(Content.TYPE type) {
        switch (type) {
            case CONTROL_LIGHT_PRESET:
            case CONTROL_LIGHT_CHANNEL:
            case CONTROL_ROOM_CHANNEL:
                during = lightTimeCount;
                stop();
                break;
            case CONTROL_ROOM_AIRS:
                during = ariTimeCount;
                stop();
                break;
        }


        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
        isControl=true;
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                during--;
                if (during == 0) {
                    isControl = false;
                    BuildAll();
                }
            }
        };
        handler.postDelayed(runnable, 1000);
        this.type = type;
        object = BaseData.getJson(type);
        return this;
    }

    public BQSDK SearchAllDevices(Map<String, Object> room, Map<String, Object> light) {
        isControl = false;
        this.objectRoom = room;
        this.objectLight = light;
        return this;
    }

    public BQSDK addParams(String key, Object value) {
        try {
            object.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }


    public void stopAll() {
        stop();
//        isControl = true;
//        Request.allData = false;
    }

    public void Build() {
        switch (type) {
            case CONTROL_LIGHT_PRESET:
                for (int i = 0; i < list_devices.size(); i++) {
                    if (object.optInt("areaId") == (list_devices.get(i).getAreaId()) &&
                            TextUtils.isEmpty(list_devices.get(i).getRoom()) &&
                            object.optString("type").equals(list_devices.get(i).getType())) {
                        if (object.optInt("presetId") == (list_devices.get(i).getId())) {
                            list_devices.get(i).getState().setValue("1");
                        } else {
                            list_devices.get(i).getState().setValue("0");
                        }
                    }
                }
                break;
            case CONTROL_LIGHT_CHANNEL:
                JSONArray array = null;
                try {
//                    "type":"channel","areaId":"1","datas":[{"id":"1","value":"100"},{"id":"2","value":"100"}]}
//                    {"areaId":1,"id":1,"name":"一层","state":{"currentT":0,"setT":0,"value":"100"},
                    array = object.getJSONArray("datas");
                    for (int i = 0; i < list_devices.size(); i++) {
                        if (object.optInt("areaId") == (list_devices.get(i).getAreaId()) &&
                                TextUtils.isEmpty(list_devices.get(i).getRoom()) &&
                                object.optString("type").equals(list_devices.get(i).getType())
                        ) {
                            for (int j = 0; j < array.length(); j++) {
                                if (list_devices.get(i).getId() == array.getJSONObject(j).optInt("id")) {
                                    list_devices.get(i).getState().setValue(array.getJSONObject(j).optString("value"));
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case CONTROL_ROOM_CHANNEL:
//                "room":"8888","type":"channel","areaId":1,"datas":[{"id":1,"value":100},{"id":2,"value":100}]
                JSONArray array1 = null;
                try {
                    array1 = object.getJSONArray("datas");
                    for (int i = 0; i < list_devices.size(); i++) {
                        if (object.optInt("areaId") == (list_devices.get(i).getAreaId()) &&
                                object.optString("type").equals(list_devices.get(i).getType()) &&
                                object.optString("room").equals(list_devices.get(i).getRoom())
                        ) {
                            for (int j = 0; j < array1.length(); j++) {
                                if (list_devices.get(i).getId() == array1.getJSONObject(j).optInt("id")) {
                                    list_devices.get(i).getState().setValue(array1.getJSONObject(j).optString("value"));
                                    Log.i(">>>>", "Build: 更新本地 id==" + list_devices.get(i).getId());
                                    Log.i(">>>>", "Build: 更新本地 value==" + list_devices.get(i).getState().getValue());
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                during = lightTimeCount;
                break;
            case CONTROL_ROOM_AIRS:
//                offon 1
//                "type":"air","room":"8888","areaId":1,"datas":[{"id":1,"fan":"high","mode":"cold","setT":24},{"id":2,"fan":"middle","mode":"fan","setT":26}]
                JSONArray array2 = null;
                try {
                    array2 = object.getJSONArray("datas");
                    for (int i = 0; i < list_devices.size(); i++) {
                        if (object.optInt("areaId") == (list_devices.get(i).getAreaId()) &&
                                object.optString("type").equals(list_devices.get(i).getType()) &&
                                object.optString("room").equals(list_devices.get(i).getRoom())
                        ) {
                            for (int j = 0; j < array2.length(); j++) {
                                if (list_devices.get(i).getId() == array2.getJSONObject(j).optInt("id")) {
                                    list_devices.get(i).getState().setMode(array2.getJSONObject(j).optString("mode"));
                                    list_devices.get(i).getState().setSetT(array2.getJSONObject(j).optInt("setT"));
                                    list_devices.get(i).getState().setFan(array2.getJSONObject(j).optString("fan"));
                                    list_devices.get(i).getState().setSwitchs(array2.getJSONObject(j).optString("switch"));
                                    Log.i(">>>>", "Build: 更新本地 id==" + list_devices.get(i).getId());
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                during = ariTimeCount;
                break;
            case CONTROL_ROOM_CURTAIN:
//                "room":"8888","areaId":1,"datas":[{"id":1,"value":"open"},{"id":2,"value":"stop"}]
                JSONArray array3 = null;
                try {
                    array3 = object.getJSONArray("datas");
                    for (int i = 0; i < list_devices.size(); i++) {
                        if (object.optInt("areaId") == (list_devices.get(i).getAreaId()) &&
                                object.optString("type").equals(list_devices.get(i).getType()) &&
                                object.optString("room").equals(list_devices.get(i).getRoom())
                        ) {
                            for (int j = 0; j < array3.length(); j++) {
                                if (list_devices.get(i).getId() == array3.getJSONObject(j).optInt("id")) {
                                    list_devices.get(i).getState().setValue(array3.getJSONObject(j).optString("value"));
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case CONTROL_ROOM_PRESET:
//                "type":"preset","room":"8888","areaId":1,"presetId":1,
                for (int i = 0; i < list_devices.size(); i++) {
                    if (object.optInt("areaId") == (list_devices.get(i).getAreaId()) && object
                            .optInt("presetId") == (list_devices.get(i).getId()) &&
                            object.optString("type").equals(list_devices.get(i).getType()) &&
                            object.optString("room").equals(list_devices.get(i).getRoom())
                    ) {
                        if (object.optInt("presetId") == (list_devices.get(i).getId())) {
                            list_devices.get(i).getState().setValue("1");
                        } else {
                            list_devices.get(i).getState().setValue("0");
                        }
                    }
                }
                during = ariTimeCount;
                break;
        }
        request(object.toString(), type);
    }

    public void BuildAll() {
        requestAll(objectRoom, objectLight);
    }

    @Override
    protected void result(String result) {
        if (dataBackListener != null) {
            dataBackListener.result(type, BaseJsonEntity.getJson(type, result));
        }
    }


    @Override
    protected void result(List<DevicesEntity> result) {
        Log.i(">>>>", "result: 实际数据" + result.size());
        if (allDataListener != null) {
            allDataListener.resultDevices(result);
        }
    }


    @Override
    protected void reason(String reason) {
        if (dataBackListener != null) {
            dataBackListener.error(type, reason);
        }
        if (allDataListener != null) {
            allDataListener.error(type, reason);
        }
    }

    public void setDataBackListener(DataBackListener dataBackListener) {
        this.dataBackListener = dataBackListener;
    }

    public void setAllDataListener(AllDataListener controlListener) {
        this.allDataListener = controlListener;
    }


    public interface DataBackListener {

        void result(Content.TYPE type, Object o);

        void error(Content.TYPE type, String season);

    }

    public interface AllDataListener {


        void resultDevices(List<DevicesEntity> entity);


        void error(Content.TYPE type, String season);

    }

}
