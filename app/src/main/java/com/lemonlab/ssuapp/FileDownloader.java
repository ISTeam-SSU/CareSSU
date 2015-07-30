package com.lemonlab.ssuapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.File;

/**
 * Created by lk on 2015. 7. 31..
 */
public class FileDownloader {
    private final Context context;
    private long lastModified;


    public FileDownloader(Context context, long lastModified) {
        this.context = context;
        this.lastModified = lastModified;
    }

    private static AsyncHttpClient client = new AsyncHttpClient();
    public void downFile(String url, String fileName){
        final File filePath = new File(context.getDatabasePath(fileName)+"");
        if(!filePath.exists()) {
            filePath.delete();
        }
        client.get(url, new FileAsyncHttpResponseHandler(context) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                CheckDB.getHandler().sendEmptyMessage(CheckDB.DB_UPDATE_FINISH);
                file.renameTo(filePath);
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = pref.edit();
                editor.putLong("savedDBTime", lastModified);
                editor.commit();
            }
        });

    }

}
