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
    private String operationTime;// 例如： "公交驾校  : 6:00－20:30 解放桥东 : 6:00－21:05",
    private String ticketPrice;// 例如：87:票价一元  K87:票价两元


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

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
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
