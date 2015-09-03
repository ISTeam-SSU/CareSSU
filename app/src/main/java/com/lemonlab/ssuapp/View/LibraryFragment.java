package com.lemonlab.ssuapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lemonlab.ssuapp.Adapter.LibraryCustomAdapter;
import com.lemonlab.ssuapp.Request.LibraryRequest;
import com.lemonlab.ssuapp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by lk on 2015. 7. 23..
 */
public class LibraryFragment extends Fragment {

    private ListView listView;
    private Intent intent;
    private ArrayList<String> titleList = new ArrayList<>();
    private ArrayList<String> contextList = new ArrayList<>();
    private LibraryCustomAdapter listAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    final String[] name = new String[]{"제 1열람실", "제 2열람실", "제 3열람실", "제 4열람실", "제 5열람실", "대학원열람실", "박사과정열람실", "제 6열람실",""};

    public LibraryFragment(){
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.library, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listView1);
        listAdapter = new LibraryCustomAdapter(getActivity(), titleList, contextList);
        listView.setAdapter(listAdapter);
        initList();
        intent = new Intent(getActivity(), ReadingRoomActivity.class); //intent 생성
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color. blue); //refresh cycle 색 변경

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setEnabled(true);
                        swipeRefreshLayout.setRefreshing(true);
                        initList();
                    }
                });
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position < 8) {
                    intent.putExtra("name", name[position]);
                    intent.putExtra("Url", "http://203.253.28.47/seat/roomview5.asp?room_no=" + (position + 1));
                    startActivity(intent);
                }
                else
                    Toast.makeText(getActivity(), "ㅇㅅㅇ;;", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initList() {
        titleList.clear();
        contextList.clear(); //기존 데이터들 삭제
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        LibraryRequest libraryRequest = new LibraryRequest(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                swipeRefreshLayout.setRefreshing(false);
                Document doc = Jsoup.parse(response);
                Elements rows = doc.select("table");
                Elements tr = rows.get(1).select("tr");
                for (int i = 2; i < 10; i++) {
                    Elements td = tr.get(i).select("td");
                    Elements font1 = td.get(2).select("font");
                    Elements font2 = td.get(3).select("font");
                    String[] temp1 = font1.get(0).toString().split(";");
                    String[] temp2 = font2.get(0).toString().split(";");
                    String[] num1 = temp1[1].toString().split("</font>");
                    String[] num2 = temp2[1].toString().split("</font>");
                    contextList.add("(" + num2[0].toString() + " / " + num1[0].toString() + ")");
                }
                titleList.add("제 1열람실");
                titleList.add("제 2열람실");
                titleList.add("제 3열람실");
                titleList.add("제 4열람실");
                titleList.add("제 5열람실");
                titleList.add("대학원열람실");
                titleList.add("박사과정열람실");
                titleList.add("제 6열람실");
                titleList.add("");
                contextList.add("");
                listAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley1111", error.toString());
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), "네트워크에 연결할 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(libraryRequest);
    }
}
