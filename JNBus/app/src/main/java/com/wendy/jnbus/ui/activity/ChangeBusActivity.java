package com.wendy.jnbus.ui.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.Tip;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.eagle.androidlib.baseUI.BaseListAdapter;
import com.eagle.androidlib.utils.Logger;
import com.eagle.androidlib.utils.ToastManager;
import com.wendy.jnbus.R;
import com.wendy.jnbus.config.PubInfo;
import com.wendy.jnbus.config.RequestActivityCode;
import com.wendy.jnbus.ui.adapter.ChangeBusAdapter;
import com.wendy.jnbus.ui.base.BaseAppActivity;
import com.wendy.jnbus.util.amap.MapUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * 车辆换乘查询列表
 * <p>
 * 需要完成的功能：
 * 1.定位/
 * 2.查询地址/
 * 3.展示换乘方案/
 * 4.查询地址反转/
 * 5.查询历史地点记录
 * Created by Wendy on 2017/7/27.
 *
 * TODO 屏幕变化，参数保存
 */

public class ChangeBusActivity extends BaseAppActivity implements AMapLocationListener, RouteSearch.OnRouteSearchListener {

    @BindView(R.id.change_address_btn)
    ImageButton changeAddressBtn;
    @BindView(R.id.from_et)
    TextView fromEt;
    @BindView(R.id.to_et)
    TextView toEt;
    @BindView(R.id.search_btn)
    Button searchBtn;
    @BindView(R.id.list_lv)
    ListView listLv;

    private AMapLocationClient mLocationClient = null;
    private MapUtil mapUtil;
    private RouteSearch routeSearch;
    private LatLonPoint fromPoint, toPoint;
    private AMapLocation aMapLocation;
    private ChangeBusAdapter changeBusAdapter;
    private BusRouteResult mBusRouteResult;// 查询路线结果

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
        routeSearch = new RouteSearch(context);
        routeSearch.setRouteSearchListener(this);
        changeBusAdapter = new ChangeBusAdapter(context);
        //TODO 适配6.0系统以上
    }

    @Override
    public void initDataResume() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RequestActivityCode.CHANGEBUS_SEARCHLOCATION_REQUEST_FROM:// 出发地点
                if (resultCode == RequestActivityCode.CHANGEBUS_SEARCHLOCATION_RESULT) {
                    Tip tip = data.getParcelableExtra("location");
                    fromPoint = tip.getPoint();
                    fromEt.setText(tip.getName());
                }
                break;
            case RequestActivityCode.CHANGEBUS_SEARCHLOCATION_REQUEST_TO:// 到达地点
                if (resultCode == RequestActivityCode.CHANGEBUS_SEARCHLOCATION_RESULT) {
                    Tip tip = data.getParcelableExtra("location");
                    toPoint = tip.getPoint();
                    toEt.setText(tip.getName());
                }
                break;
        }
    }




    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            Logger.d(context, " aMapLocation = " + aMapLocation.getAddress());
            this.aMapLocation = aMapLocation;
            if ("".equals(fromEt.getText().toString())){
                // 为防止定位过慢，当用户已经开始查询时，不再显示定位地点
                fromEt.setText(aMapLocation.getPoiName());
                fromPoint = new LatLonPoint(aMapLocation.getLatitude(),aMapLocation.getLongitude());
            }
        }
    }


    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
        if ( i != PubInfo.AMAP_REQUEST_SUCCESS_CODE){
            ToastManager.getInstance(getApplicationContext()).show("搜索时出现错误，请稍后再试");
            return;
        }

        // 展示结果
        changeBusAdapter.setItems(busRouteResult.getPaths());
        listLv.setAdapter(changeBusAdapter);
        mBusRouteResult = busRouteResult;
    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }


    /**
     * 选择出发地点
     */
    @OnClick(R.id.from_et)
    public void clickFromAddress() {
        startActivityForResult(new Intent(this, SearchLocationActivity.class), RequestActivityCode.CHANGEBUS_SEARCHLOCATION_REQUEST_FROM);
    }

    /**
     * 选择目的地
     */
    @OnClick(R.id.to_et)
    public void clickToAddress() {
        startActivityForResult(new Intent(this, SearchLocationActivity.class), RequestActivityCode.CHANGEBUS_SEARCHLOCATION_REQUEST_TO);
    }


    /**
     * 搜索
     */
    @OnClick(R.id.search_btn)
    public void clickSearchBtn(){
        if (fromPoint == null){
            ToastManager.getInstance(getApplicationContext()).show("请输入出发地点");
            return;
        }
        if (toPoint == null) {
            ToastManager.getInstance(getApplicationContext()).show("请输入目的地点");
            return;
        }
        searchLine(fromPoint, toPoint);
    }

    /**
     * 交换地址
     */
    @OnClick(R.id.change_address_btn)
    public void clickChangeAddressBtn(){
        // 交换地址 - 坐标
        LatLonPoint middle = null;
        if (fromPoint != null){
            middle = new LatLonPoint(fromPoint.getLatitude(), fromPoint.getLongitude());
        }
        if (toPoint != null){
            fromPoint = new LatLonPoint(toPoint.getLatitude(), toPoint.getLongitude());
        }

        if (middle != null){
            toPoint = new LatLonPoint(middle.getLatitude(), middle.getLongitude());
        }

        // 交换地址 - 显示内容
        String fromStr = fromEt.getText().toString();
        String toStr = toEt.getText().toString();
        fromEt.setText(toStr);
        toEt.setText(fromStr);
    }

    /**
     * 点击任意一行，都可以打开路线页面
     */
    @OnItemClick(R.id.list_lv)
    public void onListItemClick(){
        // 打开路线详情页面
        Intent intent = new Intent(context, ChangeBusDetailActivity.class);
        intent.putExtra("busRouteResult",mBusRouteResult );
        startActivity(intent);
    }


    /**
     * 查询路线
     *
     * @param from 开始坐标点
     * @param to   结束坐标点
     */
    private void searchLine(LatLonPoint from, LatLonPoint to) {
        RouteSearch.BusRouteQuery busRouteQuery = new RouteSearch.BusRouteQuery(
                new RouteSearch.FromAndTo(from, to), RouteSearch.BUS_LEASE_WALK, PubInfo.LOCATION_CITY_CODE, 1);
        routeSearch.calculateBusRouteAsyn(busRouteQuery);// 开始规划路线
    }

}
