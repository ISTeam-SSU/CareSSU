package com.lemonlab.ssuapp.Model;

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
    private String ftime_start[];
    private String ftime_end[];
    private int time_week;
    private String classroom;
    private String student;




    public Timetable(int id, String division, String subject, String teacher, int grade, String time, String ftime_start, String ftime_end, int time_count, String classroom, String student) {
        this.id = id;
        this.division = division;
        this.subject = subject;
        this.teacher = teacher;
        this.grade = grade;
        this.time = time;
        this.classroom = classroom;
        this.student = student;
        this.time_week = time_count;

        this.ftime_start = new String[6];

        this.ftime_start = ftime_start.split("/");
        this.ftime_end = new String[6];
        this.ftime_end = ftime_end.split("/");
    }

    public int getId() {
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

    public int getGrade() {
        return grade;
    }

    public String getTime() {
        return time;
    }

    public String[] getFtime_start() {
        return ftime_start;
    }

    public String[] getFtime_end() {
        return ftime_end;
    }

    public int getTime_week() {
        return time_week;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getStudent() {
        return student;
    }
}
