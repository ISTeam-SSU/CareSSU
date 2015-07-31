package com.lemonlab.ssuapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout v = (RelativeLayout) view.findViewById(R.id.timetable);
        Button bt = (Button) view.findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(),SelectClass.class);
                startActivity(intent);

            }
        });
        v.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        TableDraw mp = new TableDraw(view.getContext());
        v.addView(mp);
    }


}
