package com.lemonlab.ssuapp;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        final File filePath = new File(android.os.Environment.getExternalStorageDirectory()+"/ssuapp/test.db");
        Log.i("filepath", filePath+"");
        if(!filePath.exists()) {
            filePath.delete();
        }
//        client.get(url, new FileAsyncHttpResponseHandler(context) {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
//                Log.e("FileDown", "error");
//            }
//
//            @Override
//            public void onSuccess(int statusCode, org.apache.http.Header[] headers, File file) {
//                CheckDB.getHandler().sendEmptyMessage(CheckDB.DB_UPDATE_FINISH);
//
//                try{        //File rename tmp folder to sdcard
//                    InputStream is = new FileInputStream(file);
//                    OutputStream os = new FileOutputStream(filePath);
//
//                    byte[] buffer = new byte[1024];
//                    while (is.read(buffer) > 0) {
//                        os.write(buffer);
//                    }
//
//                    os.flush();
//                    os.close();
//                    is.close();
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//
//                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putLong("savedDBTime", lastModified);
//                editor.commit();
//                Log.i("FileDown", "Success");
//            }
//        });
    }

}
