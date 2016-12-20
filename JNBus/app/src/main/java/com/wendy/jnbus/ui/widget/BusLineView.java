package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.eagle.androidlib.utils.DensityUtil;
import com.wendy.jnbus.R;
import com.wendy.jnbus.vo.BusStation;

import java.util.List;

/**
 * Created by Wendy on 2016/12/18.
 */
public class BusLineView extends LinearLayout {


    private List<BusStation> busStations;
    private int lineNum = 10; // 线上总共有几个点
    private int ringRadius = 10;


    public BusLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BusLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);



    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if ( busStations==null){
            return;
        }

        int lineLength = (getMeasuredWidth()- ringRadius*2) / lineNum; // 某个站点的长度,总长度上，先减去边上圈圈

        int len = busStations.size();
        for (int i=0;i<len ; i++){
            BusStationView busStationView = new BusStationView(getContext());
            busStationView.setLineWidth( 4);
            busStationView.setLineColor(R.color.cyan);
            busStationView.setRingColor(R.color.yellow);
            busStationView.setRadius(ringRadius);

            int iLineNum = i/ lineNum;
            // 一组横向，加一个竖为一组
            if (i ==0){ //第一个站点,只有一个点
                busStationView.setPosition(BusStationView.Position.LEFT.name());
                busStationView.setFirst(true);
                busStationView.layout(0, 0,,
                        DensityUtil.dp2px(getContext(),busStationView.getRadius())*2,
                        lineLength);
            } else if ( iLineNum == (lineNum-1) ){    // ↓ 方向
                busStationView.setPosition(BusStationView.Position.TOP.name());
                if ( iLineNum%2 == 0){  // 下划线在右边
                    busStationView.layout( lineNum*ringRadius,);
                }else { //下划线在左边

                }

            } else if (iLineNum % 2 == 0){
                busStationView.setPosition(BusStationView.Position.RIGHT.name());
            }else if (iLineNum % 2 ==1 ){
                busStationView.setPosition(BusStationView.Position.LEFT.name());
            }


        }
    }
}
