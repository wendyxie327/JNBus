package com.wendy.jnbus.ui.activity;

import android.content.Intent;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.base.BaseAppActivity;
import com.wendy.jnbus.util.amap.MapUtil;

import butterknife.OnClick;

/**
 * 车辆换乘
 * <p>
 * 需要完成的功能：定位/查询地址/展示换乘方案/查询地址反转/查询历史地点记录
 * Created by Wendy on 2017/7/27.
 */

public class ChangeBusActivity extends BaseAppActivity implements AMapLocationListener{

    private AMapLocationClient mLocationClient = null;
    private MapUtil mapUtil;

    @Override
    public int getLayoutID() {
        return R.layout.activity_change_bus;
    }

    @Override
    public void initBundle() {

    }

    @Override
    public void initDataCreate() {
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mapUtil = new MapUtil();
        mapUtil.startLocationOnce(this, mLocationClient);
        //TODO 适配6.0系统以上
    }

    @Override
    public void initDataResume() {

    }

    @OnClick(R.id.from_et)
    public void clickFromAddress() {
        startActivity(new Intent(this, SearchLocationActivity.class));
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null){
            Logger.d(context, " location = "+ aMapLocation.getAddress());
        }
    }
}
