package com.example.amalzoheir.newsapp;
import android.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.LoaderManager.LoaderCallbacks;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<NewsInfo>>{
    public static final String LOG_TAG = MainActivity.class.getName();
    public static final String API_REQUEST_URL ="http://content.guardianapis.com/search?q=movies&api-key=test&show-tags=contributor";
    private NewsInfoAdapter madapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView newsInfoListView=(ListView)findViewById(R.id.list);
        madapter=new NewsInfoAdapter(this,new ArrayList<NewsInfo>());
        newsInfoListView.setAdapter(madapter);
        LoaderManager loaderManager=getLoaderManager();
        loaderManager.initLoader(1,null,this);
    }

    @Override
    public android.content.Loader<ArrayList<NewsInfo>> onCreateLoader(int id, Bundle args) {
        return new NewsInfoLoader(this,API_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(android.content.Loader<ArrayList<NewsInfo>> loader, ArrayList<NewsInfo> newsInfo) {
        madapter.clear();
        if(newsInfo!=null&&!newsInfo.isEmpty()){
            madapter.addAll(newsInfo);
        }
    }


    @Override
    public void onLoaderReset(android.content.Loader<ArrayList<NewsInfo>> loader) {
        madapter.clear();
    }
}
