package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.vo.BusStation;

import java.util.List;

/**
 * Created by Wendy on 2016/12/18.
 */
public class BusLineView extends LinearLayout {


    private static final String TAG= "BusLineView";
    private List<BusStation> busStations;
    private int oneLineNum = 5 ; // 线上总共有几个点
    private int ringRadius = 10;
    private int lineWidth = 5;
    private int stationNameWidth = 50 ; // 站点名称控件宽度


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

        int lineUseWidth = getMeasuredWidth() - stationNameWidth;
        int lineLength = (lineUseWidth- ringRadius*2) / (oneLineNum); // 某个站点的长度,总长度上，先减去边上圈圈.为站点名称空出一个站点的长度
        int widthNoPadding = ringRadius*2 + oneLineNum *lineLength; //正式的控件宽度为此。因为上面相除时，可能有些余数被忽略掉了
        int padding = (lineUseWidth - widthNoPadding)/2 ; //两边需要留出一定间隙

        Logger.d(TAG,"lineLength="+lineLength+",width="+widthNoPadding+",padding="+padding+",height="+getMeasuredHeight());

        int len = busStations.size();
        for (int i=0 ;i < len -1 ; i++){
            BusView busStationView = new BusView(getContext());
            busStationView.setLineWidth(lineWidth);
            busStationView.setLineColor(R.color.cyan);
            busStationView.setRingColor(R.color.yellow);
            busStationView.setRadius(ringRadius);
            busStationView.setStationNameWidth(stationNameWidth);

            int iLineNum = i/ (oneLineNum +1) ;  //判断在第几行,一行=横行n个站点+竖行一个站点
            // 一组横向，加一个竖为一组
            if (i ==0){ //第一个站点,只有一个点
                busStationView.setPosition(BusViewConstant.Position.LEFT.name());
                busStationView.setFirst(true);
                busStationView.layout(
                        padding ,
                        0,
                        ringRadius*2 + padding + stationNameWidth,
                        ringRadius*2 + stationNameWidth);

            } else if ( i%(oneLineNum +1) == 0 ){    // ↓ 方向
                if ( iLineNum%2 == 0){  // 下划线在左边
                    busStationView.setPosition(BusViewConstant.Position.TOP_LEFT.name());
                    busStationView.layout(
                            padding ,
                            ringRadius*3 + (iLineNum-1)*lineLength, // 正常情况下为（ringRadius*3 + (iLineNum-1)*lineLength），为防止线路突出，减少半个站点半径
                            padding + ringRadius*2 + stationNameWidth,
                            ringRadius*2 + iLineNum*lineLength + stationNameWidth);
                }else { //下划线在右边
                    busStationView.setPosition(BusViewConstant.Position.TOP_RIGHT.name());
                    busStationView.layout(
                            widthNoPadding + padding - ringRadius*2  ,
                            ringRadius*2 + (iLineNum-1)*lineLength,
                            widthNoPadding + padding + stationNameWidth,
                            ringRadius*3 + iLineNum*lineLength+ stationNameWidth);// 正常情况下为（ringRadius*3 + (iLineNum-1)*lineLength），为防止线路突出，增加半个站点半径
                }

            } else if (iLineNum % 2 == 0){ //单数表示向右 →
                busStationView.setPosition(BusViewConstant.Position.RIGHT.name());
                busStationView.layout(
                        padding + lineLength*(i % (oneLineNum +1) -1) + ringRadius*2 ,
                        lineLength * iLineNum ,
                        padding + lineLength*(i % (oneLineNum +1) ) + ringRadius*2 + stationNameWidth,
                        lineLength * iLineNum + ringRadius*2 + stationNameWidth);

            }else if (iLineNum % 2 ==1 ){   //双数表示向左 ←
                busStationView.setPosition(BusViewConstant.Position.LEFT.name());
                busStationView.setFirst(false);
                busStationView.layout(
                        padding + widthNoPadding - (padding + lineLength*(i % (oneLineNum +1) ) + ringRadius*2) ,
                        ringRadius*2 + iLineNum*lineLength - ringRadius,
                        padding + widthNoPadding-(padding + lineLength*(i % (oneLineNum +1) -1) + ringRadius*2) + stationNameWidth,
                        ringRadius*2 + iLineNum*lineLength + ringRadius + stationNameWidth);
            }

            addView(busStationView);// 将站点添加到view中
        }


    }
}
