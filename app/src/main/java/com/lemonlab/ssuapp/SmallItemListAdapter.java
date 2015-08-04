package com.lemonlab.ssuapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lemonlab.ssuapp.Model.Timetable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by lk on 2015. 8. 5..
 */
public class SmallItemListAdapter extends BaseAdapter{

    private ArrayList<Timetable> m_List;
    private int depth;

    public SmallItemListAdapter(ArrayList<Timetable> m_List, int depth) {
        this.m_List = m_List;
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
    public Timetable getItem(int position) {
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
            convertView = inflater.inflate(R.layout.list_subject_item, parent, false);

            TextView tvsubject = (TextView) convertView.findViewById(R.id.list_table_row_subject);
            tvsubject.setText(m_List.get(position).getSubject());
            TextView tvteacher = (TextView) convertView.findViewById(R.id.list_table_row_teacher);
            tvteacher.setText(m_List.get(position).getTeacher());
            TextView tvgrade = (TextView) convertView.findViewById(R.id.list_table_row_grade);
            tvgrade.setText(m_List.get(position).getDivision()+"/"+m_List.get(position).getStudent()+"/"+m_List.get(position).getGrade()+"학점");
            TextView tvtime = (TextView) convertView.findViewById(R.id.list_table_row_time);
            tvtime.setText(m_List.get(position).getTime());
        }else{
            TextView tvsubject = (TextView) convertView.findViewById(R.id.list_table_row_subject);
            tvsubject.setText(m_List.get(position).getSubject());
            TextView tvteacher = (TextView) convertView.findViewById(R.id.list_table_row_teacher);
            tvteacher.setText(m_List.get(position).getTeacher());
            TextView tvgrade = (TextView) convertView.findViewById(R.id.list_table_row_grade);
            tvgrade.setText(m_List.get(position).getDivision()+"/"+m_List.get(position).getStudent()+"/"+m_List.get(position).getGrade()+"학점");
            TextView tvtime = (TextView) convertView.findViewById(R.id.list_table_row_time);
            tvtime.setText(m_List.get(position).getTime());
        }

        return convertView;
    }
}
