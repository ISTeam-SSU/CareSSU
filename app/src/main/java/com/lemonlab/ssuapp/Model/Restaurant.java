package com.lemonlab.ssuapp.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by heechan on 15. 9. 23..
 */
public class Restaurant {
    String title;
    Drawable image;
    boolean isLiked;

    Restaurant(String title, Drawable image, boolean isLiked){
        this.title = title;
        this.image = image;
        this.isLiked = isLiked;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getImage() {
        return image;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }
}
