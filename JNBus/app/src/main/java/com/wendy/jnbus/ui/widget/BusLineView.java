package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wendy on 2016/12/18.
 */
public class BusLineView extends View{
    public BusLineView(Context context) {
        this(context , null ,0);
    }

    public BusLineView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BusLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int rx = 5; //半径
        int rxNum = 4; //站点有几个

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
