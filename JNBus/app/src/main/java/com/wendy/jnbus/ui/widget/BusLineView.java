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


    private static final String TAG= "BusLineView";
    private List<BusStation> busStations;
    private int lineNum = 10; // 线上总共有几个点
    private int ringRadius = 10;


    public BusLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BusLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBusStations(List<BusStation> busStations) {
        this.busStations = busStations;
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
        int width = ringRadius*2 + lineNum*lineLength; //正式的控件宽度为此。因为上面相除时，可能有些余数被忽略掉了
        int padding = (getMeasuredWidth() - width)/2 ; //两边需要留出一定间隙

        int len = busStations.size();
        for (int i=0;i < len + 1; i++){
            BusStationView busStationView = new BusStationView(getContext());
            busStationView.setLineWidth( 4);
            busStationView.setLineColor(R.color.cyan);
            busStationView.setRingColor(R.color.yellow);
            busStationView.setRadius(ringRadius);

            int iLineNum = i/ (lineNum+1) ;  //判断在第几行
            // 一组横向，加一个竖为一组
            if (i ==0){ //第一个站点,只有一个点
                busStationView.setPosition(BusStationView.Position.LEFT.name());
                busStationView.setFirst(true);
                busStationView.layout(
                        padding,
                        0,
                        DensityUtil.dp2px(getContext(),busStationView.getRadius())*2 + padding,
                        DensityUtil.dp2px(getContext(),busStationView.getRadius())*2);

            } else if ( iLineNum == (lineNum-1) ){    // ↓ 方向
                busStationView.setPosition(BusStationView.Position.TOP.name());
                if ( iLineNum%2 == 0){  // 下划线在右边
                    busStationView.layout(
                            width + padding,
                            (iLineNum+1) * ringRadius*2 + (iLineNum-1)*lineLength,
                            width + padding + ringRadius*2 ,
                            (iLineNum+1) * ringRadius*2 + iLineNum*lineLength);
                }else { //下划线在左边
                    busStationView.layout(
                            padding,
                            (iLineNum+1) * ringRadius*2 + (iLineNum-1)*lineLength,
                            padding + ringRadius*2 ,
                            (iLineNum+1) * ringRadius*2 + iLineNum*lineLength);
                }

            } else if (iLineNum % 2 == 0){ //单数表示向右 →
                busStationView.setPosition(BusStationView.Position.RIGHT.name());
                busStationView.layout(
                        padding + width*(i/ 2 % lineNum) + ringRadius,
                        width * iLineNum ,
                        padding + width*(i/ 2 % lineNum + 1) + ringRadius,
                        width * iLineNum + ringRadius*2);
            }else if (iLineNum % 2 ==1 ){   //双数表示向左 ←
                busStationView.setPosition(BusStationView.Position.LEFT.name());
                busStationView.layout(
                        padding + width*(i/ 2 % lineNum) + ringRadius,
                        getMeasuredWidth() - padding - 2*ringRadius - (i/2%lineNum-1)*lineLength ,
                        padding + width*(i/ 2 % lineNum + 1) + ringRadius,
                        getMeasuredWidth() - padding - 2*ringRadius - (i/2%lineNum)*lineLength);
            }


        }
    }
}
