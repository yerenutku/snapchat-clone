package com.headonelab.hacknbreaksnapchat.models;

public class MessageModel {

    private String key, fromWho, imageName;

    public MessageModel() {
    }

    public MessageModel(String key, String fromWho, String imageName) {
        this.key = key;
        this.fromWho = fromWho;
        this.imageName = imageName;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}