package com.lemonlab.ssuapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lemonlab.ssuapp.R;

import java.util.ArrayList;

/**
 * Created by ISTeam_Open on 2015-08-26.
 */
public class LibraryCustomAdapter extends BaseAdapter {

    private ArrayList<String> titleList;
    private ArrayList<String> contextList;
    private Activity activity;

    public LibraryCustomAdapter(Activity activity, ArrayList<String> titleList, ArrayList<String> contextList){
        this.titleList = titleList;
        this.contextList = contextList;
        this.activity = activity;
    }

    private LayoutInflater inflater;

    public int getCount() {
        return titleList.size();
    }


    public Object getItem(int position) {
        return titleList.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_row, null);
        }
        TextView textView1 = (TextView)convertView.findViewById(R.id.textView1);
        textView1.setText(titleList.get(position));

        TextView textView2 = (TextView)convertView.findViewById(R.id.textView2);
        textView2.setText(contextList.get(position));
        return convertView;
    }
}
