package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.eagle.androidlib.utils.DensityUtil;
import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.JNBusApplication;
import com.wendy.jnbus.vo.BusStation;

import java.util.List;

/**
 * Created by Wendy on 2016/12/18.
 */
public class BusLineView extends LinearLayout {


    private static final String TAG= "BusLineView";
    private List<BusStation> busStations;
    private int oneLineNum = 5 ; // 线上总共有几个点
    private int ringRadius = DensityUtil.dp2px(JNBusApplication.getContext(), 5);
    private int lineWidth = DensityUtil.dp2px(JNBusApplication.getContext(), 2);
    private int stationNameWidth = DensityUtil.dp2px(JNBusApplication.getContext(), 35)  ; // 站点名称控件宽度
    private int padding = DensityUtil.dp2px(JNBusApplication.getContext(), 35) ; //左右两遍距离最近站点的间距


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
        if ( busStations==null){
            return;
        }

        int width = widthMeasureSpec ;
        int height = heightMeasureSpec; //初始化控件宽高

        // 将屏幕分为一块块正方形,
        // 左右两个站点，左右间距会小一些，以padding为准
        int lineLength = (getMeasuredWidth() - 2*padding) / (oneLineNum-1) ; // 线路长度的两倍，包含站点直径
        int lineVerLength = lineLength + 30 ; // 上下走向的线路长度

        int widthNoPadding = (oneLineNum-1) *lineLength; //正式的控件宽度为此。因为上面相除时，可能有些余数被忽略掉了
        padding = (getMeasuredWidth() - widthNoPadding)/2 ; //两边需要留出一定间隙

        Logger.d(TAG,"lineLength="+lineLength+",width="+widthNoPadding+",padding="+padding+",height="+getMeasuredHeight());

        // 设置高度
        height = 2*padding ;
        int heightLineNum = busStations.size()/ oneLineNum ; //总共有几行
        height = height + heightLineNum * lineVerLength;
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if ( busStations==null){
            return;
        }

        // 将屏幕分为一块块正方形,
        // 左右两个站点，左右间距会小一些，以padding为准
        int lineLength = (getMeasuredWidth() - 2*padding) / (oneLineNum-1) ; // 线路长度的两倍，包含站点直径
        int lineVerLength = lineLength + 30 ; // 上下走向的线路长度

        int widthNoPadding = (oneLineNum-1) *lineLength; //正式的控件宽度为此。因为上面相除时，可能有些余数被忽略掉了
        padding = (getMeasuredWidth() - widthNoPadding)/2 ; //两边需要留出一定间隙

        Logger.d(TAG,"lineLength="+lineLength+",width="+widthNoPadding+",padding="+padding+",height="+getMeasuredHeight());

        int x = 0;
        int y = 0 ;
        int len = busStations.size();
        for (int i=0 ;i < len ; i++){
            BusContainView busStationView = new BusContainView(getContext());
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
                    if (i ==0 ) {
                        y = y - (lineVerLength - padding);
                        busStationView.setFirst(true);
                    }
                    busStationView.layout( padding-lineLength, y , padding+lineLength , y+ 2*lineVerLength );
                    x = padding;
                }else {
                    busStationView.setPosition( BusViewConstant.Position.RIGHT.name());
                    busStationView.layout(x, y , x+2*lineLength , y+2*lineVerLength );
                    x = x + lineLength;    // 右移一个格
                }

            }else { // ↓右 & ←
                if ( i % oneLineNum == 0 ){ // 右↓
                    busStationView.setPosition(BusViewConstant.Position.TOP_RIGHT.name());
                    busStationView.layout( widthNoPadding + padding - lineLength  , y , widthNoPadding + 2*padding + (lineLength-padding), y+2*lineVerLength);
                    x = widthNoPadding + padding ; // 初始化右侧第一个站点的位置
                }else {
                    busStationView.setPosition(BusViewConstant.Position.LEFT.name());
                    busStationView.layout( x-2*lineLength , y , x , y+2*lineVerLength);
                    x = x - lineLength;
                }
            }

            if ( i%oneLineNum == (oneLineNum-1) ){
                y = y+ lineVerLength ;//换行
            }
            Logger.d(TAG,"---i="+i+"---x="+x +",y="+y);

            addView(busStationView);// 将站点添加到view中
        }


    }
}
