package com.lemonlab.ssuapp.Model;

/**
 * Created by heechan on 15. 9. 23..
 */
public class Notification {
    private String title;
    private String url;
    private String date;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
