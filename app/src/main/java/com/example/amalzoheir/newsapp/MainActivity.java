package com.example.amalzoheir.newsapp;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.content.Loader;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.LoaderManager.LoaderCallbacks;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<NewsInfo>>{
    public static final String LOG_TAG = MainActivity.class.getName();
    public static final String API_REQUEST_URL ="http://content.guardianapis.com/search?";
    private NewsInfoAdapter madapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView newsInfoListView=(ListView)findViewById(R.id.list);
        madapter=new NewsInfoAdapter(this,new ArrayList<NewsInfo>());
        newsInfoListView.setAdapter(madapter);
        newsInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                NewsInfo currentNewsInfo = madapter.getItem(position);
                Uri newsInfoUri = Uri.parse(currentNewsInfo.getWebUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsInfoUri);
                if(websiteIntent.resolveActivity(getPackageManager())!=null) {
                    startActivity(websiteIntent);
                }
                else{
                    Toast.makeText(getBaseContext(),"can't open url",Toast.LENGTH_SHORT).show();
                }
            }

    });

        LoaderManager loaderManager=getLoaderManager();
        loaderManager.initLoader(1,null,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings){
            Intent settingsIntent=new Intent(this,SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public android.content.Loader<ArrayList<NewsInfo>> onCreateLoader(int id, Bundle args) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String news= sharedPrefs.getString(getString(R.string.settings_story_key),getString(R.string.settings_story_default));
        Uri baseUri = Uri.parse(API_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q",news);
        uriBuilder.appendQueryParameter("api-key","test");
        uriBuilder.appendQueryParameter("show-tag","contributor");
        return new NewsInfoLoader(this,uriBuilder.toString());
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
