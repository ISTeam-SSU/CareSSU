package com.lemonlab.ssuapp;

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
    private float ftime_start[];
    private float ftime_end[];
    private int time_week;
    private int week[];
    private String classroom;
    private String student;


    public Timetable(int id, String division, String subject, String teacher, int grade, String time, String classroom, String student) {
        this.id = id;
        this.division = division;
        this.subject = subject;
        this.teacher = teacher;
        this.grade = grade;
        this.time = time;
        this.classroom = classroom;
        this.student = student;

        this.ftime_start = new float[6];
        this.ftime_end = new float[6];
        this.week = new int[6];


    }
}
