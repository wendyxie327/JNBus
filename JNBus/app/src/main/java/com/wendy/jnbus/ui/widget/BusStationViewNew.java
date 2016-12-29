package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.eagle.androidlib.utils.Logger;

/**
 * Created by Wendy on 2016/12/18.
 */
public class BusStationViewNew extends View {

    private static final String TAG = "BusStationViewNew";

    private Paint linePaint;
    private Paint ringPaint;

    private int lineColor = Color.BLUE;
    private int ringColor = Color.RED;
    private int radius = 10;    // 站点圆圈的半径
    private int lineWidth = 7;  // 单个线路的宽度
    private int lineLength = 64; // 单个线路的长度
    private String position ;   // 线路样式
    private boolean isFirst;


    public BusStationViewNew(Context context) {
        this(context, null ,0);
    }

    public BusStationViewNew(Context context, AttributeSet attrs) {
        this(context, attrs ,0);
    }

    public BusStationViewNew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 设置类型初始化
        position = BusViewConstant.Position.LEFT.name();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Logger.d(TAG,"----onDraw--start");
        linePaint = new Paint();
        linePaint.setColor(lineColor);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(lineWidth);

        ringPaint = new Paint();
        ringPaint.setColor(ringColor);
        ringPaint.setStyle(Paint.Style.FILL);
        ringPaint.setAntiAlias(true);
        ringPaint.setColor(ringColor);

        lineLength = getWidth()/2;
        canvas.drawCircle(  // 圆心在正中央
                lineLength,
                radius,
                radius, ringPaint);

        switch (BusViewConstant.Position.valueOf(position)){

            case RIGHT: //线在左方，圈在右 →
                Logger.d(TAG,"RIGHT");
                canvas.drawLine( 0, lineLength ,
                        lineLength, lineLength ,
                        linePaint);

                break;

            case LEFT://  ←
                Logger.d(TAG,"LEFT");
                canvas.drawLine( lineLength , lineLength,
                        getWidth() , lineLength,
                        linePaint);
                break;

            case TOP_LEFT:// 线在上，圈在下 - 左
            case TOP_RIGHT:// 线在上，圈在下 - 右 ，都执行同一个方法
                if (!isFirst)   // 当不是第一个时，是有横线的
                    canvas.drawLine( lineLength , 0,
                            lineLength , lineLength ,
                            linePaint);
                break;
        }

    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = ContextCompat.getColor(getContext(),lineColor);
    }

    public int getRingColor() {
        return ringColor;
    }

    public void setRingColor(int ringColor) {
        this.ringColor = ContextCompat.getColor(getContext(),ringColor);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }
}
