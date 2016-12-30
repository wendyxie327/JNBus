package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.vo.BusDetail;
import com.wendy.jnbus.vo.BusStation;

import java.util.List;

/**
 * 绘制每个站点的样子，包含站点显示，站点名称显示，车辆显示
 * Created by Administrator on 2016/12/27 0027.
 */

public class BusContainView extends ViewGroup {

    private static final String TAG = "BusContainView";

    private int lineColor = Color.BLUE;
    private int ringColor = Color.RED;
    private int radius = 10;
    private int lineWidth = 7;
    private String position ;
    private boolean isFirst;
    private BusStation busStation;
    private int stationNameWidth ; // 站点名称控件宽度

    public BusContainView(Context context){
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        BusStationViewNew busStationView = new BusStationViewNew(getContext());
        busStationView.setLineWidth(lineWidth);
        busStationView.setLineColor(lineColor);
        busStationView.setRingColor(ringColor);
        busStationView.setRadius(radius);
        busStationView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        busStationView.layout(0, 0, getWidth(), getHeight());
        busStationView.setFirst(isFirst);
        busStationView.setPosition(position);
        addView(busStationView);

        if ( busStation!=null) Logger.d(TAG,busStation.toString());
        else Logger.d(TAG,"busStation is null");

        int lineLength = getWidth()/2;
        int lineHeight = getHeight() /2 ;// 竖向的长度

        TextView stationNameTV = new TextView(getContext());
        stationNameTV.setText(busStation.getStationName());
        stationNameTV.setTextColor(ContextCompat.getColor(getContext(),R.color.textColor));
        stationNameTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.text_note_small_size));
        stationNameTV.layout( lineLength , lineHeight , 3* lineLength/2, 3*lineHeight/2 );


        BusView busView2 = null;
        float busViewHeightP = 1.0f/3; // 显示小车高度设置少 1/3，全显示太高了

        BusView busView1 = null ;

        //显示站点车辆
        if ( busStation.getBusDetails()!=null && busStation.getBusDetails().size()>0){
            List<BusDetail> busDetails = busStation.getBusDetails();
            for (BusDetail busDetail: busDetails) {
                if ( "1".equals(busDetail.getIsArrvLft())){
                    // 未到达
                    busView1 = new BusView(getContext());
                    busView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
                    busView1.setCarId(busDetail.getCardId());
                    busView1.layout( lineHeight/2 , );
                }else {
                    // 已到达
                    busView2 = new BusView(getContext());
                    busView2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
                    busView2.setCarId(busDetail.getCardId());
                }
            }
        }


//        switch (BusViewConstant.Position.valueOf(position)){
//            case RIGHT:
////                stationNameTV.layout( getWidth()-3*stationNameWidth/2, getHeight()-stationNameWidth , getWidth()-stationNameWidth/2,getHeight());
//                if ( busView2 !=null)
//                busView2.layout( getWidth()-3*stationNameWidth/2 , (int) (stationNameWidth*busViewHeightP), getWidth()-stationNameWidth/2 ,stationNameWidth);
//                if ( busView1 !=null)
//                busView1.layout(getWidth()/2 - stationNameWidth/2 , 0 , getWidth()/2 + stationNameWidth/2,stationNameWidth);
//                break;
//            case LEFT:
////                stationNameTV.layout( stationNameWidth/2, getHeight()-stationNameWidth , 3*stationNameWidth/2,getHeight());
//                if ( busView2 !=null)
//                busView2.layout( stationNameWidth , (int) (stationNameWidth*busViewHeightP) , 2*stationNameWidth,stationNameWidth);
//                if ( busView1 !=null)
//                busView1.layout(getWidth()/2 , 0 , getWidth()/2+stationNameWidth,stationNameWidth);
//                break;
//            case TOP_LEFT:
////                stationNameTV.layout( getWidth()- 3*stationNameWidth/2, getHeight()-stationNameWidth , getWidth()-stationNameWidth/2,getHeight());
//                if ( busView2 !=null)
//                busView2.layout( stationNameWidth/2, getHeight()-2*stationNameWidth- 2* ((int)radius) + (int) (stationNameWidth*busViewHeightP) ,
//                        3*stationNameWidth/2, getHeight()- stationNameWidth- 2* ((int)radius));
//                if ( busView1 !=null)
//                busView1.layout( stationNameWidth/2, getHeight()/2-stationNameWidth , 3*stationNameWidth/2, getHeight()/2);
//                break;
//            case TOP_RIGHT:
////                stationNameTV.layout( getWidth()- 3*stationNameWidth/2, getHeight()-stationNameWidth , getWidth()-stationNameWidth/2,getHeight());
//                if ( busView2 !=null)
//                busView2.layout( getWidth()-3*stationNameWidth/2, getHeight()-2*stationNameWidth- 2* ((int)radius)+(int) (stationNameWidth*busViewHeightP) ,
//                        getWidth()-stationNameWidth/2, getHeight()- stationNameWidth- 2* ((int)radius));
//                if ( busView1 !=null)
//                busView1.layout( getWidth()-3*stationNameWidth/2, getHeight()/2-stationNameWidth , getWidth()-stationNameWidth/2, getHeight()/2);
//                break;
//        }

        addView(stationNameTV);
        if ( busView1 !=null)
        addView(busView1);
        if ( busView2 !=null) addView(busView2);

    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public int getRingColor() {
        return ringColor;
    }

    public void setRingColor(int ringColor) {
        this.ringColor = ringColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getLineWidth() {
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

    public BusStation getBusStation() {
        return busStation;
    }

    public void setBusStation(BusStation busStation) {
        this.busStation = busStation;
    }

    public int getStationNameWidth() {
        return stationNameWidth;
    }

    public void setStationNameWidth(int stationNameWidth) {
        this.stationNameWidth = stationNameWidth;
    }
}
