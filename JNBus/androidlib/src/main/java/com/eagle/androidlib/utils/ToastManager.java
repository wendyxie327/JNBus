package com.eagle.androidlib.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Toast统一管理类
 * Created by Administrator on 2016/1/5 0005.
 */
public class ToastManager {

    public static Toast toast;
    public static ToastManager toastManager;
    public Context context;


    public ToastManager(Context context) {
        this.context = context;
    }


    /**
     * 获得单例模式的ToastManager
     *
     * @param context
     * @return
     */
    public static ToastManager getInstance(Context context) {
        if (toast == null) {
            toast = new Toast(context);
            toastManager = new ToastManager(context);
        }

        return toastManager;
    }


    /**
     * 使用单例模式的Toast，防止toast弹出框排列重复出现
     *
     * @param text
     */
    public void show(String text) {
        View view = Toast.makeText(context, text, Toast.LENGTH_SHORT).getView();
        toast.setView(view);
        toast.show();
    }
}
