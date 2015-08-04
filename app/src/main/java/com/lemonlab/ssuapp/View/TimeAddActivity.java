package com.lemonlab.ssuapp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lemonlab.ssuapp.BigItemListAdapter;
import com.lemonlab.ssuapp.DaoTable;
import com.lemonlab.ssuapp.R;
import com.lemonlab.ssuapp.Model.SsuSubject;
import com.lemonlab.ssuapp.SmallItemListAdapter;

/**
 * Created by lk on 2015. 8. 3..
 */
public class TimeAddActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeadd);

        final SsuSubject s = new SsuSubject();
        DaoTable t = new DaoTable(TimeAddActivity.this);

        final ListView listView = (ListView) findViewById(R.id.lv_timeadd_list);
        final BigItemListAdapter m_Adapter = new BigItemListAdapter(getResources().getStringArray(R.array.bigItem),1);
        final TextView titleText = (TextView) findViewById(R.id.tv_timeadd_title);

        listView.setAdapter(m_Adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("test",Integer.parseInt(String.valueOf(listView.getAdapter()))+"");
                switch(Integer.parseInt(String.valueOf(listView.getAdapter()))) {
                    case 1:
                        titleText.setText(m_Adapter.getItem(position));
                        BigItemListAdapter m_Adapter2 = new BigItemListAdapter(s.getList(m_Adapter.getItem(position)),2);
                        listView.setAdapter(m_Adapter2);
                        break;
                    case 2:
                        String bigitem = (String) titleText.getText();
                        String smallitem = (String)listView.getAdapter().getItem(position);
                        titleText.setText(bigitem + " > " + smallitem);
                        Log.i("fffffff", smallitem + "" + s.getCode(bigitem, smallitem));
                        DaoTable daoTable = new DaoTable(getApplicationContext());
                        SmallItemListAdapter m_Adapter3 = new SmallItemListAdapter(daoTable.getTimetable(s.getCode(bigitem, smallitem)),3);
                        listView.setAdapter(m_Adapter3);
                        break;
                    case 3:

                }
            }
        });
        RelativeLayout v = (RelativeLayout) findViewById(R.id.timetable);
        TableDraw mp = new TableDraw(getApplicationContext());
        v.addView(mp);
    }
}
