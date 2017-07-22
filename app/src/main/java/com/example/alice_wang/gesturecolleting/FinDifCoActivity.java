package com.example.alice_wang.gesturecolleting;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by alice_wang on 16/11/28.
 */
public class FinDifCoActivity  extends  Activity implements SensorEventListener {

    private SensorManager sensorManager;//Acceleration
    private SensorManager sensorManager2;//Gyroscope

    private ImageView imageView;
    HorizontalScrollView mScrollView;//滑动
    EditText hmdif;
    Button submit;
    long oldtime = 0;
    long newtime = 0;
    boolean flag = true;

    float distanceX = 0;
    float distanceY = 0;
    float distanX1 = 0;
    float distanY1 = 0;
    float distanX2 = 0;
    float distanY2 = 0;
    long timetemp1 = 0;
    long timetemp2 = 0;

    TextView username;//用户名
    TextView usercount;//用户输入次数
    int count = 0;
    int ondown_count = 0;
    int onscroll_count = 0;
    int onzoom_count = 0;
    ArrayList ac = new ArrayList();// to formally store every gyroscope acceleration
    ArrayList gy = new ArrayList();// to formally store every gyroscope
    ArrayList accc = new ArrayList();// to store every value of acceleration
    ArrayList gyyy = new ArrayList();// to store every value of gyroscope
    String everyac;
    String maxac ;//to store the max acceleration
    String minac ;//to store the min acceleration
    String averac ;//to store the average acceleration
    String stdac ;//to store the standard acceleration
    String everygy;
    String maxgy ;//to store the max gyroscope
    String mingy ;//to store the min gyroscope
    String avergy ;//to store the average gyroscope
    String stdgy ;//to store the standard gyroscope
    ArrayList vx = new ArrayList();//to get velocity_x
    ArrayList vy = new ArrayList();//to get velocity_y
    String everyvx;
    String maxvx ;//to store the max velocity_x
    String minvx ;//to store the min velocity_x
    String avervx ;//to store the average velocity_x
    String stdvx ;//to store the standard velocity_x
    String everyvy;
    String maxvy ;//to store the max velocity_y
    String minvy ;//to store the min velocity_y
    String avervy ;//to store the average velocity_y
    String stdvy ;//to store the standard velocity_y
    ArrayList pres = new ArrayList();//to get the pressure
    ArrayList size = new ArrayList();//to get the size
    String everypres;
    String maxpres ;//to store the max pressure
    String minpres ;//to store the min pressure
    String averpres ;//to store the average pressure
    String stdpres ;//to store the standard pressure
    String everysize;
    String maxsize ;//to store the max size
    String minsize ;//to store the min size
    String aversize ;//to store the average size
    String stdsize ;//to store the standard size
    ArrayList x_start = new ArrayList();//to get the x_start
    ArrayList x_stop = new ArrayList();//to get the x_stop
    ArrayList y_start = new ArrayList();//to get the y_start
    ArrayList y_stop = new ArrayList();//to get the y_stop
    String everyxa;
    String maxxa ;//to store the max x_start
    String minxa ;//to store the min x_start
    String averxa ;//to store the average x_start
    String stdxa ;//to store the standard x_start
    String everyxo;
    String maxxo ;//to store the max x_stop
    String minxo ;//to store the min x_stop
    String averxo ;//to store the average x_stop
    String stdxo ;//to store the standard x_stop
    String everyya;
    String maxya ;//to store the max y_start
    String minya ;//to store the min y_start
    String averya ;//to store the average y_start
    String stdya ;//to store the standard y_start
    String everyyo;
    String maxyo ;//to store the max y_stop
    String minyo;//to store the min y_stop
    String averyo ;//to store the average y_stop
    String stdyo ;//to store the standard y_stop
    ArrayList dis_x = new ArrayList();//to get the dis_x
    ArrayList dis_y = new ArrayList();//to get the dis_y
    String everydx;
    String maxdx ;//to store the max dis_x
    String mindx ;//to store the min dis_x
    String averdx ;//to store the average dis_x
    String stddx ;//to store the standard dis_x
    String everydy;
    String maxdy ;//to store the max dis_y
    String mindy;//to store the min dis_y
    String averdy ;//to store the average dis_y
    String stddy ;//to store the standard dis_y

