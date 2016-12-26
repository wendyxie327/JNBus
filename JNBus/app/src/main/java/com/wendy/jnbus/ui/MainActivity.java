package com.wendy.jnbus.ui;

import com.eagle.androidlib.net.SubscriberOnNextListener;
import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.net.BusHttpMethod;
import com.wendy.jnbus.ui.base.BaseAppActivity;
import com.wendy.jnbus.ui.widget.BusLineView;
import com.wendy.jnbus.vo.BusDetail;
import com.wendy.jnbus.vo.BusLine;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseAppActivity {

    @BindView(R.id.bus_line_view)
    BusLineView busLineView;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initBundle() {

    }

    @Override
    public void initDataCreate() {

        // 根据线路，获取线路上走的车
        SubscriberOnNextListener<List<BusDetail>> buses = new SubscriberOnNextListener<List<BusDetail>>() {
            @Override
            public void onNext(List<BusDetail> busDetails) {
                if (busDetails !=null)
                    Logger.d(MainActivity.this, busDetails.toString());
                else
                    Logger.d(MainActivity.this, "busDetails is null");
            }
        };
        BusHttpMethod.queryBusDetail(MainActivity.this , buses, "164");


        // 根据线路，获取具体线路
        SubscriberOnNextListener<BusLine> busLineSub = new SubscriberOnNextListener<BusLine>() {
            @Override
            public void onNext(BusLine busLine) {
                if (busLine !=null){
                    Logger.d(MainActivity.this, busLine.toString());
                    busLineView.setBusStations( busLine.getStations());
                } else
                    Logger.d(MainActivity.this, "busLine is null");
            }
        };
        BusHttpMethod.queryBusLine(MainActivity.this , busLineSub, "163");
    }

    @Override
    public void initDataResume() {

    }
}
