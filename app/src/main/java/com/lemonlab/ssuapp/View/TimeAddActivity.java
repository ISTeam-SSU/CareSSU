package com.lemonlab.ssuapp.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lemonlab.ssuapp.BigItemListAdapter;
import com.lemonlab.ssuapp.Dao;
import com.lemonlab.ssuapp.DaoTable;
import com.lemonlab.ssuapp.Model.Timetable;
import com.lemonlab.ssuapp.R;
import com.lemonlab.ssuapp.Model.SsuSubject;
import com.lemonlab.ssuapp.SmallItemListAdapter;

import java.util.ArrayList;

/**
 * Created by lk on 2015. 8. 3..
 */
public class TimeAddActivity extends AppCompatActivity implements View.OnClickListener {

    private Timetable timetable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeadd);

        final SsuSubject s = new SsuSubject();
        DaoTable t = new DaoTable(TimeAddActivity.this);

        final ListView listView = (ListView) findViewById(R.id.lv_timeadd_list);
        final BigItemListAdapter m_Adapter = new BigItemListAdapter(getResources().getStringArray(R.array.bigItem),1);
        final TextView titleText = (TextView) findViewById(R.id.tv_timeadd_title);

        final RelativeLayout v = (RelativeLayout) findViewById(R.id.timetable);

        listView.setAdapter(m_Adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            ArrayList<Timetable> arrayList;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
                        DaoTable daoTable = new DaoTable(getApplicationContext());
                        arrayList = daoTable.getTimetable(s.getCode(bigitem, smallitem));
                        SmallItemListAdapter m_Adapter3 = new SmallItemListAdapter(arrayList, 3);
                        listView.setAdapter(m_Adapter3);
                        break;
                    case 3:
                        Log.i("test", arrayList.get(position).getSubject());
                        TableDraw mp = new TableDraw(getApplicationContext());
                        v.addView(mp);
                        timetable = arrayList.get(position);
                        TempDraw td = new TempDraw(getApplicationContext(), timetable.getFtime_start(), timetable.getFtime_end(), timetable.getTime_count(), timetable.getTime_week());
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
        Log.i("insert result" , database.insertTable(timetable)+"");
        finish();
    }
}
