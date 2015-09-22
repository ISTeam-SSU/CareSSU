package com.lemonlab.ssuapp.Request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by lk on 2015. 9. 23..
 */
public class NotificationRequest   extends Request<String> {

    private Response.Listener<String> listener;

    public NotificationRequest(String url, Response.Listener<String> successListener, Response.ErrorListener errorListener){
        super(Request.Method.GET, url, errorListener );
        listener = successListener;
    }


    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try{
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new String(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new VolleyError(e));
        }
    }

    @Override
    protected void deliverResponse(String response) {
        listener.onResponse(response);
    }
}
