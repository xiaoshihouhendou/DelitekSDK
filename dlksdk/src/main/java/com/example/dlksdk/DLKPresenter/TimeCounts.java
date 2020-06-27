package com.example.dlksdk.DLKPresenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.dlksdk.App;
import com.example.dlksdk.Content.Content;
import com.example.dlksdk.http.HttpUtils;
import com.example.dlksdk.http.callback.CommonCallback;
import com.example.dlksdk.http.entity.AllDevicesEntity;
import com.example.dlksdk.http.entity.LightDevicesByAreaEntity;
import com.example.dlksdk.http.entity.RoomDevicesByAreaEntity;
import com.google.gson.Gson;


public abstract class TimeCounts {

    /**
     * 灯控是否返回
     */
    private boolean isLightBack = false;

    /**
     * 客控是否返回
     */
    private boolean isRoomBack = false;


    private String allData = "";

    private AllDevicesEntity entity = new AllDevicesEntity();

    private Gson gson = new Gson();

    private int time = App.pass;

    public static boolean isControl = false;


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

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                handler.postDelayed(this, time);
            }
        }, time);

    }



    private void all() {
        isLightBack = false;
        isRoomBack = false;
        Log.i("", "all: 请求发出");
        HttpUtils.doPost(Content.getUrl(Content.TYPE.SEARCH_ALL_AREA_DEVICES), "", new CommonCallback<String>() {
            @Override
            protected void success(String result) {
                Log.i("---->>>>", "success: " + result);
                isLightBack = true;
//                entity.setLight(gson.fromJson(result, LightDevicesByAreaEntity.class));
                if (isRoomBack) {
                    result(entity);
                }


            }

            @Override
            protected void failure(String reason) {

                reason(reason);
            }
        });

        HttpUtils.doPost(Content.getUrl(Content.TYPE.SEARCH_ALL_AREA_DEVICES), "", new CommonCallback<String>() {
            @Override
            protected void success(String result) {
                Log.i("---->>>>", "success: " + result);
                isRoomBack = true;
//                entity.setRoom(gson.fromJson(result, RoomDevicesByAreaEntity.class));
                if (isLightBack) {
                    result(entity);
                }
            }

            @Override
            protected void failure(String reason) {
                reason(reason);
            }
        });
    }

    abstract void result(AllDevicesEntity entity);

    abstract void reason(String reason);
}
