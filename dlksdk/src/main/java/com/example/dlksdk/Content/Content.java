package com.example.dlksdk.Content;

import com.example.dlksdk.BQSDK;

public class Content {



    public enum TYPE {


        SEARCH_ALL_AREA_DEVICES, //搜索 全部 设备

        SEARCH_LIGHT_AREA, //灯控区域搜索

        SEARCH_LIGHT_PRESET,//查询灯控场景  "areaId":1,"presetId":101

        SEARCH_LIGHT_CHANNEL,//查询灯控通道 "areaId":1,"channelIds":[1,2]

        SEARCH_LIGHT_DEVICES_AREA, //根据区域搜索设备  "areaIds":[1,2],

        CONTROL_LIGHT_PRESET, //控制场景 "areaId":1,"presetIds":[101,102],

        CONTROL_LIGHT_CHANNEL, //控制通道  "areaId":1,"datas":[{"id":1,"value":100},{"id":2,"value":100}]


        //客控

        CONTROL_ROOM_CHANNEL, //控制通道 "room":"8888","areaId":1,"channelIds":[1,2],

        CONTROL_ROOM_PRESET, //控制场景 "room":"8888","areaId":1,"presetId":1,

        CONTROL_ROOM_AIRS, //控制空调  "room":"8888","areaId":1,"datas":[{"id":1,"fan":"high","mode":"cold","setT":24}

        CONTROL_ROOM_CURTAIN, //控制窗帘   "room":"8888","areaId":1,"datas":[{"id":1,"value":"open"},{"id":2,"value":"stop"}]

        SEARCH_ROOM_AREA, //查询客控区域 "room":"8888"

        SEARCH_ROOM_AREA_DEVICES, //根据客控区域查询设备 "room":"8888","areaIds":[1,2]

        SEARCH_ROOM_CHANNEL, //查询客控通道  "room":"8888","areaId":1,"datas":[{"id":1,"value":100},

        SEARCH_ROOM_AIRS, //查询客控空调 "room":"8888","type":"air","areaId":1,"airIds":[1,2],


    }

    public static String getUrl(TYPE type) {
        String url = "";
        switch (type) {
            case SEARCH_LIGHT_AREA:
            case SEARCH_LIGHT_DEVICES_AREA:
            case SEARCH_LIGHT_CHANNEL:
            case SEARCH_LIGHT_PRESET:
            case SEARCH_ROOM_AIRS:
            case SEARCH_ROOM_AREA:
            case SEARCH_ROOM_AREA_DEVICES:
            case SEARCH_ROOM_CHANNEL:
            case SEARCH_ALL_AREA_DEVICES:
                url = "http://" + BQSDK.BASE_URL + ":50002/Dalitek/Api/Query";
                break;
            case CONTROL_LIGHT_PRESET:
            case CONTROL_LIGHT_CHANNEL:
            case CONTROL_ROOM_AIRS:
            case CONTROL_ROOM_CHANNEL:
            case CONTROL_ROOM_CURTAIN:
            case CONTROL_ROOM_PRESET:
                url = "http://" + BQSDK.BASE_URL + ":50001/Dalitek/Api/Control";
                break;
        }
        return url;
    }
}
