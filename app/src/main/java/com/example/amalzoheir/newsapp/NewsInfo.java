package com.example.amalzoheir.newsapp;

/**
 * Created by Amalzoheir on 12/18/2017.
 */

public class NewsInfo {

    private String titleOfArticle;
    private String nameOfSecction;
    private String webTitle;
    private String webUrl;
    private String authorName;
    private String pDate;
    public NewsInfo(String titleOfArticle, String nameOfSecction, String webTitle, String webUrl,String authorName,String pDate) {
        this.titleOfArticle = titleOfArticle;
        this.nameOfSecction = nameOfSecction;
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.authorName=authorName;
        this.pDate = pDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getpDate() {
        return pDate;
    }

    public void setpDate(String pDate) {
        this.pDate = pDate;
    }


    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getTitleOfArticle() {
        return titleOfArticle;
    }

    public String getNameOfSecction() {
        return nameOfSecction;
    }
}
