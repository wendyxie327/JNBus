package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.wendy.jnbus.R;

/**
 * 类描述：
 * 创建人：XieWQ
 * 创建时间：2017/8/23 0023 下午 17:29
 */
public class ChangeBusStationLine extends View {

    private String busType ;// 类型：1起点，2终点，3其他

    public ChangeBusStationLine(Context context) {
        super(context);
    }

    public ChangeBusStationLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChangeBusStationLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
