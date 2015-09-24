package com.lemonlab.ssuapp.View;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lemonlab.ssuapp.Adapter.BigItemListAdapter;
import com.lemonlab.ssuapp.Dao;
import com.lemonlab.ssuapp.Model.Timetable;
import com.lemonlab.ssuapp.R;
import com.lemonlab.ssuapp.Model.SsuSubject;
import com.lemonlab.ssuapp.Adapter.SmallItemListAdapter;
import com.lemonlab.ssuapp.Request.JSONArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lk on 2015. 8. 3..
 */
public class TimeAddActivity extends AppCompatActivity implements View.OnClickListener {

    private Timetable timetable;
    private SmallItemListAdapter m_Adapter3;
    Vibrator vibe;
    TempDraw td;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeadd);

        final SsuSubject s = new SsuSubject();

        final ListView listView = (ListView) findViewById(R.id.lv_timeadd_list);
        final BigItemListAdapter m_Adapter = new BigItemListAdapter(getResources().getStringArray(R.array.bigItem),1);
        final TextView titleText = (TextView) findViewById(R.id.tv_timeadd_title);

        final RelativeLayout v = (RelativeLayout) findViewById(R.id.timetable);

        vibe = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        listView.setAdapter(m_Adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            ArrayList<Timetable> arrayList;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                vibe.vibrate(15);
                Log.i("test", Integer.parseInt(String.valueOf(listView.getAdapter())) + "");
                switch (Integer.parseInt(String.valueOf(listView.getAdapter()))) {
                    case 1:
                        titleText.setText(m_Adapter.getItem(position));
                        BigItemListAdapter m_Adapter2 = new BigItemListAdapter(s.getList(m_Adapter.getItem(position)), 2);
                        listView.setAdapter(m_Adapter2);
                        break;
                    case 2:
                        String bigitem = (String) titleText.getText();
                        String smallitem = (String) listView.getAdapter().getItem(position);
                        titleText.setText(bigitem + " > " + smallitem);
                        Log.i("fffffff", smallitem + "" + s.getCode(bigitem, smallitem));
                        arrayList = getTimetable(s.getCode(bigitem, smallitem));
                        m_Adapter3 = new SmallItemListAdapter(arrayList, 3);
                        listView.setAdapter(m_Adapter3);
                        break;
                    case 3:
                        Log.i("test", arrayList.get(position).getSubject());
                        TableDraw mp = new TableDraw(getApplicationContext());
                        v.addView(mp);
                        timetable = arrayList.get(position);
                        td = new TempDraw(getApplicationContext(), timetable.getFtime_start(), timetable.getFtime_end(), timetable.getTime_count(), timetable.getTime_week());
                        v.addView(td);

                }
            }
        });
        TableDraw mp = new TableDraw(getApplicationContext());
        v.addView(mp);

        Button btDone = (Button) findViewById(R.id.bt_timeadd_done);
        btDone.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Dao database = new Dao(getApplicationContext());
        vibe.vibrate(15);
        timetable.setColor(td.getColor());
        Log.i("insert result" , database.insertTable(timetable)+"");
        finish();
    }

    public ArrayList<Timetable> getTimetable(int id){
        final ArrayList<Timetable> tablelist = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);

        HashMap<String, String> request = new HashMap<>();
        request.put("url", "http://lemonlab.co.kr/ssu/timetable.php?id="+id);
        request.put("model", Request.Method.GET+"");
        request.put("token", "");

        JSONArrayRequest timetableRequest = JSONArrayRequest.request(request, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++) {
                    try {
                        JSONObject data = response.getJSONObject(i);
                        Timetable table = new Timetable(
                                data.getInt("id"),
                                data.getString("division"),    //Division
                                data.getString("subject"),    //Subject
                                data.getString("teacher"),    //Teacher
                                data.getInt("grade"),       //Grade
                                data.getString("time"),    //Time String
                                data.getString("time_start"),    //Time_start
                                data.getString("time_end"),    //Time_end
                                data.getInt("time_count"),       //time_count
                                data.getString("time_week"),    //Time_week
                                data.getString("classroom"),    //Classroom
                                data.getString("student"),
                                "");  //Student
                        tablelist.add(table);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                m_Adapter3.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(timetableRequest);
        return tablelist;
    }
}
