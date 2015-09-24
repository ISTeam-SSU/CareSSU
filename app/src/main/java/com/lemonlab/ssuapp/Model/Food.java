package com.lemonlab.ssuapp.Model;

/**
 * Created by lk on 2015. 9. 25..
 */
public class Food {

    private String title;       // Recipe Title
    private int id;             // Recipe Id
    private String imageUrl;    // Recipe Thumbnail URL
    private String wasLike;     // Like Id
    private String menu;

    public Food(String title, String menu, String imageUrl) {
        this.title = title;
        this.menu = menu;
        //this.id = Integer.parseInt(id);
        this.imageUrl = imageUrl;
        this.wasLike = wasLike;
    }

    public String getTitle() { return title; }

    public int getItemId() { return id; }

    public String getImageUrl() { return imageUrl; }

    public String getWasLike(){ return wasLike; }

    public void setWasLike(String id){ this.wasLike = id; }

    @Override
    public String toString() { return id+""; }
}
