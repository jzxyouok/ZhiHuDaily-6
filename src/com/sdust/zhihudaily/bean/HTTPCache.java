package com.sdust.zhihudaily.bean;

import com.google.gson.annotations.Expose;


public class HTTPCache {
    private long id;
    @Expose
    private String request;
    @Expose
    private String response;
    @Expose
    private long time;

    public HTTPCache() {
    }

    public HTTPCache(String request, String response, long time) {
        this.request = request;
        this.response = response;
        this.time = time;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
