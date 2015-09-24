package com.lemonlab.ssuapp.View;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.lemonlab.ssuapp.Adapter.RestaurantListAdapter;
import com.lemonlab.ssuapp.AppController;
import com.lemonlab.ssuapp.Model.Restaurant;
import com.lemonlab.ssuapp.R;
import com.lemonlab.ssuapp.Request.JSONArrayRequest;
import com.lemonlab.ssuapp.Request.JsonObjectRequest;
import com.lemonlab.ssuapp.Request.LibraryRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
        return inflater.inflate(R.layout.activity_food, container, false);

    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Restaurant> arrayList = new ArrayList<Restaurant>();

        listView = (ListView)view.findViewById(R.id.restaurant_listview);

        for(int i = 0 ; i < 10 ; i++){

        }
        RequestQueue queue = Volley.newRequestQueue(getActivity());


        Random random = new Random();

        HashMap<String, String> request1 = new HashMap<>();
        request1.put("url", "http://lemonlab.co.kr/ssu/php/ssuapp/restaurant/"+random.nextInt(1)+1);
        request1.put("model", Request.Method.GET + "");

        JsonObjectRequest otherFoodRequest = new JsonObjectRequest(request1, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("json", response.toString());

                String imgurl = null;
                try {
                    imgurl = response.get("url").toString();

                    ImageLoader mImageLoader = AppController.getInstance().getImageLoader();
                    NetworkImageView mImage;
                    mImage = (NetworkImageView) view.findViewById(R.id.nv_food_intro);
                    mImage.setImageUrl(imgurl, mImageLoader);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



        HashMap<String, String> request = new HashMap<>();
        request.put("url", "http://lemonlab.co.kr/ssu/php/ssuapp/user/getRestaurant/"+"5604174b08918164224926"+"/50/0");
        request.put("model", Request.Method.GET + "");
        request.put("token", "");


        JSONArrayRequest libraryRequest = JSONArrayRequest.request(request, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.i("json", response.toString());
                try {
                    JSONObject object;
                    object = response.getJSONObject(0);
                    Log.i("First", object.get("item").toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley1111", error.toString());
                Toast.makeText(getActivity(), "네트워크에 연결할 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(otherFoodRequest);
        queue.add(libraryRequest);
    }
}
