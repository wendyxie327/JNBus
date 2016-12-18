package com.wendy.jnbus.vo;

import java.util.Date;
import java.util.List;

/**
 * 公交车路线相关信息
 * Created by Wendy on 2016/12/16.
 */
public class BusLine {

    private String id;// 公交线路id，用于查询
    private int area;
    private String lineName;//线路名称
    private String startStationName;// 起始站
    private String endStationName;// 结束站
    private List<BusStation> stations;// 各站点
    private Date updateTime;// 该线路最近更新时间

    @Override
    public String toString() {
        return "BusLine{" +
                "id='" + id + '\'' +
                ", area=" + area +
                ", lineName='" + lineName + '\'' +
                ", startStationName='" + startStationName + '\'' +
                ", endStationName='" + endStationName + '\'' +
                ", stations=" + stations +
                ", updateTime=" + updateTime +
                '}';
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

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }

    public List<BusStation> getStations() {
        return stations;
    }

    public void setStations(List<BusStation> stations) {
        this.stations = stations;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
