package com.lemonlab.ssuapp.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.view.View;

import com.lemonlab.ssuapp.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lk on 2015. 8. 5..
 */
public class TempDraw extends View {

    private float[] time_start;
    private float[] time_end;
    private int time_count;
    private int[] time_week;

    ArrayList<String> colors;
    int randomInt;

    public TempDraw(Context context, float[] time_start, float[] time_end, int time_count, int[] time_week) {
        super(context);
        this.time_start = time_start;
        this.time_end = time_end;
        this.time_count = time_count;
        this.time_week = time_week;

        colors = new ArrayList<>();
        colors.add("#FF530D");
        colors.add("#E82C0C");
        colors.add("#FF0000");
        colors.add("#E80C7A");
        colors.add("#FF0DFF");
        colors.add("#54CC14");
        colors.add("#58D1FF");
        colors.add("#B497FF");
        colors.add("#A320CC");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int h = getHeight();
        int w = getWidth();

        System.out.println(h + "/" + w);



        Paint paint = new Paint();

        Random random = new Random();
        randomInt = random.nextInt(colors.size()-1);

        paint.setColor(Color.parseColor(colors.get(randomInt)));

        for(int i=0; i<time_count; i++) {
            RectF r1 = new RectF();
            r1.set((w / 6) * time_week[i], (int) ((float) (h / 14) * (time_start[i]-8)), ((w / 6) * time_week[i] + (w / 6)), (int) (float) (h / 14) * (time_end[i]-8));
            canvas.drawRoundRect(r1, 10, 10, paint);
        }




    }

    public String getColor(){
        return colors.get(randomInt);
    }

}

