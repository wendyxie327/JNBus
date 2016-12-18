package com.wendy.jnbus.vo;

import java.util.Date;

/**
 * 正在行驶车辆的相关信息
 * Created by Wendy on 2016/12/16.
 */
public class BusDetail {

    private String busId;
    private double lng;
    private double lat;
    private double velocity;    // 当前时速
    private String isArrvLft;   //1没到，2到了
    private String stationSeqNum;    // 站牌id，对应BusStation中id字段
    private String buslineId;   // 路线id,对应BusLine中id字段
    private Date actTime;
    private String cardId;
    private String orgName;
    private double averageVelocity; //平均时速
    private int coordinate;

    @Override
    public String toString() {
        return "BusDetail{" +
                "busId='" + busId + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", velocity=" + velocity +
                ", isArrvLft='" + isArrvLft + '\'' +
                ", stationSeqNum='" + stationSeqNum + '\'' +
                ", buslineId='" + buslineId + '\'' +
                ", actTime=" + actTime +
                ", cardId='" + cardId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", averageVelocity=" + averageVelocity +
                ", coordinate=" + coordinate +
                '}';
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
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

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public String getIsArrvLft() {
        return isArrvLft;
    }

    public void setIsArrvLft(String isArrvLft) {
        this.isArrvLft = isArrvLft;
    }

    public String getStationSeqNum() {
        return stationSeqNum;
    }

    public void setStationSeqNum(String stationSeqNum) {
        this.stationSeqNum = stationSeqNum;
    }

    public String getBuslineId() {
        return buslineId;
    }

    public void setBuslineId(String buslineId) {
        this.buslineId = buslineId;
    }

    public Date getActTime() {
        return actTime;
    }

    public void setActTime(Date actTime) {
        this.actTime = actTime;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public double getAverageVelocity() {
        return averageVelocity;
    }

    public void setAverageVelocity(double averageVelocity) {
        this.averageVelocity = averageVelocity;
    }

    public int getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
    }
}
