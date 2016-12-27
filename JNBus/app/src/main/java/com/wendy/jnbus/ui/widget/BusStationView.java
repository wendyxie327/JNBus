package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;

/**
 * Created by Wendy on 2016/12/18.
 */
public class BusStationView extends View {

    private static final String TAG = "BusStationView";

    private Paint linePaint;
    private Paint ringPaint;

    private int lineColor = Color.BLUE;
    private int ringColor = Color.RED;
    private float radius = 10;
    private float lineWidth = 7;
    private String position ;
    private boolean isFirst;


    public BusStationView(Context context) {
        this(context, null ,0);
    }

    public BusStationView(Context context, AttributeSet attrs) {
        this(context, attrs ,0);
    }

    public BusStationView(Context context, AttributeSet attrs, int defStyleAttr) {
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

        switch (BusViewConstant.Position.valueOf(position)){

            case RIGHT: //线在左方，圈在右 →
                Logger.d(TAG,"RIGHT");
                canvas.drawLine(0, radius,
                        getWidth(), radius,
                        linePaint);
                canvas.drawCircle(
                        getWidth()- radius,
                        radius,
                        radius, ringPaint);
                break;

            case LEFT://  ←
                Logger.d(TAG,"LEFT");
                if (!isFirst)   // 当不是第一个时，是有横线的
                    canvas.drawLine(0,radius,
                            getWidth(), radius,
                            linePaint);
                canvas.drawCircle(radius,
                        radius,
                        radius,
                        ringPaint);
                break;

            case TOP_LEFT:// 线在上，圈在下
                canvas.drawLine(radius,0,
                        radius,getHeight() ,
                        linePaint);
                canvas.drawCircle(radius,
                        getHeight()-radius,
                        radius,
                        ringPaint);
                break;
            case TOP_RIGHT:// 线在上，圈在下
                canvas.drawLine(radius,0,
                        radius,getHeight() ,
                        linePaint);
                canvas.drawCircle(radius,
                        getHeight()-radius,
                        radius,
                        ringPaint);
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

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
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
