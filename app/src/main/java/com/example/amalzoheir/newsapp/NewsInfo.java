package com.example.amalzoheir.newsapp;

/**
 * Created by Amalzoheir on 12/18/2017.
 */

public class NewsInfo {
    private String titleOfArticle;
    private String nameOfSecction;
    public NewsInfo(String titleOfArticle,String nameOfSecction){
        this.titleOfArticle=titleOfArticle;
        this.nameOfSecction=nameOfSecction;
    }
    public String getTitleOfArticle(){
        return titleOfArticle;
    }
    public String getNameOfSecction(){
        return nameOfSecction;
    }
}
