package com.lemonlab.ssuapp.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by lk on 2015. 7. 24..
 */
public class TableDraw extends View {


    public TableDraw(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int h = getHeight();
        int w = getWidth();

        System.out.println(h + "/" + w);

        Paint paint = new Paint();

        paint.setColor(Color.GRAY);
        canvas.drawLine(0, ((float) (h / 14) * 0), w, ((float) (h / 14) * 0), paint);
        canvas.drawLine(0, ((float) (h / 14) * 1), w, ((float) (h / 14) * 1), paint);
        canvas.drawLine(0, ((float) (h / 14) * 2), w, ((float) (h / 14) * 2), paint);
        canvas.drawLine(0, ((float) (h / 14) * 3), w, ((float) (h / 14) * 3), paint);
        canvas.drawLine(0, ((float) (h / 14) * 4), w, ((float) (h / 14) * 4), paint);
        canvas.drawLine(0, ((float) (h / 14) * 5), w, ((float) (h / 14) * 5), paint);
        canvas.drawLine(0, ((float) (h / 14) * 6), w, ((float) (h / 14) * 6), paint);
        canvas.drawLine(0, ((float) (h / 14) * 7), w, ((float) (h / 14) * 7), paint);
        canvas.drawLine(0, ((float) (h / 14) * 8), w, ((float) (h / 14) * 8), paint);
        canvas.drawLine(0, ((float) (h / 14) * 9), w, ((float) (h / 14) * 9), paint);
        canvas.drawLine(0, ((float) (h / 14) * 10), w, ((float) (h / 14) * 10), paint);
        canvas.drawLine(0, ((float) (h / 14) * 11), w, ((float) (h / 14) * 11), paint);
        canvas.drawLine(0, ((float) (h / 14) * 12), w, ((float) (h / 14) * 12), paint);
        canvas.drawLine(0, ((float) (h / 14) * 13), w, ((float) (h / 14) * 13), paint);
        canvas.drawLine(0, ((float) (h / 14) * 14), w, ((float) (h / 14) * 14), paint);
        canvas.drawLine((float)(w / 6) * 0, 0, (float)(w / 6) * 0, h, paint);
        canvas.drawLine((float)(w / 6) * 1, 0, (float)(w / 6) * 1, h, paint);
        canvas.drawLine((float)(w / 6) * 2, 0, (float)(w / 6) * 2, h, paint);
        canvas.drawLine((float)(w / 6) * 3, 0, (float)(w / 6) * 3, h, paint);
        canvas.drawLine((float)(w / 6) * 4, 0, (float)(w / 6) * 4, h, paint);
        canvas.drawLine((float)(w / 6) * 5, 0, (float)(w / 6) * 5, h, paint);
        canvas.drawLine((float) (w / 6) * 6, 0, (float) (w / 6) * 6, h, paint);



        paint.setColor(Color.parseColor("#F44336"));
        RectF r1 = new RectF();
        r1.set((w / 6) * 1, (int) ((float) (h / 14) * 0), (w / 6) * 1 + (w / 6), (int) (float) (h / 14) * 5);
        canvas.drawRoundRect(r1, 10, 10, paint);

        paint.setColor(Color.parseColor("#3F51B5"));
        r1 = new RectF();
        r1.set((w / 6) * 3, (int) ((float) (h / 14) * 3), (w / 6) * 3 + (w / 6), (int) (float) (h / 14) * 7);
        canvas.drawRoundRect(r1, 10, 10, paint);


    }
}
