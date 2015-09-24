package com.lemonlab.ssuapp.View;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.lemonlab.ssuapp.Adapter.RestaurantListAdapter;
import com.lemonlab.ssuapp.Model.Restaurant;
import com.lemonlab.ssuapp.R;

import java.util.ArrayList;

public class RestaurantFragment extends Fragment {
    private ListView listView = null;
    private RestaurantListAdapter restaurantListAdapter = null;

    public static RestaurantFragment newInstance() {
        RestaurantFragment fragment = new RestaurantFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public RestaurantFragment() {
        super();
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_restaurant, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Restaurant> arrayList = new ArrayList<Restaurant>();

        listView = (ListView)view.findViewById(R.id.restaurant_listview);

        for(int i = 0 ; i < 10 ; i++){

        }
    }


}
