package com.example.amalzoheir.newsapp;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by Amalzoheir on 12/18/2017.
 */
public class NewsInfoAdapter extends ArrayAdapter<NewsInfo> {
    public NewsInfoAdapter(Activity context, ArrayList<NewsInfo> newsInfos){
        super(context,0,newsInfos);
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.newsinfo_list_item,parent,false);
        }
        NewsInfo currentNewsInfo=getItem(position);
        TextView titleOfArticleView=(TextView)listItemView.findViewById(R.id.title_of_article);
        titleOfArticleView.setText(currentNewsInfo.getTitleOfArticle());
        TextView nameOfSectionView=(TextView)listItemView.findViewById(R.id.name_of_section);
        nameOfSectionView.setText(currentNewsInfo.getNameOfSecction());
        return listItemView;
    }
}
