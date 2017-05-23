package com.wendy.jnbus.ui.fragment;

import android.os.Bundle;

import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.base.BaseAppFragment;
import com.wendy.jnbus.vo.BusStation;

import java.util.List;

/**
 * 类描述：
 * 创建人：XieWQ
 * 创建时间：2017/5/23 0023 下午 16:23
 */
public class LineRoadFragment extends BaseAppFragment {

    private static final String TAG = "LineRoadFragment";

    @Override
    public int getLayoutID() {
        return R.layout.fragment_lineroad;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

    @Override
    public void initDataCreate() {

    }

    @Override
    public void initDataResume() {

    }

    public void showBusView(List<BusStation> busStations){
        Logger.d(TAG, "--------showBusView");
    }
}
