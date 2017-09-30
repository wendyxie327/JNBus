package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;

/**
 * 类描述：
 * 创建人：XieWQ
 * 创建时间：2017/8/23 0023 下午 17:29
 */
public class ChangeBusStationLine extends View {

    private String busType ;// 类型：1起点，2终点，3其他
    private int radius = 10;    // 站点圆圈的半径
    private int lineWidth = 7;  // 单个线路的宽度

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
        int totalWidth = getWidth();
        int totalHeight = getHeight();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        paint.setStrokeWidth(1);
        canvas.drawCircle(totalWidth/2 , radius, radius, paint);
        paint.setStrokeWidth(lineWidth);
        canvas.drawLine(totalWidth/2 , radius*2, totalWidth/2, totalHeight/2, paint);
        paint.setStrokeWidth(1);
        Bitmap busBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.change_line_bus);
        canvas.drawBitmap(busBitmap, totalWidth/2 - busBitmap.getWidth()/2 , totalHeight/2, paint);
//        canvas.drawCircle(radius, totalHeight/2 + radius , radius, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
