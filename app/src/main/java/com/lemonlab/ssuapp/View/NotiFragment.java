package com.lemonlab.ssuapp.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lemonlab.ssuapp.R;

import java.util.ArrayList;

/**
 * Created by lk on 2015. 7. 23..
 */
public class NotiFragment extends Fragment {
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    public NotiFragment() {
        super();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_notification, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.notification_listView);
        arrayList = new ArrayList<String>();

        arrayList.add("전체");
        arrayList.add("학사");
        arrayList.add("장학");
        arrayList.add("국제교류");
        arrayList.add("모집,채용");
        arrayList.add("교내행사");
        arrayList.add("교외행사");
        arrayList.add("봉사");

        adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1);

        for (int i = 0; i < arrayList.size(); i++) {
            adapter.add(arrayList.get(i));
        }

        listView.setAdapter(adapter);


    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            
        }
    };
}
