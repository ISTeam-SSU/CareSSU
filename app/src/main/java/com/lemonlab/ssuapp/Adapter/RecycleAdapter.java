package com.lemonlab.ssuapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.lemonlab.ssuapp.AppController;
import com.lemonlab.ssuapp.Model.Food;
import com.lemonlab.ssuapp.R;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lk on 2015. 9. 25..
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecyclerHolder> {

    private ImageLoader mImageLoader;
    private List<Food> list;
    private Context context;

    private String token;
    private String userid;

    public RecycleAdapter(List<Food> item, Context context) {
        mImageLoader = AppController.getInstance().getImageLoader();
        this.list = item;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_row, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, final int position) {
        setItem(holder, position);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), RecipeActivity.class);
//                intent.putExtra("id",list.get(position).getItemId()+"");
//                intent.putExtra("title", list.get(position).getTitle());
//                intent.putExtra("titleThumbnail", list.get(position).getImageUrl());
//                view.getContext().startActivity(intent);
//            }
//        });

        //TODO 메뉴 상세 페이지
    }

    @Override
    public int getItemViewType(int position) { return position; }

    private void setItem(final RecyclerHolder holder, int position) {
//
        final Food food = list.get(position);
//
        holder.mRecycleHolder.mTitle.setText(food.getTitle());
        holder.mRecycleHolder.mImage.setImageUrl(food.getImageUrl(), mImageLoader);
//        if(recipe.getWasLike().equals("false"))                                                 // if wasLike
//            holder.mRecycleHolder.mlikeButton.setBackground(context.getResources().getDrawable(R.drawable.ic_icon_customer));                    // set Like Button wasLike
//        holder.mRecycleHolder.mlikeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int recipeid = recipe.getItemId();
//                if (recipe.getWasLike().equals("false")) {                                       // was not Like item
//                    HashMap<String, String> request = new HashMap<>();
//                    request.put("model", Request.Method.POST+"");
//                    request.put("url", AppSetting.recipeUrl + "/" + recipeid + "/likes");
//                    request.put("token", token);
//
//                    JsonObjectRequest recipeRequest = new JsonObjectRequest(request, new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            Log.i("like", "Success");
//                            holder.mRecycleHolder.mlikeButton.setBackground(context.getResources().getDrawable(R.drawable.ic_icon_like));
//                            recipe.setWasLike("true");                // save Like id
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.e("volley", error.toString());
//                        }
//                    });
//                    AppController.getInstance().addToRequestQueue(recipeRequest);
//                } else {
//                    HashMap<String, String> request = new HashMap<>();
//                    request.put("model", Request.Method.DELETE+"");
//                    request.put("url", AppSetting.recipeUrl + "/" + recipeid + "/likes");
//                    request.put("token", token);
//
//                    JsonArrayRequest recipeRequest = JsonArrayRequest.createJsonRequestToken(request, new Response.Listener<JSONArray>() {
//                        @Override
//                        public void onResponse(JSONArray response) {
//                            Log.i("Cancel like", "Success");
//                            holder.mRecycleHolder.mlikeButton.setBackground(context.getResources().getDrawable(R.drawable.ic_icon_customer));
//                            recipe.setWasLike("");                                          // delete Like id
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.e("volley", error.toString());
//                        }
//                    });
//                    AppController.getInstance().addToRequestQueue(recipeRequest);
//                }
//            }
//        });

    }
}