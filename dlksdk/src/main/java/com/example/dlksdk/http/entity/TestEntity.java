package com.example.dlksdk.http.entity;

import com.example.dlksdk.until.TimeUntils;

public class TestEntity {

    /**
     * no : 1593178204445
     * time : 2020-06-26 21:30:04
     * func : request
     * business : light
     * type : area
     */

    private long no=System.currentTimeMillis();
    private String time= TimeUntils.TimeFormat();
    private String func="request";
    private String business="light";
    private String type="area";

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
