package com.example.dlksdk.until;

import com.example.dlksdk.Content.Content;

import org.json.JSONException;
import org.json.JSONObject;

public class BaseData {

    public static JSONObject getJson(Content.TYPE type) {
        JSONObject object = new JSONObject();
        try {
            object.put("no", System.currentTimeMillis());
            object.put("time", TimeUntils.TimeFormat());
        switch (type) {
            case SEARCH_LIGHT_AREA:
//            {"no":"1591591785","func":"request","type":"area","business":"light","time":"2020-06-08 12:49:45"}
                object.put("func", "request");
                object.put("business", "light");
                object.put("type", "area");
                break;
            case SEARCH_LIGHT_DEVICES_AREA:
//            {"no":"1591591785","func":"request","type":"device","business":"light","areaIds":[1,2],"time":"2020-06-08 12:49:45"}
                object.put("func", "request");
                object.put("business", "light");
                object.put("type", "device");
                break;
            case SEARCH_LIGHT_CHANNEL:
//            {"no":"1591591785","func":"request","business":"light","type":"channel","areaId":1,"channelIds":[1,2],"time":"2020-06-08 15:52:29"}
                object.put("func", "request");
                object.put("business", "light");
                object.put("type", "channel");
                break;
            case SEARCH_LIGHT_PRESET:
//            {"no":"1591605557","func":"request","business":"light","type":"preset","areaId":1,"presetIds":[101,102],"time":"2020-06-08 15:26:09"}
                object.put("func", "request");
                object.put("business", "light");
                object.put("type", "preset");
                break;
            case SEARCH_ROOM_AIRS:
//            {"no":"1591591785","func":"request","business":"hotel","room":"8888","type":"air","areaId":1,"airIds":[1,2],"time":"2020-06-08 15:52:29"}
                object.put("func", "request");
                object.put("business", "hotel");
                object.put("type", "air");
                break;
            case SEARCH_ROOM_AREA:
//            {"no":"1591591785","func":"request","type":"area","business":"hotel","room":"8888","time":"2020-06-08 12:49:45"}
                object.put("func", "request");
                object.put("business", "hotel");
                object.put("type", "area");
                break;
            case SEARCH_ROOM_AREA_DEVICES:
//            {"no":"1591591785","func":"request","type":"device","business":"hotel","room":"8888","areaIds":[1,2],"time":"2020-06-08 12:49:45"}
                object.put("func", "request");
                object.put("business", "hotel");
                object.put("type", "device");
                break;
            case SEARCH_ROOM_CHANNEL:
//            {"no":"1591591785","func":"request","business":"hotel","room":"8888","type":"channel","areaId":1,"channelIds":[1,2],"time":"2020-06-08 15:52:29"}
                object.put("func", "request");
                object.put("business", "hotel");
                object.put("type", "channel");
                break;
            case CONTROL_LIGHT_PRESET:
//            {"no":"1591605557","func":"control","business":"light","type":"preset","areaId":1,"presetId":101,"time":"2020-06-08 15:26:09"}
                object.put("func", "control");
                object.put("business", "light");
                object.put("type", "preset");
                break;
            case CONTROL_LIGHT_CHANNEL:
//            {"no":"1591591785","func":"control","business":"light","type":"channel","areaId":1,"datas":[{"id":1,"value":100},{"id":2,"value":100}],"time":"2020-06-08 15:50:51"}
                object.put("func", "control");
                object.put("business", "light");
                object.put("type", "channel");
                break;
            case CONTROL_ROOM_AIRS:
//            {"no":"1591591785","func":"control","business":"hotel","type":"air","room":"8888","areaId":1,"datas":[{"id":1,"fan":"high","mode":"cold","setT":24},{"id":2,"fan":"middle","mode":"fan","setT":26}],"time":"2020-06-08 15:52:29"}
                object.put("func", "control");
                object.put("business", "hotel");
                object.put("type", "air");
                break;
            case CONTROL_ROOM_CHANNEL:
//            {"no":"1591591785","func":"control","business":"hotel","room":"8888","type":"channel","areaId":1,"datas":[{"id":1,"value":100},{"id":2,"value":100}],"time":"2020-06-08 15:50:51"}
                object.put("func", "control");
                object.put("business", "hotel");
                object.put("type", "channel");
                break;
            case CONTROL_ROOM_CURTAIN:
//            {"no":"1591591785","func":"control","business":"hotel","type":"curtain","room":"8888","areaId":1,"datas":[{"id":1,"value":"open"},{"id":2,"value":"stop"}],"time":"2020-06-08 15:52:29"}
                object.put("func", "control");
                object.put("business", "hotel");
                object.put("type", "curtain");
                break;
            case CONTROL_ROOM_PRESET:
//            {"no":"1591605557","func":"control","business":"hotel","type":"preset","room":"8888","areaId":1,"presetId":1,"time":"2020-06-08 15:26:09"}
                object.put("func", "control");
                object.put("business", "hotel");
                object.put("type", "preset");
                break;
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
