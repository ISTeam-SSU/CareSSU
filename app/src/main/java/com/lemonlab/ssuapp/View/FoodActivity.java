package com.lemonlab.ssuapp.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lemonlab.ssuapp.Adapter.EndlessRecyclerOnScrollListener;
import com.lemonlab.ssuapp.Adapter.RecycleAdapter;
import com.lemonlab.ssuapp.AppController;
import com.lemonlab.ssuapp.Model.Food;
import com.lemonlab.ssuapp.R;
import com.lemonlab.ssuapp.Request.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lk on 2015. 9. 25..
 */
public class FoodActivity extends AppCompatActivity {

    private List<Food> list = new ArrayList<>();
    private ProgressDialog progressDialog;
    private View view;
    private RecyclerView mRecyclerView;                                 // Recipe Recycle View
    private RecycleAdapter mAdapter = new RecycleAdapter(list, this);   // Recycle View Adapter(Recipe list, Content)
    private LinearLayoutManager mLinearLayoutManager;
    private int count = 0;
    private EndlessRecyclerOnScrollListener mScrollListener;
    private ArrayList<Integer> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        Intent intent;
        intent = getIntent();
        arrayList = intent.getIntegerArrayListExtra("Data");
        Log.i("data", arrayList.toString());
        initListView();


    }

    private void initListView() {
        visibleprogress();

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        //mLikeBtn = (Button) findViewById(R.id.bt_recycle_like);
        //mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);

        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        loadRecipeList();
        mScrollListener = new EndlessRecyclerOnScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                Log.i("onLoadMore", current_page + "");

                if(count < arrayList.size()) {
                    visibleprogress();
                    loadRecipeList();
                }
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    private void visibleprogress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
    }

    private void hideprograssDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    private void loadRecipeList() {


        for(int i=0; i<3; i++) {
            HashMap<String, String> request = new HashMap<>();
            request.put("model", Request.Method.GET + "");
            request.put("url", "http://lemonlab.co.kr/ssu/php/ssuapp/restaurant/" + arrayList.get(count));
            Log.i("url", request.get("url"));
            count++;
            Log.i("count", count + "");
            JsonObjectRequest recipeRequest = new JsonObjectRequest(request, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    String imgUrl = "";
                    String wasLiked = "";


                    Food food = null;
                    try {
                        food = new Food(response.get("title").toString(), response.get("menu").toString(), response.get("url").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // Recipe Title, Recipe id, Recipe Thumbnail img URL, Like id
                    list.add(food);


                    mAdapter.notifyDataSetChanged();
                    hideprograssDialog();                                                      // Hide PrograssDialog at the end of the recipe loaded
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("volley", error.toString());
                    hideprograssDialog();
                }
            });
            AppController.getInstance().addToRequestQueue(recipeRequest);
        }
    }
}
