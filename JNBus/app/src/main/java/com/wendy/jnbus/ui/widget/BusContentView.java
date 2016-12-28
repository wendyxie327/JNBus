package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.vo.BusDetail;

/**
 * 绘制每个站点的样子，包含站点显示，站点名称显示，车辆显示
 * Created by Administrator on 2016/12/27 0027.
 */

public class BusContentView extends ViewGroup {

    private static final String TAG = "BusView";

    private int lineColor = Color.BLUE;
    private int ringColor = Color.RED;
    private float radius = 10;
    private float lineWidth = 7;
    private String position ;
    private boolean isFirst;
    private BusDetail busDetail;
    private int stationNameWidth ; // 站点名称控件宽度

    public BusContentView(Context context){
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
        busStationView.layout(stationNameWidth, stationNameWidth, getWidth() - stationNameWidth, getHeight()- stationNameWidth);
        busStationView.setFirst(isFirst);
        busStationView.setPosition(position);
        addView(busStationView);

        TextView textView = new TextView(getContext());
        textView.setText("test");

        BusView busView = new BusView(getContext());
        busView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        float busViewHeightP = 1.0f/3; // 显示小车高度设置少 1/3，全显示太高了

        ImageView imageView1 = new ImageView(getContext());
        imageView1.setImageDrawable(getResources().getDrawable(R.drawable.car));

        switch (BusViewConstant.Position.valueOf(position)){
            case RIGHT:
                textView.layout( getWidth()-3*stationNameWidth/2, getHeight()-stationNameWidth , getWidth()-stationNameWidth/2,getHeight());
                busView.layout( getWidth()-3*stationNameWidth/2 , (int) (stationNameWidth*busViewHeightP), getWidth()-stationNameWidth/2 ,stationNameWidth);
                imageView1.layout(getWidth()/2 - stationNameWidth/2 , 0 , getWidth()/2 + stationNameWidth/2,stationNameWidth);
                break;
            case LEFT:
                textView.layout( stationNameWidth/2, getHeight()-stationNameWidth , 3*stationNameWidth/2,getHeight());
                busView.layout( stationNameWidth , (int) (stationNameWidth*busViewHeightP) , 2*stationNameWidth,stationNameWidth);
                imageView1.layout(getWidth()/2 , 0 , getWidth()/2+stationNameWidth,stationNameWidth);
                break;
            case TOP_LEFT:
                textView.layout( getWidth()- 3*stationNameWidth/2, getHeight()-stationNameWidth , getWidth()-stationNameWidth/2,getHeight());
                busView.layout( stationNameWidth/2, getHeight()-2*stationNameWidth- 2* ((int)radius) + (int) (stationNameWidth*busViewHeightP) ,
                        3*stationNameWidth/2, getHeight()- stationNameWidth- 2* ((int)radius));
                imageView1.layout( stationNameWidth/2, getHeight()/2-stationNameWidth , 3*stationNameWidth/2, getHeight()/2);
                break;
            case TOP_RIGHT:
                textView.layout( getWidth()- 3*stationNameWidth/2, getHeight()-stationNameWidth , getWidth()-stationNameWidth/2,getHeight());
                busView.layout( getWidth()-3*stationNameWidth/2, getHeight()-2*stationNameWidth- 2* ((int)radius)+(int) (stationNameWidth*busViewHeightP) ,
                        getWidth()-stationNameWidth/2, getHeight()- stationNameWidth- 2* ((int)radius));
                imageView1.layout( getWidth()-3*stationNameWidth/2, getHeight()/2-stationNameWidth , getWidth()-stationNameWidth/2, getHeight()/2);
                break;
        }

        addView(textView);

//        addView(imageView1);
        addView(busView);

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
