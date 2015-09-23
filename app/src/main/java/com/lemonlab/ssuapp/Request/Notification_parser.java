package com.lemonlab.ssuapp.Request;

import android.util.Log;
import android.webkit.URLUtil;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.lemonlab.ssuapp.Model.Notification;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.transform.Source;

/**
 * Created by heechan on 15. 9. 23..
 */
public class Notification_parser {


    public ArrayList<Notification> getPaseResult(String param) {
        ArrayList<Notification> arrayList = new ArrayList<>();
        Log.i("Parse", "트라이 ");
        try {
            Document doc = Jsoup.parse(param);
            Elements lists = doc.select(".first-child");
            for (Element e:lists){
                Notification notification = new Notification();
                Elements urlE = e.select("a[href]");
                //String url = e.attr("href");
                String url = urlE.attr("href");
                String title = urlE.text();
                Elements dateE = e.select("span");
                String date = dateE.text();
                notification.setDate(date);
                notification.setTitle(title);
                notification.setUrl(url);
                arrayList.add(notification);
                Log.i("parse", notification.toString());
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("Parse", "널 ");
        return null;
    }
}
