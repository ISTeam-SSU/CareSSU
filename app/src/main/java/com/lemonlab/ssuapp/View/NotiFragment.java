package com.lemonlab.ssuapp.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lemonlab.ssuapp.Adapter.NotiAdapter;
import com.lemonlab.ssuapp.Model.Notification;
import com.lemonlab.ssuapp.R;
import com.lemonlab.ssuapp.Request.NotificationRequest;
import com.lemonlab.ssuapp.Request.Notification_parser;

import java.util.ArrayList;

/**
 * Created by lk on 2015. 7. 23..
 */
public class NotiFragment extends Fragment {
    ListView listView;
    ArrayList<String> arrayList;
    ArrayList<Notification> arrayList2;
    ArrayAdapter<String> adapter;
    NotiAdapter adapter2;
    int depth = 0;

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
        arrayList.add("모집·채용");
        arrayList.add("교내행사");
        arrayList.add("교외행사");
        arrayList.add("봉사");

        adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1);
        //adapter2 = new NotiAdapter(getActivity(),arrayList2);
        for (int i = 0; i < arrayList.size(); i++) {
            adapter.add(arrayList.get(i));
        }

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (depth == 0) {

                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    NotificationRequest noti = new NotificationRequest(getURL4Noti(arrayList.get(i)),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Notification_parser myParser = new Notification_parser();
                            arrayList2 = myParser.getPaseResult(response);
                            adapter2 = new NotiAdapter(getActivity(),arrayList2);
                            listView.setAdapter(adapter2);
                            depth = 1;
                        }
                    }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("volley1111", error.toString());
                        Toast.makeText(getActivity(), "네트워크에 연결할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(noti);
                    //adapter2 = new ArrayAdapter<Notification>(view.getContext(), android.R.layout.simple_list_item_2);

                } else if (depth == 1) {
                    //TODO 웹뷰 기능 여기다가 추가해주삼.
                }
            }
        });
    }

    private  String SERVER_URL = "http://m.ssu.ac.kr/html/themes/m/html/notice_univ_list.jsp?sCategory=";

    private  String getURL4Noti(String param){
        String myURL = new String();
        if(param.equals("전체")){
            myURL = "http://m.ssu.ac.kr/html/themes/m/html/notice_univ_list.jsp";
        }
        else{
            myURL = SERVER_URL + param;
        }
        return myURL;
    }
}
