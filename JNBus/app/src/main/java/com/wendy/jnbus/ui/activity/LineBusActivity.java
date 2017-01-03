package com.wendy.jnbus.ui.activity;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.eagle.androidlib.net.SubscriberOnNextListener;
import com.eagle.androidlib.utils.Logger;
import com.eagle.androidlib.utils.ToastManager;
import com.wendy.jnbus.R;
import com.wendy.jnbus.net.BusHttpMethod;
import com.wendy.jnbus.ui.base.BaseAppActivity;
import com.wendy.jnbus.ui.widget.BusLineView;
import com.wendy.jnbus.vo.BusDetail;
import com.wendy.jnbus.vo.BusLine;
import com.wendy.jnbus.vo.BusStation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LineBusActivity extends BaseAppActivity {

    private static final String TAG = "MainActivity";
    BusLineView busLineView;
    @BindView(R.id.content_ll)
    ScrollView contentLL;

    List<BusDetail> mBusDetails ;
    BusLine mBusLine;
    private String lineId;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initBundle() {
        lineId = getIntent().getStringExtra("lineId");
    }

    @Override
    public void initDataCreate() {

        if (lineId == null) ToastManager.getInstance(getApplicationContext()).show("错误");

        // 根据线路，获取线路上走的车
        SubscriberOnNextListener<List<BusDetail>> buses = new SubscriberOnNextListener<List<BusDetail>>() {
            @Override
            public void onNext(List<BusDetail> busDetails) {
                if (busDetails !=null){
                    mBusDetails = busDetails;
                    Logger.d(TAG,busDetails.toString());
                    parseBusLine();
                } else
                    Logger.d(LineBusActivity.this, "busDetails is null");
            }
        };
        BusHttpMethod.queryBusDetail(LineBusActivity.this , buses, lineId);


        // 根据线路，获取具体线路
        SubscriberOnNextListener<BusLine> busLineSub = new SubscriberOnNextListener<BusLine>() {
            @Override
            public void onNext(BusLine busLine) {
                if (busLine !=null){
                    Logger.d(LineBusActivity.this, busLine.toString());
                    mBusLine = busLine;
                    parseBusLine();
                } else
                    Logger.d(LineBusActivity.this, "busLine is null");
            }
        };
        BusHttpMethod.queryBusLine(LineBusActivity.this , busLineSub, lineId);
    }

    @Override
    public void initDataResume() {

    }

    /**
     * 将线路接口和车辆路线接口整合
     */
    private void parseBusLine(){
        if ( mBusLine!=null && mBusDetails!=null
                && mBusLine.getStations()!=null && mBusLine.getStations().size() >0 ){
            List<BusStation> busStations = mBusLine.getStations(); //获取到的站点列表
            int len = busStations.size();
            for (int i=0 ; i<len ; i++){
                List<BusDetail> busDetailsShow = new ArrayList<>(); //初始化站点包含的车辆列表
                if ( mBusDetails.size() <=0) continue;
                for (BusDetail busDetail: mBusDetails) { //循环遍历车辆列表，找出与某站点全部的车辆
                    if ( busDetail.getStationSeqNum()!=null && busDetail.getStationSeqNum().equals( busStations.get(i).getId())){
                        busDetailsShow.add( busDetail); //将符合条件的车辆加到列表中
                    }
                }
                busStations.get(i).setBusDetails(busDetailsShow);
            }
            showBusView(busStations);
        }
    }


    /**
     * 显示线路图
     * @param busStations
     */
    private void showBusView(List<BusStation> busStations){
        busLineView = new BusLineView(LineBusActivity.this,null);
        busLineView.setBusStations( busStations );
        LinearLayout.LayoutParams busParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        busLineView.setLayoutParams(busParam);
        contentLL.addView(busLineView, busParam);
    }
}
