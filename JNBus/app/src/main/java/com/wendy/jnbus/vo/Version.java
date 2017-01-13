package com.wendy.jnbus.vo;

import java.util.Arrays;

/**
 * Created by Wendy on 2016/12/16.
 */
public class Version {

    private String versionName;// 版本名称
    private int versionCode; // *版本号，需要将此信息存入，并且加入到头信息中
    private String[] descriptions;
    private boolean must;
    private String url;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String[] getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String[] descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isMust() {
        return must;
    }

    public void setMust(boolean must) {
        this.must = must;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Version{" +
                "versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", descriptions=" + Arrays.toString(descriptions) +
                ", must=" + must +
                ", url='" + url + '\'' +
                '}';
    }
}
