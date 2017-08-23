package com.wendy.jnbus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusStep;
import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.base.BaseAppFragment;
import com.wendy.jnbus.util.StringUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 类描述：
 * 创建人：XieWQ
 * 创建时间：2017/8/23 0023 上午 11:05
 */
public class ChangeBusDetailFragment extends BaseAppFragment {

    private static final String TAG = "ChangeBusDetailFragment";
    @BindView(R.id.path_tv)
    TextView pathTv;
    @BindView(R.id.cost_tv)
    TextView costTv;

    private BusPath busPath;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_change_bus_detail;
    }

    public void initBundle() {
        busPath = getArguments().getParcelable("busPath");
    }

    @Override
    public void initDataCreate() {
        initBundle();
        // 显示路线
        StringBuilder pathStr = new StringBuilder("");
        for (BusStep busStep : busPath.getSteps()) {
            if (busStep.getEntrance()!= null) Logger.d(TAG, "entrance = "+busStep.getEntrance().getName());
            if (busStep.getExit() != null) Logger.d(TAG, "exit = "+ busStep.getExit().getName());
            if (busStep.getWalk() != null) Logger.d(TAG, "destination = "+busStep.getWalk().getDistance()+"");
            if (busStep.getBusLines() != null && busStep.getBusLines().size() > 0) {
                Logger.d(TAG, "buslines is not null");
                for (BusLineItem busLineItem : busStep.getBusLines()) {
                    if (busLineItem != null && busLineItem.getBusLineName() != null) {
                        Logger.d(TAG, "busLineItem is not null");
                        String busLineName = busLineItem.getBusLineName();
                        Logger.d(TAG,"busLineName ="+ busLineName +", "+ busLineItem.getOriginatingStation() +","+ busLineItem.getTerminalStation());
//                        if (busLineName.contains("(")) {// 去掉公交描述
//                            busLineName = busLineName.substring(0, busLineName.indexOf("("));
//                        }
//                        if (busLineName.contains("/")) {// 去掉相同路线展示
//                            busLineName = busLineName.substring(0, busLineName.indexOf("/"));
//                        }
                        pathStr.append(busLineName).append(" → ");
                    }
                }
            }else {
                Logger.d(TAG, "buslines is  null");
            }
        }
        pathTv.setText(pathStr.toString().substring(0, pathStr.toString().lastIndexOf("→")));

        // 显示：时长、金额、步行距离
        StringBuilder contentStr = new StringBuilder();
        contentStr.append(StringUtil.parseTimeSecond2Ch(busPath.getDuration(), Calendar.MINUTE)).append("   ")
                .append(busPath.getCost()).append("元").append("   ")
                .append("步行").append(StringUtil.parseDistance2Ch((long) busPath.getWalkDistance()));
        costTv.setText(contentStr.toString());
    }

    @Override
    public void initDataResume() {

    }

}
