package com.lemonlab.ssuapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by lk on 2015. 7. 24..
 */
public class Dao {
    private Context context;
    private SQLiteDatabase database;

    public Dao(Context context) {
        this.context = context;

        database = context.openOrCreateDatabase("test.db",SQLiteDatabase.CREATE_IF_NECESSARY, null);

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
    }

    public String getTimetable(){
        String sql = "SELECT * FROM Timetable";
        Cursor cursor = database.rawQuery(sql,null);
        cursor.moveToNext();
        Log.i("qurry", cursor.getString(2));
        return cursor.getString(2);
    }
}
