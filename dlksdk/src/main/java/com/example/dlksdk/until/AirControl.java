package com.example.dlksdk.until;

public class AirControl {

    /**
     * id : 1
     * fan : high
     * mode : cold
     * setT : 24
     */

    private int id;
    private String fan;
    private String mode;
    private int setT;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFan() {
        return fan;
    }

    public void setFan(String fan) {
        this.fan = fan;
    }

    public String getMode() {
        return mode;
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
}
