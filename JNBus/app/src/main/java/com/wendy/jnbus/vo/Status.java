package com.wendy.jnbus.vo;

/**
 * 返回状态，暂时没用
 * Created by Wendy on 2016/12/16.
 */
public class Status {

    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Status{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
