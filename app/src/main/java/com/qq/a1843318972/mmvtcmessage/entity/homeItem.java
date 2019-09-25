package com.qq.a1843318972.mmvtcmessage.entity;

public class homeItem {
    private int image;
    private String names;
    private int namesId;

    public homeItem(int image, String names, int namesId) {
        this.image = image;
        this.names = names;
        this.namesId = namesId;
    }

    public homeItem(String names, int namesId) {
        this.names = names;
        this.namesId = namesId;
    }

    public int getImage() {
        return image;
    }

    public String getNames() {
        return names;
    }

    public int getNamesId() {
        return namesId;
    }

    @Override
    public String toString() {
        return "homeItem{" +
                "image=" + image +
                ", names='" + names + '\'' +
                ", namesId=" + namesId +
                '}';
    }
}
