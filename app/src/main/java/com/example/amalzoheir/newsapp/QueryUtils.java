package com.example.amalzoheir.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;

/**
 * Created by Amalzoheir on 12/18/2017.
 */

public class QueryUtils {
    public QueryUtils(){

    }
    public static ArrayList<NewsInfo> extractNewsInfo(String nUrl){
        ArrayList<NewsInfo> NewsInfosList=new ArrayList<>();
        try {
            JSONObject root=new JSONObject(nUrl);
            JSONObject response=root.getJSONObject("response");
            JSONArray results=response.getJSONArray("results");
            for(int i=0;i<results.length();i++){
                JSONObject newsData=results.getJSONObject(i);
                String titleOfArticle=newsData.getString("type");
                String nameOfSecction=newsData.getString("sectionName");
                String webTitle=newsData.getString("webTitle");
                String webUrl=newsData.getString("webUrl");
                if(!newsData.isNull("webPublicationDate")&&!newsData.isNull("authorName")){
                    String webPublicationDate=newsData.getString("webPublicationDate");
                    String authorName=newsData.getString("authorName");
                    NewsInfosList.add(new NewsInfo(titleOfArticle,nameOfSecction,webTitle,webUrl,authorName,webPublicationDate));
                }
                else {
                    NewsInfosList.add(new NewsInfo(titleOfArticle, nameOfSecction, webTitle, webUrl));
                }
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return NewsInfosList;
    }
    public static URL createUrl(String stringUrl) throws MalformedInputException{
        URL url=null;
        try{
            url=new URL(stringUrl);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }
    public static String makeHttpRequest(URL url) throws IOError,IOException{
        String jsonResponse="";
        if(url==null){
            return jsonResponse;
        }
        InputStream inpputstream=null;
        HttpURLConnection urlconnection=null;
        try{
            urlconnection=(HttpURLConnection)url.openConnection();
            urlconnection.setRequestMethod("GET");
            urlconnection.setReadTimeout(10000);
            urlconnection.setConnectTimeout(15000);
            urlconnection.connect();
            if(urlconnection.getResponseCode()==200){
                inpputstream=urlconnection.getInputStream();
                jsonResponse=readFromStream(inpputstream);
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(urlconnection!=null){
                urlconnection.disconnect();
            }
            if(inpputstream!=null){
                inpputstream.close();
            }
        }
        return jsonResponse;
    }
    public static String readFromStream(InputStream inputstream)throws IOException{
        StringBuilder output=new StringBuilder();
        try {
            if (inputstream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputstream, Charset.forName("utf-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }

            }
        }catch (IOException e){

            Log.e("inputquery","error when make file",e);
        }
        return output.toString();
    }
    public static ArrayList<NewsInfo> fetchNewsInfo(String requestUrl) throws MalformedURLException, MalformedInputException {
        URL url=createUrl(requestUrl);
        String jsonResponse=null;
        try{
            jsonResponse=makeHttpRequest(url);
        }catch (IOException e){
            Log.e("query", "Problem making the HTTP request.", e);
        }
        ArrayList<NewsInfo> newsInfoList=extractNewsInfo(jsonResponse);
        return newsInfoList;
    }
}
