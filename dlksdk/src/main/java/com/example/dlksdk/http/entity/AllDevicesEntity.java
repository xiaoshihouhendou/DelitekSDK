package com.example.dlksdk.http.entity;

import java.io.Serializable;

public class AllDevicesEntity implements Serializable {

    private LightDevicesByAreaEntity light;
    private RoomDevicesByAreaEntity room;

    public LightDevicesByAreaEntity getLight() {
        return light;
    }

    public void setLight(LightDevicesByAreaEntity light) {
        this.light = light;
    }

    public RoomDevicesByAreaEntity getRoom() {
        return room;
    }

    public void setRoom(RoomDevicesByAreaEntity room) {
        this.room = room;
    }
}