    SQLiteDatabase db;



    //to get gyroscope data in three axis
    public String[] getAcceleration(SensorEvent event){
        String[] acc = new String[2];
        double value = Math.sqrt((event.values[0])*(event.values[0]) +
                (event.values[1])*(event.values[1]) +
                (event.values[2])*(event.values[2]));
        acc[0] ="Acceleration X axis" + event.values[0] +
                "   Y axis" + event.values[1] +
                "   Z axis" + event.values[2] + "  value" +value;
        acc[1] = String.valueOf(value);

        return acc;
    }

    //to get gyroscope data in three axis
    public String[] getGyroscope(SensorEvent event){
        String[] gyr = new String[2];
        double value = Math.sqrt((event.values[0])*(event.values[0]) +
                (event.values[1])*(event.values[1]) +
                (event.values[2])*(event.values[2]));
        gyr[0] ="Gyroscope X axis" + event.values[0] +
                "   Y axis" + event.values[1] +
                "   Z axis" + event.values[2]+ "  value" +value;
        gyr[1] = String.valueOf(value);

        return gyr;
    }

    //record every sample
    public String arrayEvery(ArrayList sample){
        String everyone = "";
        for(int i=0; i<sample.size();i++){
            everyone = everyone + "Sample " +i +"  "+sample.get(i)+"    ";
        }
        return everyone;
    }

    //to get the max valve
    public double arrayMax(ArrayList sample){
        if(!sample.isEmpty()){
            double maxvalue =Double.valueOf((String) sample.get(0));
            for(int i=0; i<sample.size();i++){
                if(Double.valueOf((String) sample.get(i)) > maxvalue)
                    maxvalue = Double.valueOf((String) sample.get(i));
            }
            return maxvalue;}
        else {
            return 0;
        }
    }

    //to get the min valve
    public double arrayMin(ArrayList sample){
        if(!sample.isEmpty()){
            double minvalue = Double.valueOf((String) sample.get(0));
            for(int i=0; i<sample.size();i++){
                if(Double.valueOf((String) sample.get(i)) < minvalue)
                    minvalue = Double.valueOf((String) sample.get(i));
            }
            return minvalue;
        }
        else {
            return 0;
        }
    }

    //to get the average value
    public double averageValue(ArrayList sample){
        double sum=0;
        if(!sample.isEmpty()){
            for(int i=0; i<sample.size();i++){
                sum = sum + Double.valueOf((String) sample.get(i));
            }
            return sum/sample.size();
        }
        else {
            return 0;
        }
    }

