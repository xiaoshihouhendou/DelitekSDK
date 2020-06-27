package com.example.dlksdk.until;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class RequestUtils {

    public static RequestUtils instance;
    public PostRequest<BaseModel> okgo;

    private String TAG="----";

    public static RequestUtils getInstance() {
        if (instance == null) {
            instance = new RequestUtils();
        }
        return instance;
    }

    public void initRequestGet(String url, final CallBackListener listener) {
        OkGo.<BaseModel>get(url)
                .tag(this)
                .cacheMode(CacheMode.NO_CACHE).execute(new Callback<BaseModel>() {
            @Override
            public void onStart(Request<BaseModel, ? extends Request> request) {
                Log.i(TAG, "onStart: "+request.getUrl());
            }

            @Override
            public void onSuccess(Response<BaseModel> response) {
                BaseModel model = response.body();
                Log.i(TAG, "onError: " + model.getCode());
                if (model.getCode() == 0) {
                    if (listener != null) {
                        listener.callBack(response.body().getContent());
                    }
                } else {
                    listener.callBackError(model.getMessage());
                }

            }

            @Override
            public void onCacheSuccess(Response<BaseModel> response) {

            }

            @Override
            public void onError(Response<BaseModel> response) {
                Log.i(TAG, "onError: "+response.getException().getMessage());
                if (listener != null) {
                    listener.callBack("请检查网络");
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void uploadProgress(Progress progress) {

            }

            @Override
            public void downloadProgress(Progress progress) {

            }

            @Override
            public BaseModel convertResponse(okhttp3.Response response) throws Throwable {
                BaseModel model = new BaseModel();
                Log.i(TAG, "convertResponse: "+response.body().string());
                JSONObject object = new JSONObject(response.body().string());
                model.setCode(object.optInt("code"));
                model.setMessage(object.optString("message"));
                model.setContent(object.optString("content"));
                return model;
            }
        });
//        return okgo;
    }


    public RequestUtils initRequestPost(String url) {
        okgo = OkGo.<BaseModel>post(url)
                .tag(this)
                .cacheMode(CacheMode.NO_CACHE);
        return this;
    }

    public RequestUtils addParams(String key, String value) {
        okgo.params(key, value);
        return this;
    }
    public RequestUtils addJson(String value) {
        okgo.upJson( value);
        return this;
    }
    public RequestUtils addFileParams(String key, File value) {
        List<File> list = new ArrayList<>();
        list.add(value);
        okgo.addFileParams(key, list);
        return this;
    }

    public RequestUtils addHeaders(String key, String value) {
        okgo.headers(key, value);
        return this;
    }

    public RequestUtils addHeaders(String value) {
        okgo.upJson(value);
        return this;
    }

    public void request(final CallBackListener listener) {
        okgo.execute(new Callback<BaseModel>() {
            @Override
            public void onStart(Request<BaseModel, ? extends Request> request) {
                Log.i(TAG, "onStart: " + request.getUrl());
            }

            @Override
            public void onSuccess(Response<BaseModel> response) {
                BaseModel model = response.body();
                Log.i(TAG, "onError: " + model.getCode());
                if (model.getCode() == 0) {
                    if (listener != null) {
                        listener.callBack(response.body().getContent());
                    }
                } else {
                    listener.callBackError(model.getMessage());
                }

            }

            @Override
            public void onCacheSuccess(Response<BaseModel> response) {
                Log.i(TAG, "onCacheSuccess: ");
            }

            @Override
            public void onError(Response<BaseModel> response) {
                Log.i(TAG, "onError: "+response.getException().getMessage());
                if (listener != null) {
                    listener.callBack("请求异常");
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void uploadProgress(Progress progress) {

            }

            @Override
            public void downloadProgress(Progress progress) {

            }

            @Override
            public BaseModel convertResponse(okhttp3.Response response) throws Throwable {
                BaseModel model = new BaseModel();
                Log.i(TAG, "convertResponse: "+response.body().string());
                JSONObject object = new JSONObject(response.body().string());
                model.setCode(object.optInt("code"));
                model.setMessage(object.optString("message"));
                model.setContent(object.optString("content"));
                return model;
            }
        });
    }
}
