package com.example.alice_wang.gesturecolleting;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by alice_wang on 16/11/25.
 */
public class FindDifActivity extends Activity {
    RelativeLayout relativeLayout;
    ProgressBar timeBar;
    TextView timeTv;
    ImageView mv1;
    int radius = 40;
    static final int TIME_MSG = 0x001;
    float windowWidth, windowHegiht;
    int i = 60;
    float w, h, r;
    float iPoint1_X, iPoint1_Y, iPoint2_X, iPoint2_Y, iPoint3_X, iPoint3_Y,
            iPoint1_X_l, iPoint2_X_l, iPoint3_X_l;
    int k = 3;
    boolean flag_1 = true;
    boolean flag_2 = true;
    boolean flag_3 = true;
    imageOnTouchListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findif);

        //to get windows length and width
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        windowWidth = metric.widthPixels;
        windowHegiht = metric.heightPixels;

        //to get the smaller length width radius
        w = windowWidth / 800;
        h = windowHegiht / 480;
        r = (windowHegiht * windowWidth) / (480 * 800 *4);
        Log.i("123", "w=" + w + "h=" + h + "r=" + r);

        //to define the layout timebar timetv image
        relativeLayout = (RelativeLayout) findViewById(R.id.relat_layout);
        timeBar = (ProgressBar) findViewById(R.id.time_pBar);
        timeTv = (TextView) findViewById(R.id.time_tv);
        mv1 = (ImageView) findViewById(R.id.mv_1);
        timeBar.setMax(60);
        timeBar.setProgress(60);

        //listen the image
        listener = new imageOnTouchListener();
        mv1.setOnTouchListener(listener);

        //to record the different point
        iPoint1_X = (float) 500.0;
        iPoint1_Y = (float) 37.0;
        iPoint2_X = (float) 456.0;
        iPoint2_Y = (float) 189.0;
        iPoint3_X = (float) 736.0;
        iPoint3_Y = (float) 275.0;

        //the left picture is about 400pixel
        iPoint1_X_l = iPoint1_X - 400;
        iPoint2_X_l = iPoint2_X - 400;
        iPoint3_X_l = iPoint3_X - 400;

        handler.sendEmptyMessage(TIME_MSG);
    }

    class imageOnTouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                float X =  (event.getX() / w);
                float Y =  (event.getY() / h);
                float dis1 = (X - iPoint1_X) * (X - iPoint1_X)
                        + (Y - iPoint1_Y) * (Y - iPoint1_Y);
                float dis2 = (X - iPoint2_X) * (X - iPoint2_X)
                        + (Y - iPoint2_Y) * (Y - iPoint2_Y);
                float dis3 = (X - iPoint3_X) * (X - iPoint3_X)
                        + (Y - iPoint3_Y) * (Y - iPoint3_Y);
                float dis1_l = (X - iPoint1_X_l) * (X - iPoint1_X_l)
                        + (Y - iPoint1_Y) * (Y - iPoint1_Y);
                float dis2_l = (X - iPoint2_X_l) * (X - iPoint2_X_l)
                        + (Y - iPoint2_Y) * (Y - iPoint2_Y);
                float dis3_l = (X - iPoint3_X_l) * (X - iPoint3_X_l)
                        + (Y - iPoint3_Y) * (Y - iPoint3_Y);

                //acceptable radius
                float rad = 1600;
                //draw the radius
                int radiusNow = (int) (radius * r);

                if (dis1 < rad && dis2 > rad && dis3 > rad) {
                    if (flag_1) {
                        relativeLayout.addView(new MyView(
                                getApplicationContext(), iPoint1_X * w,
                                iPoint1_Y * h, radiusNow));
                        relativeLayout.addView(new MyView(
                                getApplicationContext(), iPoint1_X_l * w,
                                iPoint1_Y * h, radiusNow));
                        flag_1 = false;

                        //??
                        k--;

                    }

                } else if (dis1 > rad && dis2 < rad && dis3 > rad) {
                    if (flag_2) {
                        relativeLayout.addView(new MyView(
                                getApplicationContext(), iPoint2_X * w,
                                iPoint2_Y * h, radiusNow));
                        relativeLayout.addView(new MyView(
                                getApplicationContext(), iPoint2_X_l * w,
                                iPoint2_Y * h, radiusNow));
                        flag_2 = false;
                        k--;

                    }

                } else if (dis1 > rad && dis2 > rad && dis3 < rad) {
                    if (flag_3) {
                        relativeLayout.addView(new MyView(
                                getApplicationContext(), iPoint3_X * w,
                                iPoint3_Y * h, radiusNow));
                        relativeLayout.addView(new MyView(
                                getApplicationContext(), iPoint3_X_l * w,
                                iPoint3_Y * h, radiusNow));
                        flag_3 = false;
                        k--;

                    }

                } else if (dis1_l < rad && dis2_l > rad && dis3_l > rad) {
                    if (flag_1) {
                        relativeLayout.addView(new MyView(
                                getApplicationContext(), iPoint1_X * w,
                                iPoint1_Y * h, radiusNow));
                        relativeLayout.addView(new MyView(
                                getApplicationContext(), iPoint1_X_l * w,
                                iPoint1_Y * h, radiusNow));
                        flag_1 = false;
                        k--;

                    }
                } else if (dis1_l > rad && dis2_l < rad && dis3_l > rad) {
                    if (flag_2) {
                        relativeLayout.addView(new MyView(
                                getApplicationContext(), iPoint2_X * w,
                                iPoint2_Y * h, radiusNow));
                        relativeLayout.addView(new MyView(
                                getApplicationContext(), iPoint2_X_l * w,
                                iPoint2_Y * h, radiusNow));
                        flag_2 = false;
                        k--;

                    }
                } else if (dis1_l > rad && dis2_l > rad && dis3_l < rad) {
                    if (flag_3) {
                        relativeLayout.addView(new MyView(
                                getApplicationContext(), iPoint3_X * w,
                                iPoint3_Y * h, radiusNow));
                        relativeLayout.addView(new MyView(
                                getApplicationContext(), iPoint3_X_l * w,
                                iPoint3_Y * h, radiusNow));
                        flag_3 = false;
                        k--;
                    }
                }
                if (k == 0) {

                    //when you successfully pass
                    handler.removeMessages(TIME_MSG);
                    Builder dialog = new AlertDialog.Builder(FindDifActivity.this);
                    dialog.setCancelable(false);
                    dialog.setNegativeButton("N", new OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FindDifActivity.this.finish();
                            dialog.dismiss();
                        }
                    });
                    dialog.setMessage("恭喜你通关！").setTitle("PASS")
                            .setPositiveButton("Y", new OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    FindDifActivity.this
                                            .setContentView(R.layout.activity_findif);
                                    timeTv = (TextView) findViewById(R.id.time_tv);
                                    timeBar = (ProgressBar) findViewById(R.id.time_pBar);
                                    relativeLayout = (RelativeLayout) findViewById(R.id.relat_layout);
                                    mv1 = (ImageView) findViewById(R.id.mv_1);
                                    mv1.setOnTouchListener(listener);
                                    timeBar.setMax(60);
                                    timeBar.setProgress(60);
                                    i = 60;
                                    k = 3;
                                    flag_1 = true;
                                    flag_2 = true;
                                    flag_3 = true;
                                    handler.sendEmptyMessage(TIME_MSG);
                                    dialog.dismiss();
                                }
                            });
                    dialog.show();
                }

            }
            return false;
        }
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TIME_MSG:
                    String timeString;
                    if (i > 0) {
                        i--;
                        if (i < 10) {
                            timeString = "00:0" + i;
                        } else {
                            timeString = "00:" + i;
                        }
                        timeTv.setText(timeString);
                        timeBar.setProgress(i);
                        handler.sendEmptyMessageDelayed(TIME_MSG, 1000);
                    } else {
                        // if the game is over
                        Builder dialog = new AlertDialog.Builder(FindDifActivity.this);
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("N", new OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FindDifActivity.this.finish();
                                dialog.dismiss();
                            }
                        });
                        dialog.setMessage("非常遗憾还想再玩一次？").setTitle("Game over")
                                .setPositiveButton("Y", new OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        FindDifActivity.this
                                                .setContentView(R.layout.activity_findif);
                                        timeTv = (TextView) findViewById(R.id.time_tv);
                                        timeBar = (ProgressBar) findViewById(R.id.time_pBar);
                                        relativeLayout = (RelativeLayout) findViewById(R.id.relat_layout);
                                        mv1 = (ImageView) findViewById(R.id.mv_1);
                                        mv1.setOnTouchListener(listener);
                                        timeBar.setMax(60);
                                        timeBar.setProgress(60);
                                        i = 60;
                                        k = 3;
                                        flag_1 = true;
                                        flag_2 = true;
                                        flag_3 = true;
                                        handler.sendEmptyMessage(TIME_MSG);
                                        dialog.dismiss();
                                    }
                                });
                        dialog.show();
                    }

                    break;

                default:
                    break;
            }
        }

    };

}
