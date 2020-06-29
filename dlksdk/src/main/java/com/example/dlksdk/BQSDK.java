package com.example.dlksdk;

import android.content.Context;
import android.os.Handler;

import com.example.dlksdk.Content.Content;
import com.example.dlksdk.http.entity.AllDevicesEntity;
import com.example.dlksdk.until.BaseData;
import com.example.dlksdk.until.BaseGosnEntity;
import com.example.dlksdk.until.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class BQSDK extends Request {
    public Context context;


    public static BQSDK sdk;

    public Content.TYPE type;

    public JSONObject object;

    public Map objectRoom;
    public Map objectLight;

    public long passTime = 5 * 1000;

    private int during = 5;


    public static boolean isControl;

    public static String BASE_URL = "";

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
        during = 5;
        isControl = true;
        if (Request.allData) {//如果正在自动请求
            Request.allData = false;
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(this, 1000);
                    during--;
                    if (during == 0) {
                        isControl = false;
                    }
                }
            }, 1000);


        }
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

    public BQSDK addParams(Map<String, Object> hash) {
        try {
            for (Map.Entry<String, Object> entry : hash.entrySet()) {
                object.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void stopAll() {
        isControl = true;
        Request.allData = false;
    }

    public void Build() {
        request(object.toString(), type);
    }

    public void BuildAll() {
        requestAll(objectRoom, objectLight);
    }

    @Override
    protected void result(String result) {
        if (dataBackListener != null) {
            dataBackListener.result(type, BaseGosnEntity.getJson(type, result));
        }
    }

    @Override
    protected void result(AllDevicesEntity result) {
        if (allDataListener != null) {
            allDataListener.result(result);
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


    public DataBackListener dataBackListener;

    public void setDataBackListener(DataBackListener dataBackListener) {
        this.dataBackListener = dataBackListener;
    }


    public interface DataBackListener {

        void result(Content.TYPE type, Object o);

        void error(Content.TYPE type, String season);

    }

    public AllDataListener allDataListener;


    public void setAllDataListener(AllDataListener controlListener) {
        this.allDataListener = controlListener;
    }

    public interface AllDataListener {

        void result(AllDevicesEntity entity);

        void error(Content.TYPE type, String season);

    }
}
