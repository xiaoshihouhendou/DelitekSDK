package com.example.dlksdk.http;

import com.example.dlksdk.http.parser.XcParser;

import org.xutils.http.annotation.HttpResponse;

@HttpResponse(parser = XcParser.class)
public class CommonEntity {
    private String content;

    public CommonEntity() {
    }

    public CommonEntity(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommonEntity{" +
                "content='" + content + '\'' +
                '}';
    }
}
