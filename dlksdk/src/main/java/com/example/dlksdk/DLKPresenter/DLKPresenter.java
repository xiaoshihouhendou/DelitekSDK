package com.example.dlksdk.DLKPresenter;

public abstract class DLKPresenter {

    /**
     * 搜索区域
     */
    public abstract void SearchArea();


    /**
     * 根据区域id 搜索设备
     *
     * @param areaIds
     */
    public abstract void SearchDeviceByAreaId(int areaIds);


    /**
     * 控制场景 灯控
     *
     * @param areaId   区域id
     * @param presetId 控制场景id
     */
    public abstract void ControlPresetLight(int areaId, int presetId);


    /**
     * 查询场景 灯控
     *
     * @param areaId    区域id
     * @param presetIds 场景id 数组
     */
    public abstract void RequestPresetLightWithAreaId(int areaId, int[] presetIds);


    /**
     * 控制通道  灯控
     *
     * @param areaId   区域id
     * @param channels 通道id 和亮度值 数组
     */
    public abstract void ControlChannelLightWithAreaId(int areaId, int[] channels);


    /**
     * 查询通道 灯控
     *
     * @param areaId   区域id
     * @param channels 通道数组 id
     */
    public abstract void RequestChannelLightWithAreaId(int areaId, int[] channels);


    /**
     * 搜索区域  客房
     *
     * @param room 房间号
     */
    public abstract void SearchAreaHotelWithRoom(String room);


    /**
     * 根据区域 搜索设备 客房
     *
     * @param room    房间号
     * @param areaIds 区域id 数组  备注：当areaIds 为[]，时候为搜索客房所有区域的设备。
     */
    public abstract void SearchDeviceByAreaIdHotelWithRoom(String room, int[] areaIds);


    /**
     * 控制场景  客房
     *
     * @param room      房间号
     * @param areaIds   区域id
     * @param presetIds 场景id
     */
    public abstract void ControlPresetHotelWithRoom(String room, int areaIds, int presetIds);


    /**
     * 查询场景  客房
     *
     * @param room      房间号
     * @param areaIds   区域id
     * @param presetIds 场景id 数组
     */
    public abstract void ControlChannelLightWithAreaId(String room, int areaIds, int[] presetIds);


    /**
     * 控制通道  客房
     *
     * @param room     房间号
     * @param areaIds  区域id
     * @param channels 通道id 和亮度值 数组
     */
    public abstract void ControlChannelHotelWithRoom(String room, int areaIds, int[] channels);


    /**
     * 查询通道  客房
     *
     * @param room     房间号
     * @param areaIds  区域id
     * @param channels 通道id 数组
     */
    public abstract void RequestChannelHotelWithRoom(String room, int areaIds, int[] channels);


    /**
     * 控制空调 客房
     *
     * @param room    房间号
     * @param areaIds 区域id
     * @param airs    空调参数数组 [{"id":2,fan:"high",mode:"cold",setT:24},{"id":空调ID,fan:风速,mode:模式,setT:设置温度}]
     */
    public abstract void ControlAirInterface_HotelWithRoom(String room, int areaIds, int[] airs);


    /**
     * 查询空调 客房
     *
     * @param room    房间号
     * @param areaIds 区域id
     * @param airs    空调id 数组
     */
    public abstract void RequestAirInterface_HotelWithRoom(String room, int areaIds, int[] airs);


    /**
     * 控制窗帘
     *
     * @param room     房间号
     * @param areaIds  区域 id
     * @param curtains 数组，示例：[{"id":2,value:"open"},{"id":窗帘ID,value:状态}]
     *                 备注："value": "open"  //窗帘状态 [open,close,stop]
     */
    public abstract void ControlCurtainInterface_HotelWithRoom(String room, int areaIds, int[] curtains);


//    #pragma mark 灯光功能接口


    /**
     * 搜索区域  灯控
     */
    public abstract void SearchAreaLight();


