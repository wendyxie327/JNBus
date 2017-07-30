package com.wendy.jnbus.util.baidumap;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.utils.poi.BaiduMapPoiSearch;
import com.wendy.jnbus.util.PubInfo;

/**
 * 类描述：地图类
 * 创建人：XieWQ
 * 创建时间：2017/6/22 0022 下午 16:49
 */
public class MapUtil {

    private static String TAG = "MapUtil";



    /**
     * 初始化并开启定位
     * @param context   实现AMapLocationListener的Activity.context
     * @param aMapLocationClient    aMapLocationClient
     * @param interval          定位间隔,单位ms, 0为指定为一次，间隔必须大于1s才是有效的
     */
    private void startLocation(Context context, LocationClient aMapLocationClient, int interval ){
        //初始化定位
        if (aMapLocationClient == null) {
            aMapLocationClient = new LocationClient(context.getApplicationContext());
            Log.d(TAG , "-----------startLocation- null-------");
        }
        //设置定位回调监听
        aMapLocationClient.registerLocationListener((BDLocationListener) context);
        LocationClientOption option = new LocationClientOption();
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");

        //设置为高精度定位模式
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setScanSpan(interval);
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        aMapLocationClient.setLocOption(option);

        // 开始定位
        aMapLocationClient.start();
    }


    /**
     * 初始化并开启定位 - 只支持单次定位
     * @param context    实现AMapLocationListener的Activity.context
     * @param aMapLocationClient aMapLocationClient
     */
    public void startLocationOnce(Context context, LocationClient aMapLocationClient){
        startLocation(context, aMapLocationClient, 0);
    }


}
