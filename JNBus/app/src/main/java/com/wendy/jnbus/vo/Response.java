package com.wendy.jnbus.vo;

/**
 * 响应包
 * Created by Xiewenqian on 2016/12/16.
 */
public class Response<T> {

    private Status status;

    private T result;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", result=" + result +
                '}';
    }
}
