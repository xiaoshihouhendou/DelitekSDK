package com.example.dlksdk.http.entity;

import com.example.dlksdk.until.TimeUntils;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    /**
     * no : 1591591785
     * func : request
     * type : area
     * business : light
     * time : 2020-06-08 12:49:45
     *
     * {"no":"1591591785","func":"request","type":"area","business":"light","time":"2020-06-08 12:49:45"}
     */

    private long no=System.currentTimeMillis();
    private String func="request";
    private String business="light";
    private String time= TimeUntils.TimeFormat();

    public BaseEntity(long no, String func, String business, String time) {
        this.no = no;
        this.func = func;
        this.business = business;
        this.time = time;
    }

    public BaseEntity() {

    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
