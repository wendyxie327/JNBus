package com.eagle.androidlib.baseUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/3/29 0029.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());

        initBundle();
        initView(savedInstanceState);
        initDataCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDataResume();
    }

    /**
     * 设置本界面layoutId
     * @return
     */
    public abstract int getLayoutID();


    /**
     * 获取上个界面传来的信息
     */
    public abstract void initBundle();

    /**
     * 初始化界面控件
     * @param savedInstanceState
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * 在onCreate方法中初始化数据
     * 如果一些不及时的消息可在此处联网获取
     */
    public abstract void initDataCreate();

    /**
     * 在OnResume方法中初始化数据
     * 及时更新的数据，在此处联网获取
     */
    public abstract void initDataResume();

    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
