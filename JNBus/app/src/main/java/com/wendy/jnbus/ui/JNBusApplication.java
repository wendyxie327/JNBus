package com.wendy.jnbus.ui;

import android.app.Application;
import android.content.Context;

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
    }

    public static Context getContext() {
        return context;
    }

}
