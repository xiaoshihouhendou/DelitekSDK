package com.example.dlksdk.http.entity;

import java.io.Serializable;

public class AllDevicesEntity implements Serializable {

    private LightDevicesByAreaEntity light;
    private RoomDevicesByAreaEntity room;

    public LightDevicesByAreaEntity getLight() {
        if (light==null){
            light=new LightDevicesByAreaEntity();
        }
        return light;
    }

    public void setLight(LightDevicesByAreaEntity light) {
        this.light = light;
    }

    public RoomDevicesByAreaEntity getRoom() {
        if (room==null){
            room=new RoomDevicesByAreaEntity();
        }

        return room;
    }

    public void setRoom(RoomDevicesByAreaEntity room) {
        this.room = room;
    }
}
