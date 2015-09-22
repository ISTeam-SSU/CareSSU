package com.lemonlab.ssuapp.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

import com.lemonlab.ssuapp.Dao;
import com.lemonlab.ssuapp.Model.Timetable;

import java.util.ArrayList;

/**
 * Created by lk on 2015. 7. 24..
 */
public class TableDraw extends View {

    private Context context;

    public TableDraw(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int h = getHeight();
        int w = getWidth();

        System.out.println(h + "/" + w);

        canvas.drawColor(Color.parseColor("#eeeeee")); //set clear

        Dao database = new Dao(context);
        ArrayList<Timetable> timetables = database.getTimetable();

        Paint paint = new Paint();
        Paint paint_text = new Paint();
        paint_text.setColor(Color.WHITE);
        paint_text.setTextSize(20);
        paint_text.setTextAlign(Paint.Align.CENTER);

        paint.setColor(Color.GRAY);


        for(int i=0; i<15; i++)
            canvas.drawLine(0, ((float) (h / 14) * i), w, ((float) (h / 14) * i), paint);
        for(int i=1; i<7; i++)
            canvas.drawLine((float)(w / 6) * i, 0, (float)(w / 6) * i, h, paint);



        ArrayList<Integer> colorArrayList = new ArrayList<>();
        colorArrayList.add(Color.parseColor("#A40A24"));
        colorArrayList.add(Color.parseColor("#410539"));
        colorArrayList.add(Color.parseColor("#F2EBCE"));
        colorArrayList.add(Color.parseColor("#A59B91"));
        colorArrayList.add(Color.parseColor("#F36152"));
        colorArrayList.add(Color.parseColor("#0C274C"));


        paint.setColor(Color.parseColor("#ff009f"));

        for(int i=0; i<timetables.size(); i++){
            Timetable timetable = timetables.get(i);
            int time_count = timetable.getTime_count();
            int[] time_week = timetable.getTime_week();
            float[] time_start = timetable.getFtime_start();
            float[] time_end = timetable.getFtime_end();
            paint.setColor(colorArrayList.get(i));
            for(int k=0; k<time_count; k++) {
                RectF r1 = new RectF();
                r1.set((w / 6) * time_week[k], (int) ((float) (h / 14) * (time_start[k] - 8)), ((w / 6) * time_week[k] + (w / 6)), (int) (float) (h / 14) * (time_end[k] - 8));
                canvas.drawRoundRect(r1, 10, 10, paint);
                canvas.drawText(timetable.getSubject(), (w / 6) * time_week[k] + (w / 12), (((int) ((float) (h / 14) * (time_start[k] - 8)))+((int) (float) (h / 14) * (time_end[k] - 8)))/2, paint_text);
            }
        }


    }
}