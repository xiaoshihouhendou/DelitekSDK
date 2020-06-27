package com.example.dlksdk.http.entity;


import com.example.dlksdk.http.parser.XcParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by 2 on 2018/12/26.
 */
@HttpResponse(parser = XcParser.class)
public class XcEnity {
    private int code;
    private String message;
    private String content;
    private String info;

    public XcEnity() {
    }

    public XcEnity(int code, String message, String content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public XcEnity(int code, String message, String content, String info) {
        this.code = code;
        this.message = message;
        this.content = content;
        this.info = info;
    }

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
