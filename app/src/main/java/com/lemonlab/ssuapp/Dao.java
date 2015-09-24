package com.lemonlab.ssuapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lemonlab.ssuapp.Model.Timetable;

import java.util.ArrayList;

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
            String sql = "CREATE TABLE Timetable " +
                    "(Id INTEGER NOT NULL,"
                    + "Division text,"  // 분
                    + "Subject text,"
                    + "Teacher text,"
                    + "Grade int,"
                    + "Time text,"
                    + "Time_start text,"
                    + "Time_end text,"
                    + "Time_count int,"
                    + "Time_week text,"
                    + "Classroom text,"
                    + "Student text,"
                    + "Color text);";
            database.execSQL(sql);
        }catch(Exception e){
            Log.e("DAO","CREATE TABLE FAILED! -" + e);
        }
    }

    public ArrayList<Timetable> getTimetable(){
        ArrayList<Timetable> tablelist = new ArrayList<>();
        String sql = "SELECT * FROM Timetable;";
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
                    cursor.getString(11),  //Student
                    cursor.getString(12));
            cursor.moveToNext();
            tablelist.add(table);
        }
        return tablelist;
    }

    public boolean insertTable(Timetable timetable){
        float[] ftime_start = timetable.getFtime_start();
        float[] ftime_end = timetable.getFtime_end();
        int[] itime_week = timetable.getTime_week();
        String time_start = "";
        String time_end= "";
        String time_week = "";
        for(int i=0; i< timetable.getTime_count(); i++) {
            time_start += ftime_start[i] + "/";
            time_end += ftime_end[i] + "/";
        }
        for(int i=0; i< timetable.getTime_count(); i++)
            time_week += itime_week[i] + "/";


        String sql = "INSERT INTO Timetable (Id, Division, Subject, Teacher, Grade, Time, Time_start, Time_end," +
                "Time_count, Time_week, Classroom, Student, Color)" +
                "VALUES (" + timetable.getSubjectId() + ",'" + timetable.getDivision() + "','" + timetable.getSubject() + "','" +timetable.getTeacher() +
                "'," + timetable.getGrade() + ",'" + timetable.getTime() + "','" + time_start + "','" + time_end +
                "'," + timetable.getTime_count() + ",'" + time_week + "','" + timetable.getClassroom() + "','" + timetable.getStudent()+"', '"+timetable.getColor()+"');";
        try {
            database.execSQL(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}


