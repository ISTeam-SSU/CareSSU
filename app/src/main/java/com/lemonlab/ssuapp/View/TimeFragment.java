package com.lemonlab.ssuapp.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lemonlab.ssuapp.R;

/**
 * Created by lk on 2015. 7. 23..
 */
public class TimeFragment extends Fragment{
    TableDraw mp;
    private View capture;
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
        setHasOptionsMenu(true);
        RelativeLayout v = (RelativeLayout) view.findViewById(R.id.timetable);
        capture = view.findViewById(R.id.timetable_container);

//        Button bt = (Button) view.findViewById(R.id.button);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(view.getContext(),SelectClass.class);
//                startActivity(intent);
//
//            }
//        });
        v.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mp = new TableDraw(view.getContext());
        v.addView(mp);
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_time, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_addtime :
                Intent intent = new Intent(getActivity(), TimeAddActivity.class);
                //intent.putExtra("draw",mp);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
