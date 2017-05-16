package com.wendy.jnbus.vo;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class Address {

    private int id;
    private String name;
    private String httpAddr;
    private String functions;
    private String state;
    private boolean useToken;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", httpAddr='" + httpAddr + '\'' +
                ", functions='" + functions + '\'' +
                ", state='" + state + '\'' +
                ", useToken=" + useToken +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHttpAddr() {
        return httpAddr;
    }

    public void setHttpAddr(String httpAddr) {
        this.httpAddr = httpAddr;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isUseToken() {
        return useToken;
    }

    public void setUseToken(boolean useToken) {
        this.useToken = useToken;
    }
}
