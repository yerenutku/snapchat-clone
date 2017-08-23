package com.headonelab.hacknbreaksnapchat.models;

public class MessageModel {

    private String key, fromWho, url;

    public MessageModel() {
    }

    public MessageModel(String key, String fromWho, String url) {
        this.key = key;
        this.fromWho = fromWho;
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFromWho() {
        return fromWho;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}