package com.wendy.jnbus.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.wendy.jnbus.ui.JNBusApplication;

/**
 * Created by Wendy on 2016/12/17.
 */
public class BusShare {

    private static final String BUS_IP_DEFAULT = "60.216.101.229";
    private static final int VERSION_DEFAULT = 2319;
    private static final String AREA_DEFAULT = "370100";

    private static final String SHARE_NAME = "bus";

    private static final String KEY_AREA = "area";
    private static final String KEY_BUS_IP = "bus_ip";
    private static final String KEY_VERSION_CODE = "version_code";


    public static void setKeyBusIpAndArea(String ip, String area){
        SharedPreferences sharedPreferences = JNBusApplication.getContext().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(KEY_BUS_IP, ip).commit();
        sharedPreferences.edit().putString(KEY_AREA, area ).commit();
    }

    public static void setKeyVersionCode(int versionCode){
        SharedPreferences sharedPreferences = JNBusApplication.getContext().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(KEY_VERSION_CODE, versionCode).commit();
    }

    public static int getKeyVersionCode(){
        return JNBusApplication.getContext().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE).getInt(KEY_VERSION_CODE, VERSION_DEFAULT);
    }


    public static String getKeyBusIp(){
        return JNBusApplication.getContext().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE).getString(KEY_BUS_IP, BUS_IP_DEFAULT);
    }

    public static String getKeyArea(){
        return JNBusApplication.getContext().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE).getString(KEY_AREA, AREA_DEFAULT);
    }
}
