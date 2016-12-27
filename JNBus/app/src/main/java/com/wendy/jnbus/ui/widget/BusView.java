package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.vo.BusDetail;

/**
 * Created by Administrator on 2016/12/27 0027.
 */

public class BusView extends ViewGroup {

    private static final String TAG = "BusView";

    private int lineColor = Color.BLUE;
    private int ringColor = Color.RED;
    private float radius = 10;
    private float lineWidth = 7;
    private String position ;
    private boolean isFirst;
    private BusDetail busDetail;
    private int stationNameWidth ; // 站点名称控件宽度

    public BusView(Context context){
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        BusStationView busStationView = new BusStationView(getContext());
        busStationView.setLineWidth(lineWidth);
        busStationView.setLineColor(lineColor);
        busStationView.setRingColor(ringColor);
        busStationView.setRadius(radius);
        busStationView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        busStationView.layout(0, 0, getWidth() - stationNameWidth, getHeight()- stationNameWidth);
        busStationView.setFirst(isFirst);
        busStationView.setPosition(position);
        addView(busStationView);

        TextView textView = new TextView(getContext());
        textView.setText("test");

        switch (BusViewConstant.Position.valueOf(position)){
            case RIGHT:
                textView.layout( getWidth()-stationNameWidth, getHeight()-stationNameWidth , getWidth(),getHeight());
                break;
            case LEFT:
                textView.layout( 0, getHeight()-stationNameWidth , stationNameWidth,getHeight());
                break;
            case TOP_LEFT:
                textView.layout( getWidth()-stationNameWidth, getHeight()-stationNameWidth , getWidth(),getHeight());
                break;
            case TOP_RIGHT:
                textView.layout( getWidth()-stationNameWidth, getHeight()-stationNameWidth , getWidth(),getHeight());
                break;
        }

        addView(textView);

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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
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

    public BusDetail getBusDetail() {
        return busDetail;
    }

    public void setBusDetail(BusDetail busDetail) {
        this.busDetail = busDetail;
    }

    public int getStationNameWidth() {
        return stationNameWidth;
    }

    public void setStationNameWidth(int stationNameWidth) {
        this.stationNameWidth = stationNameWidth;
    }
}
