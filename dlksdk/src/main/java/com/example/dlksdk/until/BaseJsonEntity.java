package com.example.dlksdk.until;

import com.example.dlksdk.Content.Content;
import com.example.dlksdk.http.entity.LightAreaEntity;
import com.example.dlksdk.http.entity.LightChannelEntity;
import com.example.dlksdk.http.entity.LightDevicesByAreaEntity;
import com.example.dlksdk.http.entity.LightPresetEntity;
import com.example.dlksdk.http.entity.RoomAirsEntity;
import com.example.dlksdk.http.entity.RoomAreaEntity;
import com.example.dlksdk.http.entity.RoomChannelEntity;
import com.example.dlksdk.http.entity.RoomDevicesByAreaEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseJsonEntity {

    public static Object getJson(Content.TYPE type, String json) {
        Object object = null;
        Gson gson = new Gson();
        switch (type) {
            case SEARCH_LIGHT_AREA:
                object = gson.fromJson(json, LightAreaEntity.class);
                break;
            case SEARCH_LIGHT_DEVICES_AREA:
                object = gson.fromJson(json, LightDevicesByAreaEntity.class);
                break;
            case SEARCH_LIGHT_CHANNEL:
                object = gson.fromJson(json, LightChannelEntity.class);
                break;
            case SEARCH_LIGHT_PRESET:
                object = gson.fromJson(json, LightPresetEntity.class);
                break;
            case SEARCH_ROOM_AIRS:
                object = gson.fromJson(json, RoomAirsEntity.class);
                break;
            case SEARCH_ROOM_AREA:
                object = gson.fromJson(json, RoomAreaEntity.class);
                break;
            case SEARCH_ROOM_AREA_DEVICES:
                object = gson.fromJson(json, RoomDevicesByAreaEntity.class);
                break;
            case SEARCH_ROOM_CHANNEL:
                object = gson.fromJson(json, RoomChannelEntity.class);
                break;
            case CONTROL_LIGHT_PRESET:
            case CONTROL_LIGHT_CHANNEL:
            case CONTROL_ROOM_AIRS:
            case CONTROL_ROOM_CHANNEL:
            case CONTROL_ROOM_CURTAIN:
            case CONTROL_ROOM_PRESET:
                object = gson.fromJson(json, ControlEntity.class);
                break;
        }
        return object;
    }
}
