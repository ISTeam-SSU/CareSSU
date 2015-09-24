package com.lemonlab.ssuapp.Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lk on 2015. 7. 25..
 */
public class Timetable {

    private int id;
    private String division;
    private String subject;
    private String teacher;
    private int grade;
    private String time;
    private String time_start[];
    private String time_end[];
    private int time_count;
    private String time_week[];
    private String classroom;
    private String student;
    private String color;




    public Timetable(int id, String division, String subject, String teacher, int grade, String time, String ftime_start, String ftime_end, int time_count, String time_week, String classroom, String student, String color) {
        this.id = id;
        this.division = division;
        this.subject = subject;
        this.teacher = teacher;
        this.grade = grade;
        this.time = time;
        this.classroom = classroom;
        this.student = student;
        this.time_count = time_count;

        this.time_start = new String[6];

        this.time_start = ftime_start.split("/");
        this.time_end = new String[6];
        this.time_end = ftime_end.split("/");

        this.time_week = time_week.split("/");
        this.color = color;
    }

    public int getSubjectId() {
        return id;
    }

    public String getDivision() {
        return division;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public int getGrade() { return grade; }

    public String getTime() { return time; }

    public float[] getFtime_start() {
        float[] ftime_start = new float[6];
        for(int i=0; i<time_count; i++)
            ftime_start[i] = Float.parseFloat(time_start[i]);

        return ftime_start;
    }

    public float[] getFtime_end() {
        float[] ftime_end = new float[6];
        for(int i=0; i<time_count; i++)
            ftime_end[i] = Float.parseFloat(time_end[i]);
        return ftime_end;
    }

    public int getTime_count() { return time_count; }

    public int[] getTime_week(){
        int[] itime_week = new int[6];
        for(int i=0; i<time_count; i++)
            itime_week[i] = Integer.parseInt(time_week[i]);
        return itime_week;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getStudent() {
        return student;
    }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; return;}
}
