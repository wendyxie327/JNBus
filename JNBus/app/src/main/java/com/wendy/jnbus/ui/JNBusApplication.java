package com.wendy.jnbus.ui;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Wendy on 2016/12/17.
 */
public class JNBusApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
    }

    public static Context getContext() {
        return context;
    }

}
