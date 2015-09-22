package com.lemonlab.ssuapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lemonlab.ssuapp.Model.Notification;
import com.lemonlab.ssuapp.R;

import java.util.ArrayList;

/**
 * Created by lk on 2015. 9. 23..
 */
public class NotiAdapter extends BaseAdapter {

    private ArrayList<Notification> m_List;
    private LayoutInflater inflater;
    private Activity activity;

    public NotiAdapter(Activity activity, ArrayList<Notification> list){
        this.m_List = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return m_List.size();
    }

    @Override
    public Object getItem(int i) {
        return m_List.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null){
            view = inflater.inflate(R.layout.list_noti_row2, null);
        }
        TextView textView1 = (TextView)view.findViewById(R.id.tv_noti_title);
        textView1.setText(m_List.get(i).getTitle());

        TextView textView2 = (TextView)view.findViewById(R.id.tv_noti_date);
        textView2.setText(m_List.get(i).getDate());

        return view;
    }
}
