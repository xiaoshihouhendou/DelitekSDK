package com.example.dlksdk.http.entity;

public class ResultEnrity {


    /**
     * areaId : 1
     * result : ok
     * no : 1591605557
     * func : control
     * type : preset
     * business : light
     * time : 2020-06-26 21:05:52
     */

    private String result;
    private String no;
    private String func;
    private String type;
    private String business;
    private String time;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
