package com.wendy.jnbus.vo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 公交车站点相关信息
 * Created by Wendy on 2016/12/16.
 */
public class BusStation {

    private String id;
    private int area;
    private String stationName;// 站点名称
    private double lng;// 经度
    private double lat;// 纬度
    private String buslines;
    private String state;
    private Date updateTime;
    private int distance;
    private String[] busLineList;
    private List<BusDetail> busDetails; //该站点存在的总车辆

    @Override
    public String toString() {
        return "BusStation{" +
                "id='" + id + '\'' +
                ", area=" + area +
                ", stationName='" + stationName + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", buslines='" + buslines + '\'' +
                ", state='" + state + '\'' +
                ", updateTime=" + updateTime +
                ", distance=" + distance +
                ", busLineList=" + Arrays.toString(busLineList) +
                ", busDetails=" + busDetails +
                '}';
    }

    public List<BusDetail> getBusDetails() {
        return busDetails;
    }

    public void setBusDetails(List<BusDetail> busDetails) {
        this.busDetails = busDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getBuslines() {
        return buslines;
    }

    public void setBuslines(String buslines) {
        this.buslines = buslines;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String[] getBusLineList() {
        return busLineList;
    }

    public void setBusLineList(String[] busLineList) {
        this.busLineList = busLineList;
    }
}
