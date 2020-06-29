package com.example.dlksdk.http;


import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.http.body.StringBody;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by 2 on 2018/12/23.
 */

public class HttpUtils {

    public static void doGet(String url, Callback.CommonCallback<?> callback) {
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, callback);
    }

    public static void doPost(String url, String body, Callback.CommonCallback<?> callback) {
        RequestParams params = new RequestParams(url);
        try {
            StringBody body1 = new StringBody(body, "utf-8");
            body1.setContentType("application/text");
            params.setRequestBody(body1);
            Log.i(">>>>>", "doPost: " + body);
            x.http().post(params, callback);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public static void doPut(String url, String body, Callback.CommonCallback<?> callback) {
        RequestParams params = new RequestParams(url);
        params.setBodyContent(body);
        x.http().request(HttpMethod.PUT, params, callback);
    }

    public static void doDelete(String url, Callback.CommonCallback<?> callback) {
        RequestParams params = new RequestParams(url);
        x.http().request(HttpMethod.DELETE, params, callback);
    }

    public static void updateFile(String url, String path, Callback.CommonCallback<?> callback) {
        RequestParams requestParams = new RequestParams(url);
        requestParams.setMultipart(true);
        requestParams.setAsJsonContent(true);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("file", new File(path)));//文件
        MultipartBody body = new MultipartBody(list, "UTF-8");
        requestParams.setRequestBody(body);
        x.http().post(requestParams, callback);
    }

    public static void updateFile(String url, File path, Callback.CommonCallback<?> callback) {
        RequestParams requestParams = new RequestParams(url);
        requestParams.setMultipart(true);
        requestParams.setAsJsonContent(false);
        requestParams.addBodyParameter("file", path);
//        List<KeyValue> list = new ArrayList<>();
//        list.add(new KeyValue("file",path));//文件
//        MultipartBody body = new MultipartBody(list, "UTF-8");
//        requestParams.setRequestBody(body);
        x.http().post(requestParams, callback);
    }

    public static void doPostWithHeader(String url, String body, HashMap<String, String> header, Callback.CommonCallback<?> callback) {
        RequestParams params = new RequestParams(url);
        params.setBodyContent(body);
        for (String key : header.keySet()) {
            params.addHeader(key, header.get(key));
        }
        x.http().post(params, callback);
    }
}
