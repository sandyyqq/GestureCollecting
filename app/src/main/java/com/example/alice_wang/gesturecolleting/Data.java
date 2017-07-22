package com.example.alice_wang.gesturecolleting;

/**
 * Created by alice_wang on 16/8/10.
 */
import java.util.ArrayList;

public class Data {

    public String name;
    public String password;
    public String sex;
    public String age;

    public int action_count;
    public int ondown_count;
    public int onfling_count;
    public int onlongpress_count;
    public int onshowpress_count;
    public int onscroll_count;
    public int onsingletapup_count;
    public int ondoubletapup_count;
    public int ondoubletapevent_count;
    public int onsingletapconfirm_count;

    public String everyac;
    public String maxac ;//to store the max acceleration
    public String minac ;//to store the min acceleration
    public String averac ;//to store the average acceleration
    public String stdac ;//to store the standard acceleration
    public String everygy;
    public String maxgy ;//to store the max gyroscope
    public String mingy ;//to store the min gyroscope
    public String avergy ;//to store the average gyroscope
    public String stdgy ;//to store the standard gyroscope
    public String everyvx;
    public String maxvx ;//to store the max velocity_x
    public String minvx ;//to store the min velocity_x
    public String avervx ;//to store the average velocity_x
    public String stdvx ;//to store the standard velocity_x
    public String everyvy;
    public String maxvy ;//to store the max velocity_y
    public String minvy ;//to store the min velocity_y
    public String avervy ;//to store the average velocity_y
    public String stdvy ;//to store the standard velocity_y
    public String everypres;
    public String maxpres ;//to store the max pressure
    public String minpres ;//to store the min pressure
    public String averpres ;//to store the average pressure
    public String stdpres ;//to store the standard pressure
    public String everysize;
    public String maxsize ;//to store the max size
    public String minsize ;//to store the min size
    public String aversize ;//to store the average size
    public String stdsize ;//to store the standard size
    public String everyxa;
    public String maxxa ;//to store the max x_start
    public String minxa ;//to store the min x_start
    public String averxa ;//to store the average x_start
    public String stdxa ;//to store the standard x_start
    public String everyxo;
    public String maxxo ;//to store the max x_stop
    public String minxo ;//to store the min x_stop
    public String averxo ;//to store the average x_stop
    public String stdxo ;//to store the standard x_stop
    public String everyya;
    public String maxya ;//to store the max y_start
    public String minya ;//to store the min y_start
    public String averya ;//to store the average y_start
    public String stdya ;//to store the standard y_start
    public String everyyo;
    public String maxyo ;//to store the max y_stop
    public String minyo;//to store the min y_stop
    public String averyo ;//to store the average y_stop
    public String stdyo ;//to store the standard y_stop
    public String everydx;
    public String maxdx ;//to store the max dis_x
    public String mindx ;//to store the min dis_x
    public String averdx ;//to store the average dis_x
    public String stddx ;//to store the standard dis_x
    public String everydy;
    public String maxdy ;//to store the max dis_y
    public String mindy;//to store the min dis_y
    public String averdy ;//to store the average dis_y
    public String stddy ;//to store the standard dis_y



    public Data(String name, String password) {
        this.name = name;
        this.password=password;
    }

    public void putInData(String sex, String age, int action_count,int ondown_count, int onfling_count, int onlongpress_count,
                          int onshowpress_count,int onscroll_count, int onsingletapup_count, int ondoubletapup_count,
                          int ondoubletapevent_count, int onsingletapconfirm_count, String everyac,String maxac,String minac,
                          String averac , String stdac , String everygy, String maxgy , String mingy , String avergy ,
                          String stdgy , String everyvx, String maxvx , String minvx , String avervx , String stdvx ,
                          String everyvy, String maxvy , String minvy , String avervy ,String stdvy ,String everypres,
                          String maxpres , String minpres , String averpres , String stdpres , String everysize, String maxsize ,
                          String minsize , String aversize , String stdsize , String everyxa, String maxxa , String minxa ,
                          String averxa , String stdxa , String everyxo, String maxxo , String minxo , String averxo ,
                          String stdxo , String everyya, String maxya , String minya , String averya , String stdya ,
                          String everyyo, String maxyo , String minyo, String averyo , String stdyo , String everydx,
                          String maxdx , String mindx , String averdx , String stddx , String everydy, String maxdy ,
                          String mindy , String averdy , String stddy ){
        this.sex = sex;
        this.age = age;
        this.action_count = action_count;
        this.ondown_count = ondown_count;
        this.onfling_count = onfling_count;
        this.onlongpress_count = onlongpress_count;
        this.onshowpress_count = onshowpress_count;
        this.onscroll_count = onscroll_count;
        this.onsingletapup_count = onsingletapup_count;
        this.ondoubletapup_count = ondoubletapup_count;
        this.ondoubletapevent_count = ondoubletapevent_count;
        this.onsingletapconfirm_count = onsingletapconfirm_count;
        this.everyac = everyac;
        this.maxac = maxac;
        this.minac = minac;
        this.averac = averac;
        this.stdac = stdac;
        this.minac = minac;
        this.everygy = everygy;
        this.maxgy = maxgy;
        this.mingy = mingy;
        this.avergy = avergy;
        this.stdgy = stdgy;
        this.everyvx = everyvx;
        this.maxvx = maxvx;
        this.minvx = minvx;
        this.avervx = avervx;
        this.stdvx = stdvx;
        this.everyvy = everyvy;
        this.maxvy = maxvy;
        this.minvy = minvy;
        this.avervy = avervy;
        this.stdvy = stdvy;
        this.everypres = everypres;
        this.maxpres = maxpres;
        this.minpres = minpres;
        this.averpres = averpres;
        this.stdpres = stdpres;
        this.everysize = everysize;
        this.maxsize = maxsize;
        this.minsize = minsize;
        this.aversize = aversize;
        this.stdsize = stdsize;
        this.everyxa = everyxa;
        this.maxxa = maxxa;
        this.minxa = minxa;
        this.averxa = averxa;
        this.stdxa = stdxa;
        this.everyxo = everyxo;
        this.maxxo = maxxo;
        this.minxo = minxo;
        this.averxo = averxo;
        this.stdxo = stdxo;
        this.everyya = everyya;
        this.maxya = maxya;
        this.minya = minya;
        this.averya = averya;
        this.stdya = stdya;
        this.everyyo = everyyo;
        this.maxyo = maxyo;
        this.minyo = minyo;
        this.averyo = averyo;
        this.stdyo = stdyo;
        this.everydx = everydx;
        this.maxdx = maxdx;
        this.mindx = mindx;
        this.averdx = averdx;
        this.stddx = stddx;
        this.everydy = everydy;
        this.maxdy = maxdy;
        this.mindy = mindy;
        this.averdy = averdy;
        this.stddy = stddy;

    }
}