package com.example.amalzoheir.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;

/**
 * Created by Amalzoheir on 12/22/2017.
 */

public class NewsInfoLoader extends AsyncTaskLoader<ArrayList<NewsInfo>> {
    private static String LOG_TAG=NewsInfoLoader.class.getName();
    private String mUrl;
    public NewsInfoLoader(Context context,String mUrl){
        super(context);
        this.mUrl=mUrl;
    }

    @Override
    protected void onForceLoad() {
        onForceLoad();
    }

    @Override
    public ArrayList<NewsInfo> loadInBackground() {
        if(mUrl==null) {
            return null;
        }
        ArrayList<NewsInfo> newsInfoArrayList=null;
        try{
            newsInfoArrayList=QueryUtils.fetchNewsInfo(mUrl);
        }catch (MalformedInputException e){
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return newsInfoArrayList;
    }
}
