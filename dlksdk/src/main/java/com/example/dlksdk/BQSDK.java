package com.example.dlksdk;

import com.alibaba.fastjson.JSON;
import com.example.dlksdk.Content.Content;
import com.example.dlksdk.http.entity.AllDevicesEntity;
import com.example.dlksdk.http.entity.TestEntity;
import com.example.dlksdk.until.BaseData;
import com.example.dlksdk.until.BaseGosnEntity;
import com.example.dlksdk.until.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class BQSDK extends Request {

    public static BQSDK sdk;

    public Content.TYPE type;

    public JSONObject object;

    public long passTime = 5 * 1000;


    public BQSDK setPassTime(long passTime) {
        this.passTime = passTime;
        return this;
    }

    public static String BASE_URL = "";

    public static BQSDK getInit(String url) {
        if (sdk == null) {
            sdk = new BQSDK();
        }
        BASE_URL = url;
        return sdk;
    }

    public static BQSDK getInit() {
        if (sdk == null) {
            sdk = new BQSDK();
        }
        return sdk;
    }


    public BQSDK SearchLight(Content.TYPE type) {
        this.type = type;
        object = BaseData.getJson(type);
        return this;
    }

    public BQSDK ControlLight(Content.TYPE type) {
        this.type = type;
        object = BaseData.getJson(type);
        return this;
    }


    public BQSDK SearchAllDevices(Content.TYPE type) {
        this.type = type;
        object = BaseData.getJson(type);
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


    public void Build() {



        TestEntity testEntity = new TestEntity();

        request( JSON.toJSONString(testEntity), type);


    }

    public void BuildAll() {
        TestEntity testEntity = new TestEntity();
//        JSON.toJSONString(testEntity)
        requestAll();
    }

    @Override
    protected void result(String result) {
        if (searchListener != null) {
            searchListener.result(type, BaseGosnEntity.getJson(type, result));
        }
    }

    @Override
    protected void result(AllDevicesEntity result) {

    }

    @Override
    protected void reason(String reason) {
        if (searchListener != null) {
            searchListener.error(type, reason);
        }
    }


    public SearchListener searchListener;

    public void setSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
    }

    public interface SearchListener {

        void result(Content.TYPE type, Object o);

        void error(Content.TYPE type, String season);

    }

    public ControlListener controlListener;


    public void setControlListener(ControlListener controlListener) {
        this.controlListener = controlListener;
    }

    public interface ControlListener {

        void result(Content.TYPE type, AllDevicesEntity entity);

        void error(Content.TYPE type, String season);

    }
}
