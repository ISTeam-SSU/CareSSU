package com.lemonlab.ssuapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.lemonlab.ssuapp.Model.Restaurant;
import com.lemonlab.ssuapp.R;

import java.util.ArrayList;

/**
 * Created by heechan on 15. 9. 25..
 */
public class RestaurantListAdapter extends BaseAdapter {
    ArrayList<Restaurant> arrayList;
    private Context mContext = null;
    private Activity activity;
    public RestaurantListAdapter(ArrayList<Restaurant> arrayListRestaurant){
        this.arrayList = arrayListRestaurant;

    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.restaurnat_list_row, parent, false);
        }
        else{

        }
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageview_food);
        imageView.setImageResource(R.drawable.book);

        Button btnLike = (Button)convertView.findViewById(R.id.btn_like);
        btnLike.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        });
        Button btnMenu = (Button)convertView.findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        });
        return convertView;
    }
}
