package com.lemonlab.ssuapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.lemonlab.ssuapp.R;

/**
 * Created by lk on 2015. 9. 25..
 */
public class RecyclerHolder extends RecyclerView.ViewHolder {

    public RecycleHolder mRecycleHolder;

    static class RecycleHolder {

        public TextView mTitle;             // Recipe Name
        public NetworkImageView mImage;     // Cooking Thumbnail
        public Button mlikeButton;          // Like Button
    }

    public RecyclerHolder(View itemView) {
        super(itemView);
        setHolder(itemView);
    }

    private void setHolder(View itemView) {
        mRecycleHolder = new RecycleHolder();
        mRecycleHolder.mTitle = (TextView) itemView.findViewById(R.id.tv_recycle_title);
        mRecycleHolder.mImage = (NetworkImageView) itemView.findViewById(R.id.nv_recycle_image);
        mRecycleHolder.mlikeButton = (Button) itemView.findViewById(R.id.bt_recycle_like);
    }
}