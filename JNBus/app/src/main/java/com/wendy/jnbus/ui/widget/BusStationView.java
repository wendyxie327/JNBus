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

import com.eagle.androidlib.utils.DensityUtil;
import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;

/**
 * Created by Wendy on 2016/12/18.
 */
public class BusStationView extends View {

    private Paint linePaint;
    private Paint ringPaint;

    private int lineColor = Color.BLUE;
    private int ringColor = Color.RED;
    private float radius = 10;
    private String position ;

    enum Position{
        LEFT, RIGHT , TOP
    }

    public BusStationView(Context context) {
        this(context, null ,0);
    }

    public BusStationView(Context context, AttributeSet attrs) {
        this(context, attrs ,0);
    }

    public BusStationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.BusStationView);//注意一定要传入attrs，否则该方法不起作用
        lineColor = array.getColor(R.styleable.BusStationView_lineColor, lineColor);
        ringColor = array.getColor(R.styleable.BusStationView_ringColor, ringColor);
        position = array.getString(R.styleable.BusStationView_linePosition);
        array.recycle();

        if (position == null || "".equals(position)){
            position = Position.LEFT.name();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        linePaint = new Paint();
        linePaint.setColor(lineColor);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(4);

        ringPaint = new Paint();
        ringPaint.setColor(ringColor);
        ringPaint.setStyle(Paint.Style.FILL);
        ringPaint.setAntiAlias(true);
        ringPaint.setColor(ringColor);

        switch (Position.valueOf(position)){

            case LEFT: //线在左方，圈在右

//                ringPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
                canvas.drawCircle(getX()+getWidth()- radius*4,getY(),radius,ringPaint);
                Logger.d("BusStation", "x="+(getX()+getWidth()- radius*4)+",x+width="+(getWidth()+getX())+",");
                canvas.drawLine(getX(),getY(),getX()+getWidth(),getY() ,linePaint);
                canvas.drawLine(getX(),getY()+100,getX()+getWidth(),getY()+100 ,linePaint);
                Logger.d("BusStationq", "x="+getX()+",x+width="+(getWidth()+getX())+",");
                break;

            case RIGHT:// 线在右，圈在左
                canvas.drawCircle(getX(),getY(),radius,ringPaint);
                canvas.drawLine(getX(),getY(),getX()+getWidth(),getY() ,linePaint);
                break;

            case TOP:// 线在上，圈在下
                canvas.drawLine(getX(),getY(),getX(),getWidth()+ getY() ,linePaint);
                canvas.drawCircle(getX(),getWidth() + getY(),radius,ringPaint);
                break;
        }

    }
}
