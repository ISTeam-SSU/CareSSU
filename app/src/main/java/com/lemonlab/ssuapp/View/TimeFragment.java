package com.lemonlab.ssuapp.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lemonlab.ssuapp.R;

/**
 * Created by lk on 2015. 7. 23..
 */
public class TimeFragment extends Fragment {

    public TimeFragment(){
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.timefragment, container, false);
        //RelativeLayout timetable = (RelativeLayout)v.findViewById(R.id.timetable);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout v = (RelativeLayout) view.findViewById(R.id.timetable);
        v.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Timetable mp = new Timetable(view.getContext());
        v.addView(mp);
    }


}
