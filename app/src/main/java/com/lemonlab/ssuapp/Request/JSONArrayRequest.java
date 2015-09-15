package com.lemonlab.ssuapp.Request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lk on 2015. 9. 1..
 */
public class JSONArrayRequest extends Request<JSONArray> {

    private String token;
    private Response.Listener<JSONArray> listener;
    private Map<String, String> mParams;

    private JSONArrayRequest(HashMap<String, String> request, Response.Listener<JSONArray> successListener, Response.ErrorListener errorListener) {

        super(Integer.parseInt(request.get("model")), request.get("url"), errorListener);
        this.token = request.get("token");
        listener = successListener;

        if(request.get("model").equals(Method.POST+"")) {
            Log.i("post", "post");
            mParams = request;
            mParams.remove("model");
            mParams.remove("url");
            mParams.remove("token");
        }else{
            mParams = new HashMap<>();
        }

    }

    public static JSONArrayRequest request(HashMap<String, String> request, Response.Listener<JSONArray> successListener, Response.ErrorListener errorListener) {
        return new JSONArrayRequest(request, successListener, errorListener);
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Log.i("jjj",HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONArray(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new VolleyError(e));
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.error(new VolleyError(e));
        }
    }

    @Override
    protected void deliverResponse(JSONArray response) {
        listener.onResponse(response);
    }

    public Map getHeaders() throws AuthFailureError {
        Map params = new HashMap();
        params.put("Authorization", "Bearer " + token);
        return params;
    }
}