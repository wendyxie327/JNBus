package com.wendy.jnbus.ui.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.JNBusApplication;
import com.wendy.jnbus.ui.base.BaseAppFragment;
import com.wendy.jnbus.vo.BusDetail;
import com.wendy.jnbus.vo.BusStation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * 类描述：
 * 创建人：XieWQ
 * 创建时间：2017/5/23 0023 下午 16:23
 */
public class LineRoadFragment extends BaseAppFragment {

    private static final String TAG = "LineRoadFragment";

    @BindView(R.id.map_view)
    MapView mapView;

    private AMap aMap;
    private List<BusStation> busStations;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_lineroad;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mapView.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }

    @Override
    public void initDataCreate() {

    }

    @Override
    public void initDataResume() {
        mapView.onResume();
        showBusView(busStations);
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }


    public void showBusView(List<BusStation> busStations){
        Logger.d(TAG, "--------showBusView");
        this.busStations = busStations;

        if ( !this.isResumed()) {
            // 页面不显示时，不加载地图
            return;
        }


        aMap.clear();
        //TODO 显示线路图
        if (busStations == null || busStations.isEmpty()) return;
        List<LatLng> latLngs = new ArrayList<>();
        for (BusStation busStation : busStations) {
            latLngs.add(new LatLng(busStation.getLat(), busStation.getLng()));

            //TODO 显示线路各点上的车辆
            List<BusDetail> busDetails = busStation.getBusDetails();
            if (busDetails!=null && !busDetails.isEmpty()){
                for (BusDetail busDetail: busDetails) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(busDetail.getLat(), busDetail.getLng()));
                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.car_com)));
                    aMap.addMarker(markerOptions);
                }
            }
        }
        // 将地图显示中心拉至线路中间
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLngs.get( latLngs.size()/2), 14,0,0));
        aMap.moveCamera(cameraUpdate);

        aMap.addPolyline(
                new PolylineOptions().addAll(latLngs).width(8).color(ContextCompat.getColor(JNBusApplication.getContext(), R.color.colorPrimary)).geodesic(true));





    }
}
