package com.eagle.androidlib.utils;

import android.app.Service;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.TelephonyManager;


public class AppUtil {

    /**
     * 获取到cpu数目
     * @return
     */
    public static int getCpuNum(){
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context)
    {
        try
        {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context)
    {
        try
        {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取IMEI
     * @param context
     * @return
     */
    public static String getIMEI(Context context){
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 获取用户定位信息
     * @param context
     * @return
     * @throws SecurityException
     */
    public static Location getLocation(Context context) throws SecurityException{
        LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.GPS_PROVIDER;
        return locationManager.getLastKnownLocation(locationProvider);
    }


    /**
     * 获取安卓系统版本
     * @return
     */
    public static String getAndroidSysVersion(){
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     * @return
     */
    public static String getPhoneModel(){
        return  android.os.Build.MODEL;
    }

    /**
     * 获取版本号
     * @param context
     * @return  应用版本号
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
