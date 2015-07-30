package com.lemonlab.ssuapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;

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

    public CheckDB(Context context, String url, String fileName) {
        this.context = context;
        this.fileurl = url;
        this.fileName = fileName;
        dbFile = new File(context.getDatabasePath(fileName).toString());
    }

    public void ckDB(){
        CheckProgress();
        if(dbFile.exists()){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(fileurl);
                        URLConnection conn = url.openConnection();
                        lastModified = conn.getLastModified();
                        //서버에 있는 파일의 생성 날짜를 받아온다.

                        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                        if (lastModified > pref.getLong("savedDBTime", 0)) { //로컬 디비와 서버 디피를 비교한다.
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
        }else{
            handler.sendEmptyMessage(DB_ERROR);  //로컬에 디비가 없으므로 새로 받아온다.
        }
    }

    private void CheckProgress(){
            dialog = ProgressDialog.show(context, "","시간표 DB를 체크합니다 ...", true);
    }

    private static void UpdateProgress(){
        dialog = ProgressDialog.show(context, "","시간표를 업데이트 중입니다 ...", true);
    }

    private static void DialogSimple(){
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

    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DB_ERROR:
                    dialog.dismiss();
                    DialogSimple();
                    break;
                case DB_UPDATE_FINISH:
                    dialog.dismiss();
                    break;
            }
        }
    };

    public static Handler getHandler(){
        return handler;
    }

}
