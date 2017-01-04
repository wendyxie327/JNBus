package com.wendy.jnbus.ui.activity;

import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
import butterknife.OnClick;

public class LineBusActivity extends BaseAppActivity {

    private static final String TAG = "MainActivity";
    BusLineView busLineView;
    @BindView(R.id.content_ll)
    ScrollView contentLL;
    @BindView(R.id.line_reverse_btn)
    ImageButton lineReverseBtn;
    @BindView(R.id.operation_time_tv)
    TextView operationTimeTV;

    private List<BusDetail> mBusDetails ;
    private BusLine mBusLine;
    private BusLine mBusLineReverse; // 反方向线路
    private boolean isReverse; //正向false,反向true
    private String lineId;
    private SubscriberOnNextListener<BusLine> busLineSub;// 根据线路，获取具体线路
    private SubscriberOnNextListener<List<BusDetail>> busesSub;// 根据线路，获取线路上走的车

    @Override
    public int getLayoutID() {
        return R.layout.activity_linebus;
    }

    @Override
    public void initBundle() {
        lineId = getIntent().getStringExtra("lineId");
    }

    @Override
    public void initDataCreate() {

        if (lineId == null) ToastManager.getInstance(getApplicationContext()).show("错误");

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
        if (isReverse){ //当前为反向
            isReverse = !isReverse;
            if (mBusLine==null ){
                BusHttpMethod.queryBusLine(LineBusActivity.this , busLineSub, lineId);
            }else {
                lineId = mBusLine.getId();// 设置当前线路id
                BusHttpMethod.queryBusDetail(LineBusActivity.this , busesSub, lineId);// 线路图获取完成后，请求线路上车辆
                showLineMsg(mBusLine);
            }

        }else { // 当前正向
            isReverse = !isReverse;
            if (mBusLineReverse==null ){
                BusHttpMethod.queryOtherBusLine(LineBusActivity.this , busLineSub, lineId);
            } else {
                lineId = mBusLineReverse.getId();// 设置当前线路id
                BusHttpMethod.queryBusDetail(LineBusActivity.this , busesSub, lineId);// 线路图获取完成后，请求线路上车辆
                showLineMsg(mBusLineReverse);
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

    private void showLineMsg(BusLine busLine){
        operationTimeTV.setText( busLine.getTicketPrice() +"\n"+ busLine.getOperationTime());
    }


    /**
     * 显示线路图
     * @param busStations
     */
    private void showBusView(List<BusStation> busStations){
        if ( busLineView!=null){
            contentLL.removeView(busLineView);
        }
        busLineView = new BusLineView(LineBusActivity.this,null);
        busLineView.setBusStations( busStations );
        LinearLayout.LayoutParams busParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        busLineView.setLayoutParams(busParam);
        contentLL.addView(busLineView, busParam);
    }
}
