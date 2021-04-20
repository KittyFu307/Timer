package com.example.a3;

public class TimeUtil {

    public static String getFormatHMS(long time){
        time=time/1000;//Total seconds
        int s= (int) (time%60);//second
        int m= (int) (time/60);//Minute
        int h=(int) (time/3600);//second
        return String.format("%02d:%02d:%02d",h,m,s);
    }
}
