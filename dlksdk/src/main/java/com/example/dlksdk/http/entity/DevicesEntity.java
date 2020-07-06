package com.example.dlksdk.http.entity;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

public class DevicesEntity {

    /**
     * id : 1
     * type : air
     * name : 空调1
     * state : {"fan":"low","mode":"fan","setT":25,"currentT":25}
     */
    private int areaId;
    private int id;
    private String type="";
    private String name="";
    private String room="";
    private StateBean state;

    public String getRoom() {
        return str(room);
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return str(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public static class StateBean {
        /**
         * fan : low
         * mode : fan
         * setT : 25
         * currentT : 25
         */


        private String fan="";
        private String mode="";
        @SerializedName("switch")
        private String onoff="";
        private int setT;
        private String value="";
        private int currentT;


        public String getSwitchs() {
            return str(onoff);
        }

        public void setSwitchs(String switchs) {
            this.onoff = switchs;
        }

        public String getValue() {
            return str(value);
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getFan() {
            return str(fan);
        }

        public void setFan(String fan) {
            this.fan = fan;
        }

        public String getMode() {
            return str(mode);
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public int getSetT() {
            return setT;
        }

        public void setSetT(int setT) {
            this.setT = setT;
        }

        public int getCurrentT() {
            return currentT;
        }

        public void setCurrentT(int currentT) {
            this.currentT = currentT;
        }
    }

    public static String str(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str;
    }
}
