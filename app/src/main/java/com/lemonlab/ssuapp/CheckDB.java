package com.lemonlab.ssuapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lk on 2015. 7. 31..
 */
public class CheckDB {
    private static Context context;
    private static String fileurl;
    private static String fileName;
    private static File dbFile;
    private static long lastModified;
    private static Dialog dialog;


    public static final int DB_ERROR = 1;
    public static final int DB_UPDATE_FINISH = 2;
    public static final int NETWORK_ERROR = 3;

    public CheckDB(Context context, String url, String fileName) {
        this.context = context;
        this.fileurl = url;
        this.fileName = fileName;
        dbFile = new File(android.os.Environment.getExternalStorageDirectory()+"/ssuapp/test.db");
        Log.i("file path ", android.os.Environment.getExternalStorageDirectory()+"/ssuapp/test.db");
        Log.i("dbFileExists",dbFile.exists()+"");
    }

    public void ckDB(){
        CheckProgress();

        if(dbFile.exists()){
            if(!isOnline()){
                handler.sendEmptyMessage(NETWORK_ERROR);
            }else {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(fileurl);
                            URLConnection conn = url.openConnection();
                            lastModified = conn.getLastModified();
                            //서버에 있는 파일의 생성 날짜를 받아온다.

                            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                            if (lastModified != pref.getLong("savedDBTime", 0)) { //로컬 디비와 서버 디피를 비교한다.
                                handler.sendEmptyMessage(DB_ERROR);
                                // 서버 DB가 갱신되지 않았다면 메인화면으로 넘어간다.
                            } else {
                                handler.sendEmptyMessage(DB_UPDATE_FINISH);
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        }else{
            if(isOnline()) {
                handler.sendEmptyMessage(DB_ERROR);  //로컬에 디비가 없으므로 새로 받아온다.
            }else
                handler.sendEmptyMessage(NETWORK_ERROR);
        }

    }

    private void CheckProgress(){
            dialog = ProgressDialog.show(context, "","시간표 DB를 체크합니다 ...", true);
    }

    private static void UpdateProgress(){
        dialog = ProgressDialog.show(context, "","시간표를 업데이트 중입니다 ...", true);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!dbFile.exists()) {
                    handler.sendEmptyMessage(NETWORK_ERROR);
                }
            }
        }, 10000);

    }

    private static void DialogUpdate(){
        dialog.dismiss();
        AlertDialog.Builder updateck = new AlertDialog.Builder(context);
        updateck.setMessage("시간표 데이터를 업데이트해야합니다.").setCancelable(false).setPositiveButton("수락", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                UpdateProgress();
                FileDownloader fileDownloader = new FileDownloader(context, lastModified);
                fileDownloader.downFile(fileurl,fileName);
            }
        }).setNegativeButton("거절", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog alert = updateck.create();
        alert.setTitle("시간표 DB 업데이트");
        alert.show();
    }

    public static void DialogNetworkError(){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        alert.setMessage("네트워크 상태를 확인해주세요.");
       alert.show();
    }

    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DB_ERROR:
                    dialog.dismiss();
                    DialogUpdate();
                    break;
                case DB_UPDATE_FINISH:
                    dialog.dismiss();
                    break;
                case NETWORK_ERROR:
                    dialog.dismiss();
                    DialogNetworkError();
            }
        }
    };

    public static Handler getHandler(){
        return handler;
    }

    private static boolean isOnline(){
        try{
            ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo.State wifi = conMan.getNetworkInfo(1).getState();
            if(wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
                return true;
            NetworkInfo.State mobile = conMan.getNetworkInfo(0).getState();
            if(mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING){
                return true;
            }
        }catch (NullPointerException e){
            return false;
        }
        return false;
    }

}
