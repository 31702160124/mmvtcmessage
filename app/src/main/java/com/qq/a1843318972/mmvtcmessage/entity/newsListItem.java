package com.qq.a1843318972.mmvtcmessage.entity;

public class newsListItem {
    private String date;
    private String titleNames;
    private String newsUrl;

    public newsListItem(String date, String titleNames, String newsUrl) {
        this.date = date;
        this.titleNames = titleNames;
        this.newsUrl = newsUrl;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitleNames(String titleNames) {
        this.titleNames = titleNames;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public newsListItem(String date, String titleNames) {
        this.date = date;
        this.titleNames = titleNames;
    }

    public String getDate() {
        return date;
    }

    public String getTitleNames() {
        return titleNames;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    @Override
    public String toString() {
        return "newsListItem{" +
                "date=" + date +
                ", titleNames='" + titleNames + '\'' +
                ", newsUrl='" + newsUrl + '\'' +
                '}';
    }
}
