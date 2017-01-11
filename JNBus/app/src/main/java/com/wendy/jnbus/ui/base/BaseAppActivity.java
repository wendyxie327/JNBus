package com.wendy.jnbus.ui.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.eagle.androidlib.baseUI.BaseActivity;
import com.wendy.jnbus.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public abstract class BaseAppActivity extends BaseActivity {

    protected Context context = this;


    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initToolbar(null);
    }


    /**
     * 设置标题栏
     *
     * @param titleText
     */
    public void initToolbar(String titleText) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (titleText !=null) toolbar.setTitle(titleText);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_bar_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(),R.mipmap.ic_bar_menu));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        if (titleText != null){
//            TextView titleTV = (TextView)findViewById(R.id.title_tv);
//            titleTV.setText(titleText);
//            titleTV.setTextColor(Color.WHITE);
//        }
    }
}
