package com.lemonlab.ssuapp.Request;

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

    private static String SERVER_URL = "http://m.ssu.ac.kr/html/themes/m/html/notice_univ_list.jsp?sCategory=";

    private static String getURL4Noti(String param){
        String myURL = new String();
        if(param.equals("전체")){
            myURL = "http://m.ssu.ac.kr/html/themes/m/html/notice_univ_list.jsp";
        }
        else{
            myURL = SERVER_URL + param;
        }
        return myURL;
    }
    public ArrayList<Notification> getPaseResult(String param) {
        ArrayList<Notification> arrayList = new ArrayList<Notification>();
        try {
            Document doc = Jsoup.connect(getURL4Noti(param)).get();
            Elements lists = doc.select(".first-child");
            for (Element e:lists){
                Notification notification = new Notification();
                String url = e.attr("href");
                String title = e.tagName("a").text();
                String date = e.tagName("span").text();
                notification.setDate(date);
                notification.setTitle(title);
                notification.setUrl(url);
                arrayList.add(notification);
            }
            return arrayList;
        } catch (Exception e) {

        }
        return null;
    }
}