    //to get the standard value
    public double stdValue(ArrayList sample){
        double sum=0;
        double aver;
        if(!sample.isEmpty()){
            for(int i=0; i<sample.size();i++){
                sum = sum + Double.valueOf((String) sample.get(i));
            }
            aver = sum/sample.size();
            sum = 0;
            for(int i=0; i<sample.size();i++){
                sum = sum + Math.pow((Double.valueOf((String) sample.get(i)) - aver), 2);
            }
            return sum/sample.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findifco);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager2 = (SensorManager) getSystemService(SENSOR_SERVICE);

        imageView = (ImageView) this.findViewById(R.id.mv_1);
        imageView.setOnTouchListener(new TouchListener());
        mScrollView = (HorizontalScrollView)findViewById(R.id.SCROLLER_ID);
        username  = (TextView)findViewById(R.id.username);
        usercount = (TextView)findViewById(R.id.usercount);
        hmdif = (EditText)findViewById(R.id.hmdif);
        submit = (Button)findViewById(R.id.submit);
        username.setText("username: " + MainActivity.DataName);
        usercount.setText("usercount: " + count);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = hmdif.getText().toString();
                Log.i("wyhanswer",answer);
                if (answer.equals("5")){
                    new AlertDialog.Builder(FinDifCoActivity.this).setTitle("PASS")
                            .setMessage("恭喜您回答正确！").setPositiveButton("确定", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){

                            Intent intent = new Intent();
                            intent.setClass(FinDifCoActivity.this, FinDifZoomActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        }
                    }).show();

                }else{
                    new AlertDialog.Builder(FinDifCoActivity.this).setTitle("Game over")
                            .setMessage("请再次仔细查看图片！胜利就在前方").setPositiveButton("确定", null)
                            .show();
                }
            }
        });

    }

    private final class TouchListener implements View.OnTouchListener {


        /** 记录是移动照片模式还是放大缩小照片模式 */
        private int mode = 0;// 初始状态
        /** 移动照片模式 */
        private static final int MODE_Flick = 1;
        /** 放大缩小照片模式 */
        private static final int MODE_ZOOM = 2;

        /** 用于记录开始时候的坐标位置 */
        private PointF startPoint = new PointF();
        /** 用于记录图片移动的坐标位置 */
        private Matrix matrix = new Matrix();
        /** 用于记录图片要进行变化时候的坐标位置 */
        private Matrix currentMatrix = new Matrix();

        /** 两个手指的开始距离 */
        private float startDis;
        /** 两个手指的中间点 */
        private PointF midPoint;

        @Override
        public boolean onTouch(View v, MotionEvent event) {


            newtime = System.currentTimeMillis();
            if (newtime - oldtime > 100 && ondown_count == 1){
                flag = true;
                if (newtime - oldtime > 600000) {
                    Log.i("wyh", "the first time");
                    flag = false;
                }
                oldtime = newtime;
            }


            if(flag)
            {

                //deal with the data collected
                everyac = arrayEvery(accc);
                maxac = String.valueOf(arrayMax(accc));
                minac = String.valueOf(arrayMin(accc));
                averac = String.valueOf(averageValue(accc));
                stdac = String.valueOf(stdValue(accc));
                everygy = arrayEvery(gyyy);
                maxgy = String.valueOf(arrayMax(gyyy));
                mingy = String.valueOf(arrayMin(gyyy));
                avergy = String.valueOf(averageValue(gyyy));
                stdgy = String.valueOf(stdValue(gyyy));
                everyvx = arrayEvery(vx);
                maxvx = String.valueOf(arrayMax(vx));
                minvx = String.valueOf(arrayMin(vx));
                avervx = String.valueOf(averageValue(vx));
                stdvx = String.valueOf(stdValue(vx));
                everyvy = arrayEvery(vy);
                maxvy = String.valueOf(arrayMax(vy));
                minvy = String.valueOf(arrayMin(vy));
                avervy = String.valueOf(averageValue(vy));
                stdvy = String.valueOf(stdValue(vy));
                everypres = arrayEvery(pres);
                maxpres = String.valueOf(arrayMax(pres));
                minpres = String.valueOf(arrayMin(pres));
                averpres = String.valueOf(averageValue(pres));
                stdpres = String.valueOf(stdValue(pres));
                everysize = arrayEvery(size);
                maxsize = String.valueOf(arrayMax(size));
                minsize = String.valueOf(arrayMin(size));
                aversize = String.valueOf(averageValue(size));
                stdsize = String.valueOf(stdValue(size));
                everyxa = arrayEvery(x_start);
                maxxa = String.valueOf(arrayMax(x_start));
                minxa = String.valueOf(arrayMin(x_start));
                averxa = String.valueOf(averageValue(x_start));
                stdxa = String.valueOf(stdValue(x_start));
                everyxo = arrayEvery(x_stop);
                maxxo = String.valueOf(arrayMax(x_stop));
                minxo = String.valueOf(arrayMin(x_stop));
                averxo = String.valueOf(averageValue(x_stop));
                stdxo = String.valueOf(stdValue(x_stop));
                everyya = arrayEvery(y_start);
                maxya = String.valueOf(arrayMax(y_start));
                minya = String.valueOf(arrayMin(y_start));
                averya = String.valueOf(averageValue(y_start));
                stdya = String.valueOf(stdValue(y_start));
                everyyo = arrayEvery(y_stop);
                maxyo = String.valueOf(arrayMax(y_stop));
                minyo = String.valueOf(arrayMin(y_stop));
                averyo = String.valueOf(averageValue(y_stop));
                stdyo = String.valueOf(stdValue(y_stop));
                everydx = arrayEvery(dis_x);
                maxdx = String.valueOf(arrayMax(dis_x));
                mindx = String.valueOf(arrayMin(dis_x));
                averdx = String.valueOf(averageValue(dis_x));
                stddx = String.valueOf(stdValue(dis_x));
                everydy = arrayEvery(dis_y);
                maxdy = String.valueOf(arrayMax(dis_y));
                mindy = String.valueOf(arrayMin(dis_y));
                averdy = String.valueOf(averageValue(dis_y));
                stddy = String.valueOf(stdValue(dis_y));

                //effient scroll
                if(!maxdx.equals("0.0")) {
                    Log.i("wyh", arrayEvery(accc));
                    Log.i("wyh", "accmax" + maxac);
                    Log.i("wyh", "accmin" + minac);
                    Log.i("wyh", "accaver" + averac);
                    Log.i("wyh", "accstd" + stdac);
                    Log.i("wyh", "vxmax" + maxvx);
                    Log.i("wyh", "vxmin" + minvx);
                    Log.i("wyh", "vxaver" + avervx);
                    Log.i("wyh", "vxstd" + stdvx);
                    Log.i("wyh", "presmax" + maxpres);
                    Log.i("wyh", "presmin" + minpres);
                    Log.i("wyh", "presaver" + averpres);
                    Log.i("wyh", "presstd" + stdpres);
                    Log.i("wyh", "sizemax" + maxsize);
                    Log.i("wyh", "sizemin" + minsize);
                    Log.i("wyh", "sizeaver" + aversize);
                    Log.i("wyh", "sizestd" + stdsize);
                    Log.i("wyh", "xamax" + maxxa);
                    Log.i("wyh", "xamin" + minxa);
                    Log.i("wyh", "xaaver" + averxa);
                    Log.i("wyh", "xastd" + stdxa);
                    Log.i("wyh", "xomax" + maxxo);
                    Log.i("wyh", "xomin" + minxo);
                    Log.i("wyh", "xoaver" + averxo);
                    Log.i("wyh", "xostd" + stdxo);
                    Log.i("wyh", "yamax" + maxya);
                    Log.i("wyh", "yamin" + minya);
                    Log.i("wyh", "yaaver" + averya);
                    Log.i("wyh", "yastd" + stdya);
                    Log.i("wyh", "yomax" + maxyo);
                    Log.i("wyh", "yomin" + minyo);
                    Log.i("wyh", "yoaver" + averyo);
                    Log.i("wyh", "yostd" + stdyo);
                    Log.i("wyh", "dxmax" + maxdx);
                    Log.i("wyh", "dxmin" + mindx);
                    Log.i("wyh", "dxaver" + averdx);
                    Log.i("wyh", "dxstd" + stddx);
                    Log.i("wyh", "dymax" + maxdy);
                    Log.i("wyh", "dymin" + mindy);
                    Log.i("wyh", "dyaver" + averdy);
                    Log.i("wyh", "dystd" + stddy);
                    count++;
                    usercount.setText("usercount: " + count);

                        db = openOrCreateDatabase("imagegc.db", Context.MODE_PRIVATE, null);

                    db.execSQL("CREATE TABLE IF NOT EXISTS person (_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR," +
                            "password VARCHAR, sex VARCHAR, age VARCHAR, count VARCHAR, ondown_count VARCHAR, onscroll_count VARCHAR, onzoom_count VARCHAR" +
                            "                                 ,everyac TEXT, maxac TEXT, minac TEXT," +
                            "                                 averac TEXT ,  stdac TEXT ,  everygy TEXT,  maxgy TEXT ,  mingy TEXT ,  avergy TEXT ," +
                            "                                 stdgy TEXT ,  everyvx TEXT,  maxvx TEXT ,  minvx TEXT ,  avervx TEXT ,  stdvx TEXT ," +
                            "                                 everyvy TEXT,  maxvy TEXT ,  minvy TEXT ,  avervy TEXT , stdvy TEXT , everypres TEXT," +
                            "                                 maxpres TEXT ,  minpres TEXT ,  averpres TEXT ,  stdpres TEXT ,  everysize TEXT,  maxsize TEXT ," +
                            "                                 minsize TEXT ,  aversize TEXT ,  stdsize TEXT ,  everyxa TEXT,  maxxa TEXT ,  minxa TEXT ," +
                            "                                 averxa TEXT ,  stdxa TEXT ,  everyxo TEXT,  maxxo TEXT ,  minxo TEXT ,  averxo TEXT ," +
                            "                                 stdxo TEXT ,  everyya TEXT,  maxya TEXT ,  minya TEXT ,  averya TEXT ,  stdya TEXT ," +
                            "                                 everyyo TEXT,  maxyo TEXT ,  minyo TEXT,  averyo TEXT ,  stdyo TEXT ,  everydx TEXT," +
                            "                                 maxdx TEXT ,  mindx TEXT ,  averdx TEXT ,  stddx TEXT ,  everydy TEXT,  maxdy TEXT ," +
                            "                                 mindy TEXT ,  averdy TEXT ,  stddy TEXT)");
                    //Data person = new Data(MainActivity.DataName, MainActivity.DataPsw);
                        /* person.putInData(MainActivity.sex, MainActivity.age, count, ondown_count, onfling_count,  onlongpress_count,
                         onshowpress_count, onscroll_count,  onsingletapup_count,  ondoubletapup_count,
                         ondoubletapevent_count,  onsingletapconfirm_count,  everyac, maxac, minac,
                                 averac ,  stdac ,  everygy,  maxgy ,  mingy ,  avergy ,
                                 stdgy ,  everyvx,  maxvx ,  minvx ,  avervx ,  stdvx ,
                                 everyvy,  maxvy ,  minvy ,  avervy , stdvy , everypres,
                                 maxpres ,  minpres ,  averpres ,  stdpres ,  everysize,  maxsize ,
                                 minsize ,  aversize ,  stdsize ,  everyxa,  maxxa ,  minxa ,
                                 averxa ,  stdxa ,  everyxo,  maxxo ,  minxo ,  averxo ,
                                 stdxo ,  everyya,  maxya ,  minya ,  averya ,  stdya ,
                                 everyyo,  maxyo ,  minyo,  averyo ,  stdyo ,  everydx,
                                 maxdx ,  mindx ,  averdx ,  stddx ,  everydy,  maxdy ,
                                 mindy ,  averdy ,  stddy );

                    }*/

                    ContentValues cv = new ContentValues();
                    cv.put("name", MainActivity.DataName);
                    cv.put("password", MainActivity.DataPsw);
                    cv.put("sex", MainActivity.sex);
                    cv.put("age", MainActivity.age);
                    cv.put("count", count);
                    cv.put("ondown_count", ondown_count);
                    cv.put("onscroll_count", onscroll_count);
                    cv.put("onzoom_count", onzoom_count);
                    cv.put("everyac", everyac);
                    cv.put("maxac", maxac);
                    cv.put("minac", minac);
                    cv.put("averac", averac);
                    cv.put("stdac", stdac);
                    cv.put("everygy", everygy);
                    cv.put("maxgy", maxgy);
                    cv.put("mingy", mingy);
                    cv.put("avergy", avergy);
                    cv.put("stdgy", stdgy);
                    cv.put("everyvx", everyvx);
                    cv.put("maxvx", maxvx);
                    cv.put("minvx", minvx);
                    cv.put("avervx", avervx);
                    cv.put("stdvx", stdvx);
                    cv.put("everyvy", everyvy);
                    cv.put("maxvy", maxvy);
                    cv.put("minvy", minvy);
                    cv.put("avervy", avervy);
                    cv.put("stdvy", stdvy);
                    cv.put("everypres", everypres);
                    cv.put("maxpres", maxpres);
                    cv.put("minpres", minpres);
                    cv.put("averpres", averpres);
                    cv.put("stdpres", stdpres);
                    cv.put("everysize", everysize);
                    cv.put("maxsize", maxsize);
                    cv.put("minsize", minsize);
                    cv.put("aversize", aversize);
                    cv.put("stdsize", stdsize);
                    cv.put("everyxa", everyxa);
                    cv.put("maxxa", maxxa);
                    cv.put("minxa", minxa);
                    cv.put("averxa", averxa);
                    cv.put("stdxa", stdxa);
                    cv.put("everyxo", everyxo);
                    cv.put("maxxo", maxxo);
                    cv.put("minxo", minxo);
                    cv.put("averxo", averxo);
                    cv.put("stdxo", stdxo);
                    cv.put("everyya", everyya);
                    cv.put("maxya", maxya);
                    cv.put("minya", minya);
                    cv.put("averya", averya);
                    cv.put("stdya", stdya);
                    cv.put("everyyo", everyyo);
                    cv.put("maxyo", maxyo);
                    cv.put("minyo", minyo);
                    cv.put("averyo", averyo);
                    cv.put("stdyo", stdyo);
                    cv.put("everydx", everydx);
                    cv.put("maxdx", maxdx);
                    cv.put("mindx", mindx);
                    cv.put("averdx", averdx);
                    cv.put("stddx", stddx);
                    cv.put("everydy", everydy);
                    cv.put("maxdy", maxdy);
                    cv.put("mindy", mindy);
                    cv.put("averdy", averdy);
                    cv.put("stddy", stddy);

                    db.insert("person", null, cv);
                }
                db.close();
                // clear the space for new data to enter
                ondown_count = 0;
                onscroll_count = 0;
                onzoom_count = 0;
                timetemp1 = 0;
                timetemp2 = 0;
                distanceY = 0;
                distanceX = 0;
                distanX1 = 0;
                distanY1 = 0;
                distanX2 = 0;
                distanY2 = 0;
                ac.clear();
                accc.clear();
                gy.clear();
                gyyy.clear();
                vx.clear();
                vy.clear();
                pres.clear();
                size.clear();
                x_start.clear();
                x_stop.clear();
                y_start.clear();
                y_stop.clear();
                dis_x.clear();
                dis_y.clear();
                flag = false;

            }

            pres.add(String.valueOf(event.getPressure()));
            size.add(String.valueOf(event.getSize()));

            /** 通过与运算保留最后八位 MotionEvent.ACTION_MASK = 255 */
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                // 手指压下屏幕
                case MotionEvent.ACTION_DOWN:
                    mode = MODE_Flick;
                    timetemp1 = System.currentTimeMillis();
                    x_start.add(String.valueOf(event.getX(0)));
                    y_start.add(String.valueOf(event.getY(0)));
                    distanX1 = event.getX(0) ;
                    distanY1 = event.getY(0) ;
                    ondown_count++;
                    break;
                // 手指在屏幕上移动，改事件会被不断触发
                case MotionEvent.ACTION_MOVE:
                    // 拖拉图片
                    if (mode == MODE_Flick) {
                        scrollDown();

                        timetemp2 = System.currentTimeMillis();
                        Log.i("wyhtimeflick", "time" + (timetemp2 - timetemp1));

                        x_stop.add(String.valueOf(event.getX(0)));
                        y_stop.add(String.valueOf(event.getY(0)));
                        distanX2 = event.getX(0) ;
                        distanY2 = event.getY(0) ;
                        distanceX = Math.abs(distanX1 - distanX2);
                        distanceY = Math.abs(distanY1 - distanY2);
                        dis_x.add(String.valueOf(distanceX));
                        dis_y.add(String.valueOf(distanceY));
                        vx.add(String.valueOf(distanceX / (timetemp2 - timetemp1)));
                        vy.add(String.valueOf(distanceY / (timetemp2 - timetemp1)));


                        onscroll_count++;


                        /*
                        float dx = event.getX() - startPoint.x; // 得到x轴的移动距离
                        float dy = event.getY() - startPoint.y; // 得到x轴的移动距离
                        // 在没有移动之前的位置上进行移动
                        matrix.set(currentMatrix);
                        matrix.postTranslate(dx, dy);
                        */
                    }
                    // 放大缩小图片
                    else if (mode == MODE_ZOOM) {
                        float endDis = distance(event);// 结束距离
                        timetemp2 = System.currentTimeMillis();
                        Log.i("wyhtime", "time" + (timetemp2 - timetemp1));
                        x_stop.add(String.valueOf(event.getX(0)));
                        y_stop.add(String.valueOf(event.getY(0)));
                        distanX2 = event.getX(0) ;
                        distanY2 = event.getY(0) ;

                        distanceX = Math.abs(distanX1 - distanX2);
                        distanceY = Math.abs(distanY1 - distanY2);
                        dis_x.add(String.valueOf(distanceX));
                        dis_y.add(String.valueOf(distanceY));
                        vx.add(String.valueOf(distanceX / (timetemp2 - timetemp1)));
                        vy.add(String.valueOf(distanceY / (timetemp2 - timetemp1)));
                        if (endDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                            float scale = endDis / startDis;// 得到缩放倍数
                            matrix.set(currentMatrix);
                            matrix.postScale(scale, scale,midPoint.x,midPoint.y);
                        }

                        onzoom_count++;


                    }
                    break;
                // 手指离开屏幕
                case MotionEvent.ACTION_UP:
                    Log.i("wyh","actionup");
                    // 当触点离开屏幕，但是屏幕上还有触点(手指)
                case MotionEvent.ACTION_POINTER_UP:
                    Log.i("wyh","actionup222");


                    mode = 0;
                    Log.i("wyh"," mode "+mode);
                    break;
                // 当屏幕上已经有触点(手指)，再有一个触点压下屏幕
                case MotionEvent.ACTION_POINTER_DOWN:

                    mode = MODE_ZOOM;
                    /** 计算两个手指间的距离 */
                    startDis = distance(event);
                    /** 计算两个手指间的中间点 */
                    if (startDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                        midPoint = mid(event);
                        //记录当前ImageView的缩放倍数
                        currentMatrix.set(imageView.getImageMatrix());
                    }
                    break;
            }
            imageView.setImageMatrix(matrix);
            return true;
        }

        /** 计算两个手指间的距离 */
        private float distance(MotionEvent event) {
            float dx = event.getX(1) - event.getX(0);
            float dy = event.getY(1) - event.getY(0);
            /** 使用勾股定理返回两点之间的距离 */
            return Float.parseFloat(String.valueOf(Math.sqrt(dx * dx + dy * dy)));
        }

        /** 计算两个手指间的中间点 */
        private PointF mid(MotionEvent event) {
            float midX = (event.getX(1) + event.getX(0)) / 2;
            float midY = (event.getY(1) + event.getY(0)) / 2;
            return new PointF(midX, midY);
        }

    }
    public void scrollDown()
    {
        mScrollView.post(new Runnable() {
            public void run() {
              //  mScrollView.fullScroll(View.FOCUS_RIGHT);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager2.registerListener(this,
                sensorManager2.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        sensorManager2.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch (event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:

                ac.add(getAcceleration(event)[0]);
                accc.add(getGyroscope(event)[1]);

                break;
            case Sensor.TYPE_GYROSCOPE:
                gy.add(getGyroscope(event)[0]);
                gyyy.add(getGyroscope(event)[1]);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = false;
                break;
            default:
                break;
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
