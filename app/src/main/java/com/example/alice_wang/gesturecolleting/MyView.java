package com.example.alice_wang.gesturecolleting;

/**
 * Created by alice_wang on 16/11/25.
 */
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("DrawAllocation")
public class MyView extends View {
    Paint paint;
    float x, y;
    int radius;
    /**
     *
     *
     * @param context
     */
    public MyView(Context context,float X,float Y,int radius) {
        super(context);
        paint = new Paint();
        //
        paint.setColor(Color.RED);
        //
        paint.setStrokeWidth(2);
        //
        paint.setStyle(Style.STROKE);
        //
        paint.setAntiAlias(true);
        this.x=X;
        this.y=Y;
        this.radius=radius;
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();

        paint.setColor(Color.RED);
        //
        paint.setStrokeWidth(2);
        //
        paint.setStyle(Style.STROKE);
        //
        paint.setAntiAlias(true);

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(x, y, radius, paint);


    }
}
