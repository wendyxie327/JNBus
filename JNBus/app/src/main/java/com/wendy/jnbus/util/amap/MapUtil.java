package com.wendy.jnbus.util.amap;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;

/**
 * 类描述：地图类
 * 创建人：XieWQ
 * 创建时间：2017/6/22 0022 下午 16:49
 */
public class MapUtil {

    private static String TAG = "MapUtil";


    /**
     * 开始查询输入框内容
     * @param context   context
     * @param inputtipsListener 输入内容监听事件
     * @param inputtipsQuery    查询内容
     */
    public void searchPoiInputAsyn(Context context, Inputtips.InputtipsListener inputtipsListener, InputtipsQuery inputtipsQuery){
        Inputtips inputtips = new Inputtips(context, inputtipsQuery);
        inputtips.setInputtipsListener(inputtipsListener);
        inputtips.requestInputtipsAsyn();
    }

    /**
     * 获取城市预报天气
     * @param context   实现WeatherSearch.OnWeatherSearchListener
     * @param city  城市
     */
    public void searchWeatherForecast(Context context, String city){
        searchWeather(context, city, WeatherSearchQuery.WEATHER_TYPE_FORECAST);
    }

    /**
     * 查询城市天气
     * @param context 实现WeatherSearch.OnWeatherSearchListener
     * @param city  城市
     * @param type  实况天气为WEATHER_TYPE_LIVE、天气预报为WEATHER_TYPE_FORECAST
     */
    private void searchWeather(Context context, String city, int type){
        //检索参数为城市和天气类型，实况天气为WEATHER_TYPE_LIVE、天气预报为WEATHER_TYPE_FORECAST
        WeatherSearchQuery weatherSearchQuery = new WeatherSearchQuery("北京", type);
        WeatherSearch weatherSearch=new WeatherSearch(context);
        weatherSearch.setOnWeatherSearchListener((WeatherSearch.OnWeatherSearchListener) context);
        weatherSearch.setQuery(weatherSearchQuery);
        weatherSearch.searchWeatherAsyn(); //异步搜索
    }


    /**
     * 初始化并开启定位
     * @param locationListener   实现AMapLocationListener的Activity.context
     * @param aMapLocationClient    aMapLocationClient 不能为null
     * @param isOnceLocation    true只定位一次，false多次定位;
     *                          false时，注意onDestroy()中调用stopLocation()
     * @param interval          定位间隔
     */
    private void startLocation(AMapLocationListener locationListener, AMapLocationClient aMapLocationClient, boolean isOnceLocation, long interval ){
        if ( aMapLocationClient == null) throw new NullPointerException("aMapLocationClient is null");
        //设置定位回调监听
        aMapLocationClient.setLocationListener(locationListener);
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        //设置为高精度定位模式
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置为只定位一次
        aMapLocationClientOption.setOnceLocation(isOnceLocation);
        //设置定位时间间隔
        aMapLocationClientOption.setInterval(interval);
        //设置定位参数
        aMapLocationClient.setLocationOption(aMapLocationClientOption);

        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        aMapLocationClient.startLocation();
    }


    /**
     * 初始化并开启定位 - 只支持单次定位
     * @param locationListener    实现AMapLocationListener的Activity.context
     * @param aMapLocationClient aMapLocationClient 不能为null
     */
    public void startLocationOnce(AMapLocationListener locationListener, AMapLocationClient aMapLocationClient){
        startLocation(locationListener, aMapLocationClient, true, 0);
    }

    /**
     * 初始化并开启定位 - 多次定位
     * @param locationListener   实现AMapLocationListener的Activity.context
     * @param aMapLocationClient    aMapLocationClient 不能为null
     * @param interval  间隔
     */
    public void startLocation(AMapLocationListener locationListener, AMapLocationClient aMapLocationClient, long interval){
        startLocation(locationListener, aMapLocationClient, false, interval);
    }
}
