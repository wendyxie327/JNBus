package com.wendy.jnbus.ui.fragment;

import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
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
    TextureMapView mapView;

    private BaiduMap aMap;
    private List<BusStation> busStations;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_lineroad;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        if (aMap == null) {
            aMap = mapView.getMap();
            //普通地图
            aMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            mapView.showScaleControl(false);// 不显示比例尺
            mapView.setZoomControlsPosition(new Point(0, 0)); // 设置缩放比例位置
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
        // 显示线路图
        if (busStations == null || busStations.isEmpty()) return;
        List<LatLng> latLngs = new ArrayList<>();
        for (BusStation busStation : busStations) {
            latLngs.add(new LatLng(busStation.getLat(), busStation.getLng()));

            // 显示线路各点上的车辆
            List<BusDetail> busDetails = busStation.getBusDetails();
            CoordinateConverter converter  = new CoordinateConverter();
            converter.from(CoordinateConverter.CoordType.COMMON);
            if (busDetails!=null && !busDetails.isEmpty()){
                for (BusDetail busDetail: busDetails) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    LatLng sourceLatLng = new LatLng(busDetail.getLat(), busDetail.getLng());
                    // sourceLatLng待转换坐标
                    converter.coord(sourceLatLng);
                    markerOptions.position(converter.convert());// 将坐标改为高德地图坐标
                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.car_com)));
                    aMap.addOverlay(markerOptions);
                }
            }
        }
        // 将地图显示中心拉至线路中间
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLngs.get( latLngs.size()/2), 14,0,0));
//        aMap.(cameraUpdate);
        MapStatusUpdate update = MapStatusUpdateFactory.newLatLngZoom(latLngs.get(latLngs.size()/2), 14);
        // 移动到某经纬度
        aMap.animateMapStatus(update);

        aMap.addOverlay(
                new PolylineOptions()
                        .points(latLngs)
                        .color(ContextCompat.getColor(JNBusApplication.getContext(), R.color.colorPrimary))
                        .width(8)
        );





    }
}
