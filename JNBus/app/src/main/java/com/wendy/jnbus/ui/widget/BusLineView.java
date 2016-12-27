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

        Logger.d(TAG,"-----onLayout--start");

        if ( busStations==null){
            return;
        }

        int lineLength = (getMeasuredWidth()- ringRadius*2) / oneLineNum; // 某个站点的长度,总长度上，先减去边上圈圈
        int widthNoPadding = ringRadius*2 + oneLineNum *lineLength; //正式的控件宽度为此。因为上面相除时，可能有些余数被忽略掉了
        int padding = (getMeasuredWidth() - widthNoPadding)/2 ; //两边需要留出一定间隙

        Logger.d(TAG,"lineLength="+lineLength+",width="+widthNoPadding+",padding="+padding+",height="+getMeasuredHeight());

        int len = busStations.size();
        Logger.d(TAG,"-----onLayout-len="+busStations.size());
        for (int i=0 ;i < len -1 ; i++){
            BusStationView busStationView = new BusStationView(getContext());
            busStationView.setLineWidth(lineWidth);
            busStationView.setLineColor(R.color.cyan);
            busStationView.setRingColor(R.color.yellow);
            busStationView.setRadius(ringRadius);

            int iLineNum = i/ (oneLineNum +1) ;  //判断在第几行,一行=横行n个站点+竖行一个站点
            Logger.d(TAG,"-----onLayout-iLineNum="+iLineNum);
            // 一组横向，加一个竖为一组
            if (i ==0){ //第一个站点,只有一个点
                busStationView.setPosition(BusStationView.Position.LEFT.name());
                busStationView.setFirst(true);
                busStationView.layout(
                        padding,
                        0,
                        ringRadius*2 + padding,
                        ringRadius*2);

                Logger.d( TAG,"radius="+busStationView.getRadius());

            } else if ( i%(oneLineNum +1) == 0 ){    // ↓ 方向
                Logger.d(TAG,"bottom");
                busStationView.setPosition(BusStationView.Position.TOP.name());
                if ( iLineNum%2 == 0){  // 下划线在左边
                    busStationView.layout(
                            padding,
                            ringRadius*2 + (iLineNum-1)*lineLength,
                            padding + ringRadius*2 ,
                            ringRadius*2 + iLineNum*lineLength);
                }else { //下划线在右边
                    busStationView.layout(
                            widthNoPadding + padding - ringRadius*2 ,
                            ringRadius*2 + (iLineNum-1)*lineLength,
                            widthNoPadding + padding ,
                            ringRadius*2 + iLineNum*lineLength);
                }

            } else if (iLineNum % 2 == 0){ //单数表示向右 →
                Logger.d(TAG,"i="+i +",right");
                busStationView.setPosition(BusStationView.Position.RIGHT.name());
                busStationView.layout(
                        padding + lineLength*(i % (oneLineNum +1) -1) + ringRadius*2,
                        lineLength * iLineNum ,
                        padding + lineLength*(i % (oneLineNum +1) ) + ringRadius*2,
                        lineLength * iLineNum + ringRadius*2);

                Logger.d(TAG,"i="+i+",i % oneLineNum="+i % oneLineNum);
                Logger.d(TAG,
                        "x1="+(padding + lineLength*(i % (oneLineNum +1) -1) + ringRadius*2)
                        +",y1="+(lineLength * iLineNum )
                        +",x2="+(padding + lineLength*(i % (oneLineNum +1) ) + ringRadius*2)
                        +",y2="+(lineLength * iLineNum + ringRadius*2));

            }else if (iLineNum % 2 ==1 ){   //双数表示向左 ←
                Logger.d(TAG,"i="+i+",left");
                busStationView.setPosition(BusStationView.Position.LEFT.name());
                busStationView.setFirst(false);
                busStationView.layout(
                        padding + widthNoPadding - (padding + lineLength*(i % (oneLineNum +1) ) + ringRadius*2),
                        ringRadius*2 + iLineNum*lineLength - ringRadius,
                        padding + widthNoPadding-(padding + lineLength*(i % (oneLineNum +1) -1) + ringRadius*2),
                        ringRadius*2 + iLineNum*lineLength + ringRadius);

                Logger.d(TAG,"i="+i+",i % oneLineNum="+i % (oneLineNum +1));
                Logger.d(TAG,
                        "x1="+(padding + widthNoPadding - (padding + lineLength*(i % (oneLineNum +1) ) + ringRadius*2))
                                +",y1="+((iLineNum/2) * ringRadius*2 + iLineNum*lineLength +ringRadius)
                                +",x2="+( padding + widthNoPadding-(padding + lineLength*(i % (oneLineNum +1) -1) + ringRadius*2))
                                +",y2="+((iLineNum/2) * ringRadius*2 + iLineNum*lineLength -ringRadius));
            }

            addView(busStationView);

        }
    }
}
