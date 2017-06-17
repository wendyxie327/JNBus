package com.wendy.jnbus.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eagle.androidlib.baseUI.BaseFragment;
import com.eagle.androidlib.widget.RefreshLayout;
import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.activity.LineBusActivity;
import com.wendy.jnbus.ui.base.BaseAppFragment;
import com.wendy.jnbus.ui.widget.BusLineView;
import com.wendy.jnbus.util.SystemUtil;
import com.wendy.jnbus.vo.BusLine;
import com.wendy.jnbus.vo.BusStation;

import java.util.List;

import butterknife.BindView;

/**
 * 类描述：
 * 创建人：XieWQ
 * 创建时间：2017/5/23 0023 下午 15:34
 */
public class LineBusFragment extends BaseAppFragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = "LineBusFragment";

    BusLineView busLineView;
    @BindView(R.id.content_ll)
    LinearLayout contentLL;

    @BindView(R.id.operation_time_tv)
    TextView operationTimeTV;
    @BindView(R.id.refresh_srl)
    RefreshLayout refreshLayout;

    private List<BusStation> busStations;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_linebus;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void initDataCreate() {

    }

    @Override
    public void initDataResume() {

    }

    @Override
    public void onRefresh() {
        stopRefresh();
        ((LineBusActivity)getActivity()).onRefreshLine();
    }


    private void stopRefresh(){
        if ( refreshLayout!=null && refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }
    }


    /**
     * 显示路线附带信息
     * @param busLine
     */
    public void showLineMsg(BusLine busLine){
        operationTimeTV.setText( SystemUtil.parseLineOperTimeStr(busLine.getOperationTime())
                +"（"+busLine.getTicketPrice()+"）");
    }

    /**
     * 显示线路图，包含车辆显示
     * @param busStations
     */
    public void showBusView(List<BusStation> busStations){

        this.busStations = busStations;

        if ( !this.isResumed()) return;
        if ( busLineView!=null){
            contentLL.removeView(busLineView);
        }
        busLineView = new BusLineView(getContext(),null);
        busLineView.setBusStations( busStations );
        LinearLayout.LayoutParams busParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        busLineView.setLayoutParams(busParam);
        contentLL.addView(busLineView, busParam);
    }
}
