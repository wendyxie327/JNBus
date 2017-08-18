package com.wendy.jnbus.util;

import com.eagle.androidlib.utils.Logger;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * 类描述：
 * 创建人：XieWQ
 * 创建时间：2017/8/18 0018 下午 17:05
 */
public class StringUtil {

    private static String TAG = "StringUtil";

    /**
     * 将数字转换成 **.00 的格式，防止出现多位小数点
     *
     * @param num
     * @return
     */
    public static String parseDouble2Str(double num) {
        DecimalFormat df = new DecimalFormat("###0.00");
        return df.format(num);
    }

    /**
     * 解析距离
     * @param distance  单位md的距离
     * @return  千米/米
     */
    public static String parseDistance2Ch(long distance){
        if (distance > 1000 ){
            return parseDouble2Str(distance / 1000.00) +"千米";
        }else {
            return distance + "米";
        }
    }

    /**
     * 将时间转换成 n天n时n分钟n秒的格式
     * @param time  单位s的时间
     * @param timeType  Calendar的日期类型
     * @return  n天n时n分钟n秒
     */
    public static String parseTimeSecond2Ch(long time, int timeType){
        Logger.d(TAG, "time = " +time );
        if ( time == 0) {
            switch (timeType){
                case Calendar.SECOND:
                    return "0秒";
                case Calendar.MINUTE:
                    return "0分钟";
                case Calendar.HOUR_OF_DAY:
                    return "0时";
                case Calendar.DATE:
                    return "0天";
            }
        }

        int secondTime = 0 ;
        int minuteTime = 0;
        int hourTime = 0;
        int dayTime = 0;
        switch (timeType){
            case Calendar.SECOND:
                secondTime = Long.valueOf(time % 60).intValue() ;
            case Calendar.MINUTE:
                minuteTime = Long.valueOf(time /60 % 60).intValue();
            case Calendar.HOUR_OF_DAY:
                hourTime = Long.valueOf(time /60 / 60 %24 ).intValue();
            case Calendar.DATE:
                dayTime = Long.valueOf(time / (60*60*24)).intValue();
                break;
        }


        StringBuilder str = new StringBuilder();
        if (dayTime != 0){
            str.append(dayTime).append("天");
        }
        if (hourTime != 0){
            str.append(hourTime).append("时");
        }
        if (minuteTime != 0){
            str.append(minuteTime).append("分钟");
        }
        if (secondTime != 0){
            str.append(secondTime).append("秒");
        }
        return str.toString();
    }
}
