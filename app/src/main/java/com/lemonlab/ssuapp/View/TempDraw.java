package com.lemonlab.ssuapp.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.view.View;

import com.lemonlab.ssuapp.R;

/**
 * Created by lk on 2015. 8. 5..
 */
public class TempDraw extends View {

    private float[] time_start;
    private float[] time_end;
    private int time_count;
    private int[] time_week;



    public TempDraw(Context context, float[] time_start, float[] time_end, int time_count, int[] time_week) {
        super(context);
        this.time_start = time_start;
        this.time_end = time_end;
        this.time_count = time_count;
        this.time_week = time_week;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int h = getHeight();
        int w = getWidth();

        System.out.println(h + "/" + w);



        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#9E9E9E"));



        for(int i=0; i<time_count; i++) {
            RectF r1 = new RectF();
            r1.set((w / 6) * time_week[i], (int) ((float) (h / 14) * (time_start[i]-8)), ((w / 6) * time_week[i] + (w / 6)), (int) (float) (h / 14) * (time_end[i]-8));
            canvas.drawRoundRect(r1, 10, 10, paint);
        }




    }
}
