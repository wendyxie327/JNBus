package com.wendy.jnbus.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wendy.jnbus.ui.JNBusApplication;
import com.wendy.jnbus.vo.BusLine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by Wendy on 2016/12/17.
 */
public class BusShare {

    private static final String BUS_IP_DEFAULT = "60.216.101.229";
    private static final int VERSION_DEFAULT = 2319;
    private static final String AREA_DEFAULT = "370100";


    private static final String SHARE_NAME = "bus";

    private static final String KEY_AREA = "area";
    private static final String KEY_BUS_IP = "bus_ip";
    private static final String KEY_VERSION_CODE = "version_code";
    private static final String KEY_SEARCH_HISTORY = "search_history";


    public static void setKeyBusIpAndArea(String ip, String area){
        SharedPreferences sharedPreferences = JNBusApplication.getContext().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(KEY_BUS_IP, ip).commit();
        sharedPreferences.edit().putString(KEY_AREA, area ).commit();
    }

    public static void setKeyVersionCode(int versionCode){
        SharedPreferences sharedPreferences = JNBusApplication.getContext().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(KEY_VERSION_CODE, versionCode).commit();
    }

    public static int getKeyVersionCode(){
        return JNBusApplication.getContext().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE).getInt(KEY_VERSION_CODE, VERSION_DEFAULT);
    }


    public static String getKeyBusIp(){
        return JNBusApplication.getContext().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE).getString(KEY_BUS_IP, BUS_IP_DEFAULT);
    }

    public static String getKeyArea(){
        return JNBusApplication.getContext().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE).getString(KEY_AREA, AREA_DEFAULT);
    }

    public static void setKeySearchHistory(List<BusLine> busLines){
        SharedPreferences sharedPreferences = JNBusApplication.getContext().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        if ( busLines == null){
            busLines = new ArrayList<>();
        }
        sharedPreferences.edit().putString(KEY_SEARCH_HISTORY, (new Gson()).toJson(busLines)).commit();
    }

    /**
     * 添加查询历史记录
     * @param busLine
     * @return
     */
    public static List<BusLine> addKeySearchHistory(BusLine busLine){
        List<BusLine> keySearchHistory = getKeySearchHistory(); // 旧车辆列表
        List<BusLine> searchHisNew = new ArrayList<>();// 新车辆列表
        if ( keySearchHistory!=null && keySearchHistory.size()>0 && busLine!=null ){
            for (int i=0; i<keySearchHistory.size(); i++){
                if ( !busLine.getId().equals( keySearchHistory.get(i).getId())){
                    searchHisNew.add( keySearchHistory.get(i)); // 在旧列表中去除本次查询的车辆信息，保证最新查询的在最上方
                }
            }
        }
        if (busLine!= null){
            searchHisNew.add(busLine);
        }
        setKeySearchHistory( searchHisNew); // 更新车辆列表
        return searchHisNew;
    }

    /**
     * 获取用户已查询的查询历史
     * @return
     */
    public static List<BusLine> getKeySearchHistory(){
        String searchHistory = JNBusApplication.getContext().getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE).getString(KEY_SEARCH_HISTORY, "");
        List<BusLine> busLines = (new Gson()).fromJson(searchHistory, new TypeToken<List<BusLine>>(){}.getType());
        if ( busLines == null){
            busLines = new ArrayList<>();
        }
        return busLines;
    }
}
