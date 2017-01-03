package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;

/**
 * 站点车辆显示
 * Created by Administrator on 2016/12/28 0028.
 */

public class BusView extends RelativeLayout {

    private static final String TAG = "BusView";
    private String carId;
    private TextView carIdTV;

    public BusView(Context context,String carId) {
        this(context, null, 0);

    }

    public BusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.setBackground(ContextCompat.getDrawable(context,R.drawable.car1));

        carIdTV = new TextView(context);
        carIdTV.setText(carId);
        Logger.d(TAG,"---cardId="+carId);
        carIdTV.setTextColor(ContextCompat.getColor(context, R.color.red_a200));
        carIdTV.setTextSize(10);
        carIdTV.setGravity(Gravity.CENTER);
        LayoutParams carIdTVParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        carIdTVParams.addRule(CENTER_IN_PARENT);
        carIdTV.layout(0,0,getWidth(),getHeight());
        addView(carIdTV, carIdTVParams);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int count = this.getChildCount();

        for (int i = 0; i < count; i++) {
            View child = this.getChildAt(i);
            child.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean arg0, int l, int t, int r, int b) {

        int count = this.getChildCount();

        for (int i = 0; i < count; i++) {
            View child = this.getChildAt(i);
            child.setVisibility(View.VISIBLE);
            //child.measure(r-l, b-t);
            int x = 0;
            int y = 0;
            child.layout(x, y, x + getWidth(), y + getHeight()/2);
        }

    }

    public void setCarId(String carId) {
        this.carId = carId;
        carIdTV.setText(carId);
    }
}
