package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.view.ViewGroup;

import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.vo.BusDetail;

import java.util.List;

/**
 *
 * 一个格上小车的显示
 * Created by Administrator on 2016/12/30 0030.
 */

public class BusViews extends ViewGroup {

    private static final String TAG = "BusViews";

    private List<BusDetail> busDetails;
    private String position ;
    private int stationWidth;
    private int busHeight = 30;
    private int busWidth = 70;

    public BusViews(Context context){
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int widthHalf = getWidth()/2;
        int heightHalf = getHeight()/2;
        int x1 =0, x2 = 0 ;
        int y1 =0, y2 = 0 ;
        switch (BusViewConstant.Position.valueOf(position)){
            case LEFT:
                y1 = heightHalf-stationWidth;y2 = heightHalf-stationWidth;
                break;
            case RIGHT:
                y1 = heightHalf - stationWidth;y2 = heightHalf - stationWidth;
                break;
            case TOP_LEFT:
                x1 = widthHalf  ;  x2 = widthHalf  ;
                break;
            case TOP_RIGHT:
                x1 = widthHalf ; x2 = widthHalf  ;
                break;
        }


        if ( busDetails !=null && busDetails.size()>0 ){
            for(int i=0; i< busDetails.size() ; i++){
                Logger.d(TAG,"cardId0="+busDetails.get(i).getCardId());
                BusView busView = new BusView(getContext(), busDetails.get(i).getCardId());
                busView.setCarId( busDetails.get(i).getCardId() );
                Logger.d(TAG,"cardId="+busDetails.get(i).getCardId());

                switch (BusViewConstant.Position.valueOf(position)){
                    case LEFT:
                        if ("1".equals( busDetails.get(i).getIsArrvLft())){ //未到达
                            busView.layout( 3*widthHalf/2, y1- busHeight, 3*widthHalf/2+ busWidth , y1);
                            y1 = y1-10;
                        }else {
                            busView.layout( widthHalf, y1- busHeight, widthHalf+busWidth , y1 );
                            y2 = y2-10;
                        }

                        break;
                    case RIGHT:
                        if ("1".equals( busDetails.get(i).getIsArrvLft())){
                            busView.layout(widthHalf/2 , y1- busHeight, widthHalf/2 + busWidth, y1);
                            y1 = y1-10;
                        }else {
                            busView.layout(widthHalf, y1- busHeight, widthHalf+busWidth , y1);
                            y2 = y2-10;
                        }

                        break;
                    case TOP_LEFT:
                        if ("1".equals( busDetails.get(i).getIsArrvLft())){
                            busView.layout( x1 , heightHalf/2- busHeight, x1+ busHeight, heightHalf/2);
                            x1 = x1+10;
                        }else {
                            busView.layout(x1, heightHalf - stationWidth - busHeight, x1+ busHeight, heightHalf-stationWidth);
                            x2 = x2+10;
                        }

                        break;
                    case TOP_RIGHT:
                        if ("1".equals( busDetails.get(i).getIsArrvLft())){
                            busView.layout( x1, heightHalf/2- busHeight , x1+ busHeight, heightHalf/2);
                            x1 = x1-10;
                        }else {
                            busView.layout(x1, heightHalf-stationWidth- busHeight, x1+ busHeight, heightHalf-stationWidth);
                            x2 = x2-10;
                        }

                        break;
                }

                Logger.d(TAG , "busView: width="+busView.getWidth()+",height="+busView.getHeight());
                addView(busView);
            }
        }
    }

    public int getBusHeight() {
        return busHeight;
    }

    public void setBusHeight(int busHeight) {
        this.busHeight = busHeight;
    }

    public int getStationWidth() {
        return stationWidth;
    }

    public void setStationWidth(int stationWidth) {
        this.stationWidth = stationWidth;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<BusDetail> getBusDetails() {
        return busDetails;
    }

    public void setBusDetails(List<BusDetail> busDetails) {
        this.busDetails = busDetails;
    }
}
