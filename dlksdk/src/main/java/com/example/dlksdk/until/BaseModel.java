package com.example.dlksdk.until;

public class BaseModel {

    /**
     * code : 0
     * message : success
     * content : {"roomNum":"1111","breakfast":1,"breakfast_num":2,"unuse_breakfast_num":0}
     */

    private int code;
    private String message;
    private String content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
