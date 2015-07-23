package com.lemonlab.ssuapp.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by lk on 2015. 7. 24..
 */
public class Timetable extends View {


    public Timetable(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int h = getHeight();
        int w = getWidth();

        System.out.println(h + "/" + w);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        Rect r1 = new Rect();
        r1.set((w / 6) * 1, (int) ((float) (h / 13) * 0), (w / 6) * 1 + (w / 6), (int) (float) (h / 13) * 13);
        System.out.println((w/6)*1+"/"+(int)((float)(h/13) * 200)+"/"+ (w /6)*1+(w/6)+"/"+(int)(float)(h/13)*300);


        canvas.drawRect(r1, paint);
    }
}
