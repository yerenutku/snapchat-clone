package com.headonelab.hacknbreaksnapchat.models;

public class MessageModel {

    private String fromWho, imageName;

    public MessageModel() {
    }

    public MessageModel(String fromWho, String imageName) {
        this.fromWho = fromWho;
        this.imageName = imageName;
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