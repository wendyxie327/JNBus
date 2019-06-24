package com.wendy.jnbus.ui.activity;

import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.eagle.androidlib.net.SubscriberOnNextListener;
import com.eagle.androidlib.utils.Logger;
import com.eagle.androidlib.utils.ToastManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wendy.jnbus.R;
import com.wendy.jnbus.net.BusHttpMethod;
import com.wendy.jnbus.ui.base.BaseAppActivity;
import com.wendy.jnbus.ui.fragment.LineBusFragment;
import com.wendy.jnbus.ui.fragment.LineRoadFragment;
import com.wendy.jnbus.vo.BusDetail;
import com.wendy.jnbus.vo.BusLine;
import com.wendy.jnbus.vo.BusStation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LineBusActivity extends BaseAppActivity {

    private static final String TAG = "LineBusActivity";

    @BindView(R.id.line_reverse_btn)
    FloatingActionButton lineReverseBtn;

    private List<BusDetail> mBusDetails ;
    private BusLine mBusLine;
    private BusLine mBusLineReverse; // 反方向线路
    private boolean isReverse; //正向false,反向true
    private boolean isRoadShow; //显示地图true, 不显示false
    private String lineId , stationName;
    private SubscriberOnNextListener<BusLine> busLineSub;// 根据线路，获取具体线路
    private SubscriberOnNextListener<List<BusDetail>> busesSub;// 根据线路，获取线路上走的车

    private LineBusFragment busFragment;
    private LineRoadFragment roadFragment;

    @Override
    public int getLayoutID() {
        return R.layout.activity_linebus;
    }

    @Override
    public void initBundle() {
        lineId = getIntent().getStringExtra("lineId");
        stationName = getIntent().getStringExtra("stationName");
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initToolbar(stationName+"路");
    }

    @Override
    public void initDataCreate() {
        if (lineId == null) {
            ToastManager.getInstance(getApplicationContext()).show("错误");
            return;
        }

        // 默认显示普通线路图
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        busFragment = new LineBusFragment();
        roadFragment = new LineRoadFragment();
        fragmentTransaction.add(R.id.content_frg, busFragment, "busFragment");
        fragmentTransaction.commit();

        // 根据线路，获取线路上走的车
        busesSub = new SubscriberOnNextListener<List<BusDetail>>() {
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

        // 根据线路，获取具体线路
        busLineSub = new SubscriberOnNextListener<BusLine>() {
            @Override
            public void onNext(BusLine busLine) {
                if (busLine !=null){
                    Logger.d(LineBusActivity.this, busLine.toString());
                    if ( isReverse){
                        mBusLineReverse = busLine;
                    }else {
                        mBusLine = busLine;
                    }
                    // 显示线路基本信息，并请求线路上车辆
                    lineId = busLine.getId();// 设置当前线路id
                    BusHttpMethod.queryBusDetail(LineBusActivity.this , busesSub, lineId);// 线路图获取完成后，请求线路上车辆
                    showLineMsg(busLine);
                } else
                    Logger.d(LineBusActivity.this, "busLine is null");
            }
        };

        // 获取线路图
        BusHttpMethod.queryBusLine(LineBusActivity.this , busLineSub, lineId);

    }

    @Override
    public void initDataResume() {
    }

    /**
     * 点击反转，求反方向内容
     * 将每次请求的线路
     */
    @OnClick(R.id.line_reverse_btn)
    public void clickReverseBtn(){
        isReverse = !isReverse;
        onRefreshLine();
    }

    @OnClick(R.id.road_reverse_btn)
    public void clickRoad(){
        isRoadShow = !isRoadShow;
        onRefreshRoad();
        Logger.d(TAG, "clickRoad");
    }

    /**
     * 刷新路线-刷新地图显示的路线
     */
    private void onRefreshRoad(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment( fragmentTransaction);
        if ( isRoadShow){
            if ( roadFragment.isAdded()){
                Logger.d(TAG, "roadFragment is add");
                fragmentTransaction.show( roadFragment);
            }else {
                Logger.d(TAG, "roadFragment is not add");
                fragmentTransaction.add(R.id.content_frg, roadFragment, "roadFragment");
                fragmentTransaction.show(roadFragment);
            }
        }else {
            if ( busFragment.isAdded()){
                Logger.d(TAG, "busFragment is add");
                fragmentTransaction.show( busFragment);
            }else {
                fragmentTransaction.add(R.id.content_frg, busFragment, "busFragment");
                fragmentTransaction.show(busFragment);
            }
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction){
        fragmentTransaction.hide( busFragment);
        fragmentTransaction.hide( roadFragment);
    }

    /**
     * 刷新路线-反向
     */
    public void onRefreshLine() {
        if (isReverse){
            if (mBusLineReverse==null ){
                BusHttpMethod.queryOtherBusLine(LineBusActivity.this , busLineSub, lineId);
            } else {
                lineId = mBusLineReverse.getId();// 设置当前线路id
                BusHttpMethod.queryBusDetail(LineBusActivity.this , busesSub, lineId);// 线路图获取完成后，请求线路上车辆
                showLineMsg(mBusLineReverse);
            }

        }else {
            if (mBusLine==null ){
                BusHttpMethod.queryBusLine(LineBusActivity.this , busLineSub, lineId);
            }else {
                lineId = mBusLine.getId();// 设置当前线路id
                BusHttpMethod.queryBusDetail(LineBusActivity.this , busesSub, lineId);// 线路图获取完成后，请求线路上车辆
                showLineMsg(mBusLine);
            }

        }
    }



    /**
     * 将线路接口和车辆路线接口整合
     */
    private void parseBusLine(){
        if ( ((mBusLine!=null && mBusLine.getStations()!=null && mBusLine.getStations().size() >0)
                ||(mBusLineReverse!=null && mBusLineReverse.getStations()!=null && mBusLineReverse.getStations().size() >0))
                && mBusDetails!=null ){

            List<BusStation> busStations ;
            if ( isReverse){
                busStations = mBusLineReverse.getStations();
            }else {
                busStations =  mBusLine.getStations(); //获取到的站点列表
            }

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
     * 显示线路图，包含车辆显示
     * @param busStations
     */
    private void showBusView(List<BusStation> busStations){
        if ( roadFragment!= null ){
            roadFragment.showBusView(busStations);
        }

        if ( busFragment != null ){
            busFragment.showBusView(busStations);
        }
    }

    /**
     * 显示路线其他相关信息，比如车辆运营时间
     * @param busLine
     */
    private void showLineMsg(BusLine busLine){
        if ( isReverse){
            lineReverseBtn.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.line_reverse1));
        }else {
            lineReverseBtn.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.line_reverse0));
        }

        if (busFragment!=null){
            busFragment.showLineMsg( busLine);
        }
    }
}
