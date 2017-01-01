package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.vo.BusStation;

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
        BusStationView busStationView = new BusStationView(getContext());
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


        //显示站点车辆
        BusViews busViews = new BusViews(getContext());
        busViews.setBusDetails(busStation.getBusDetails());
        busViews.setPosition(position);
        busViews.setStationWidth( radius);
        busViews.setBusHeight(50);//TODO
        busViews.layout(0,0,getWidth(),getHeight());

        addView(stationNameTV);
        addView( busViews);
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
