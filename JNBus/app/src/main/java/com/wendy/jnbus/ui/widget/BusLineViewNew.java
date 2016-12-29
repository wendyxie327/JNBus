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
public class BusLineViewNew extends LinearLayout {


    private static final String TAG= "BusLineView";
    private List<BusStation> busStations;
    private int oneLineNum = 4 ; // 线上总共有几个点
    private int ringRadius = 10;
    private int lineWidth = 5;
    private int stationNameWidth = 70 ; // 站点名称控件宽度


    public BusLineViewNew(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BusLineViewNew(Context context, AttributeSet attrs, int defStyleAttr) {
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

        // 将屏幕分为一块块正方形
        int lineLength = getMeasuredWidth() / (oneLineNum+1) ; // 线路长度的两倍，包含站点直径
        int lineVerLength = lineLength + 30 ; // 上下走向的线路长度

        int widthNoPadding = (oneLineNum+1) *lineLength; //正式的控件宽度为此。因为上面相除时，可能有些余数被忽略掉了
        int padding = (getMeasuredWidth() - widthNoPadding)/2 ; //两边需要留出一定间隙

        Logger.d(TAG,"lineLength="+lineLength+",width="+widthNoPadding+",padding="+padding+",height="+getMeasuredHeight());

        int x = padding;
        int y = 0;
        int len = busStations.size();
        for (int i=0 ;i < 4 ; i++){
            BusContentView busStationView = new BusContentView(getContext());
            busStationView.setLineWidth(lineWidth);
            busStationView.setLineColor(R.color.cyan);
            busStationView.setRingColor(R.color.yellow);
            busStationView.setRadius(ringRadius);
            busStationView.setStationNameWidth(stationNameWidth);
            busStationView.setBusStation( busStations.get(i) );
            Logger.d(TAG,"i="+i+"---x="+x +",y="+y);

            if ( i/oneLineNum %2 ==0 ){ // 左↓ & →
                if ( i % oneLineNum == 0  ){ //第一个数，表示为 左↓
                    busStationView.setPosition(BusViewConstant.Position.TOP_LEFT.name());
                    if (i ==0 ) busStationView.setFirst(true);

                }else {
                    busStationView.setPosition( BusViewConstant.Position.RIGHT.name());

                }
                busStationView.layout(x, y , x+2*lineLength , y+2*lineLength );
                x = x + lineLength;    // 右移一个格
                Logger.d(TAG,"---i="+i+"---x="+( x+2*lineLength) +",y="+(y+2*lineLength));

            }else { // ↓右 & ←
                if ( i % oneLineNum == 0 ){ // 右↓
                    busStationView.setPosition(BusViewConstant.Position.TOP_RIGHT.name());

                }else {
                    busStationView.setPosition(BusViewConstant.Position.LEFT.name());

                }
                busStationView.layout( x-2*lineLength , y , x , y+2*lineLength);
                x = x - lineLength;
            }

            if ( i%oneLineNum == (oneLineNum-1) ){
                y = y+ lineLength ;//换行
            }
            Logger.d(TAG,"---i="+i+"---x="+x +",y="+y);

            addView(busStationView);// 将站点添加到view中
        }


    }
}
