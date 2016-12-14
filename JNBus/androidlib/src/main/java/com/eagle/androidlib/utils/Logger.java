package com.eagle.androidlib.utils;

import android.content.Context;
import android.util.Log;

/**
 * Log统一管理类
 */
public class Logger {

    private Logger() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化

    // 下面四个是默认tag的函数
    public static void i(Context context, String msg) {
        if (isDebug)
            Log.i(context.getClass().getName(), msg);
    }

    public static void d(Context context,String msg) {
        if (isDebug)
            Log.d(context.getClass().getName(), msg);
    }

    public static void e(Context context,String msg) {
        if (isDebug)
            Log.e(context.getClass().getName(), msg);
    }

    public static void v(Context context,String msg) {
        if (isDebug)
            Log.v(context.getClass().getName(), msg);
    }

    public static void w(Context context,String msg){
        if (isDebug){
            Log.w(context.getClass().getName(),msg);
        }
    }


    public static void i(String context, String msg) {
        if (isDebug)
            Log.i(context, msg);
    }

    public static void d(String context,String msg) {
        if (isDebug)
            Log.d(context, msg);
    }

    public static void e(String context,String msg) {
        if (isDebug)
            Log.e(context, msg);
    }

    public static void v(String context,String msg) {
        if (isDebug)
            Log.v(context, msg);
    }

    public static void w(String context,String msg){
        if (isDebug){
            Log.w(context,msg);
        }
    }

}