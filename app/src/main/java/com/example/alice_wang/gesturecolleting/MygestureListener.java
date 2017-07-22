package com.example.alice_wang.gesturecolleting;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by alice_wang on 16/11/1.
 */
public class MygestureListener extends GestureDetector.SimpleOnGestureListener {
    /*
    @Override
    public boolean onDown(MotionEvent e) {
        ReadActivity.position.setText("单手起始位置" + " (" + e.getX() + " , " + e.getY() + ")");
        return super.onDown(e);
    }
    @Override
    public void onShowPress(MotionEvent e) {
        ReadActivity.position.append("单手移动位置" + " (" + e.getX() + " , " + e.getY() + ")");
        super.onShowPress(e);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        ReadActivity.position.append("单手终止位置" + " (" + e.getX() + " , " + e.getY() + ")");
        return super.onSingleTapUp(e);

    }*/
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        ReadActivity.position.setText("双手起始位置" + " (" + e1.getX() + " , " + e1.getY() + ")" + " (" + e2.getX() + " , " + e2.getY() + ")");
        ReadActivity.position.append("双手移动位置" + " (" + e1.getX() + " , " + e1.getY() + ")" + " (" + e2.getX() + " , " + e2.getY() + ")");
        //return false;
        return super.onScroll(e1, e2, distanceX, distanceY);
    }


}