    /**
     * @param areaIds 区域id
     *                备注：当areaIds 为[]，时候为搜索灯控所有区域的设备。
     */
    public abstract void SearchDeviceByAreaIdLightWithAreaIds(int[] areaIds);


    /**
     * 控制场景
     *
     * @param areIds
     * @param presetId 场景id  数组
     */
    public abstract void ControlPresetLightWithAreaId(int areIds, int presetId);


    /**
     * 查询场景
     *
     * @param areIds
     * @param presetId 场景id 数组
     */
    public abstract void RequestPresetLightWithAreaIdSuccess(int areIds, int[] presetId);

    /**
     * 控制通道
     *
     * @param areIds
     * @param channels 通道ID和亮度值数组，示例：[{"id":1,value:100},{"id":2,value:0},{"id":通道ID,value:通道亮度值}]
     */
    public abstract void ControlChannelLightWithAreaIdSuccess(int areIds, int[] channels);

    /**
     * 查询通道
     *
     * @param areIds
     * @param channels 通道id  数组
     */
    public abstract void RequestChannelInterfaceLightWithAreaId(int areIds, int[] channels);


    //客房功能


    /**
     * 搜索区域
     *
     * @param room 房间号
     *             AreaModel
     *             备注：当areaIds 为[]，时候为搜索客房所有区域的设备。
     */
    public abstract void SearchAreaInterfaceHotelWithRoom(String room);

    /**
     * 根据区域搜索设备
     *
     * @param room    房间号
     * @param areaIds 区域id
     *                [AreaModel]   注意：该接口返回 AreaModel的devices里面的设备只有id、type、name字段有效。
     */
    public abstract void SearchDeviceByAreaIdInterfaceHotelWithRoom(String room, int[] areaIds);

    /**
     * 控制场景
     * @param room
     * @param areaIds 区域id
     * @param presetIds 场景id
     *
     */
    public abstract void ControlPresetInterfaceHotelWithRoom(String room, int areaIds,int  presetIds);


    /**
     * 查询场景
     * @param room
     * @param areaIds 区域id
     * @param presetIds 场景 id 数组
     *                  PresetModel
     */
    public abstract void RequestPresetInterfaceHotelWithRoom(String room, int areaIds,int [] presetIds);


    /**
     *  控制通道
     * @param room
     * @param areaIds
     * @param channels 通道ID和亮度值数组，示例：[{"id":1,value:100},{"id":2,value:0},{"id":通道ID,value:通道亮度值}]
     */
    public abstract void ControlChannelInterfaceHotelWithRoom(String room, int areaIds,int []  channels);


    /**
     * 查询通道
     * @param room
     * @param areaIds
     * @param channels 通道id 数组
     * ChannelModel
     */
    public abstract void RequestChannelInterfaceHotelWithRoom(String room, int areaIds,int []  channels);


    /**
     * 控制空调
     * @param room
     * @param areaIds
     * @param airs (空调ID、风速、模式、设置温度) 数组，示例：[{"id":2,fan:"high",mode:"cold",setT:24},{"id":空调ID,fan:风速,mode:模式,setT:设置温度}]
     *             备注："fan": "high"//风速 [high,middle,low,off]   ；    "mode": "cold" //模式 [cold,hot,fan,off]   ；    "setT": 24 //[16-32]
     */
    public abstract void ControlAirInterfaceHotelWithRoom(String room, int areaIds,int [] airs);


    /**
     * 查询空调
     * @param room
     * @param areaIds
     * @param airs 空调id 数组
     *             AirModel
     */
    public abstract void RequestAirInterfaceHotelWithRoom(String room, int areaIds,int [] airs);


    /**
     *  控制窗帘
     * @param room
     * @param areaIds
     * @param curtains (窗帘ID、状态) 数组，示例：[{"id":2,value:"open"},{"id":窗帘ID,value:状态}]
     *                 备注："value": "open"  //窗帘状态 [open,close,stop]
     *
     */
    public abstract void ControlCurtainInterfaceHotelWithRoom(String room, int areaIds,int [] curtains);

}

