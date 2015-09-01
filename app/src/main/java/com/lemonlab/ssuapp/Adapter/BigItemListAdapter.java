package com.lemonlab.ssuapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lemonlab.ssuapp.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by lk on 2015. 8. 4..
 */
public class BigItemListAdapter extends BaseAdapter {

    private ArrayList<String> m_List;
    private int depth;

    public BigItemListAdapter(ArrayList<String> m_List, int depth) {
        this.m_List = m_List;
        this.depth = depth;
    }

    public BigItemListAdapter(String[] array, int depth){
        for(int i=0; i<array.length; i++)
            m_List = new ArrayList<String>(Arrays.asList(array));
        this.depth = depth;
    }

    public int getDepth(){
        return depth;
    }

    @Override
    public String toString() {
        return ""+depth;
    }

    @Override
    public int getCount() {
        return m_List.size();
    }

    @Override
    public String getItem(int position) {
        return m_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_class_item, parent, false);

            TextView tv = (TextView) convertView.findViewById(R.id.tv_class_item);
            tv.setText(m_List.get(position));
        }else{
            TextView tv = (TextView) convertView.findViewById(R.id.tv_class_item);
            tv.setText(m_List.get(position));
        }

        return convertView;
    }

}
