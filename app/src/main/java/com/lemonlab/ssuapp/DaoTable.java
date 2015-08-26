package com.lemonlab.ssuapp;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.lemonlab.ssuapp.Model.Timetable;

import java.sql.Time;
import java.util.ArrayList;


/**
 * Created by lk on 2015. 8. 4..
 */
public class DaoTable {

    private Context context;
    private SQLiteDatabase database;

    public DaoTable(Context context) {
        this.context = context;
        database = context.openOrCreateDatabase(android.os.Environment.getExternalStorageDirectory()+"/ssuapp/test.db",SQLiteDatabase.CREATE_IF_NECESSARY, null);

        try{
            String sql = "CREATE TABLE IF NOT EXISTS Timetable (Id integer not null," +
                    "                                           Division text not null," +
                    "                                           Subject text not null," +
                    "                                           Teacher text not null," +
                    "                                           Grade integer not null," +
                    "                                           Time text," +
                    "                                           Classroom text," +
                    "                                           student text not null);";
            database.execSQL(sql);
        }catch(Exception e){
            Log.e("DAO","CREATE TABLE FAILED! -" + e);
        }

        String sql = "SELECT * FROM Timetable";
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.getCount()==0)
            dialog();
    }

    public ArrayList<Timetable> getTimetable(int id){
        ArrayList<Timetable> tablelist = new ArrayList<>();
        String sql = "SELECT * FROM Timetable WHERE Id = "+id +";";
        Cursor cursor = database.rawQuery(sql,null);
        cursor.moveToNext();
        while(!cursor.isAfterLast()){
            Timetable table = new Timetable(cursor.getInt(0),
                    cursor.getString(1),    //Division
                    cursor.getString(2),    //Subject
                    cursor.getString(3),    //Teacher
                    cursor.getInt(4),       //Grade
                    cursor.getString(5),    //Time String
                    cursor.getString(6),    //Time_start
                    cursor.getString(7),    //Time_end
                    cursor.getInt(8),       //time_count
                    cursor.getString(9),    //Time_week
                    cursor.getString(10),    //Classroom
                    cursor.getString(11));  //Student
            cursor.moveToNext();
            tablelist.add(table);
        }
        return tablelist;
    }


    public void dialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     //닫기
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        alert.setMessage("시간표 데이터를 찾을 수 없습니다.\n새로 받아주시기 바랍니다.");
        alert.show();
    }

}
