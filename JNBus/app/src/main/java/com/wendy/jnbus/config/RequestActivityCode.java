package com.wendy.jnbus.config;

/**
 * 类描述：AActivity 和BActivity之间通信码
 * 创建人：XieWQ
 * 创建时间：2017/8/18 0018 下午 14:21
 */
public class RequestActivityCode {

    public static final int SEARCH_BUSLINE_REQUEST = 10001;

    /** 换乘 → 地点查询 */
    public static final int CHANGEBUS_SEARCHLOCATION_REQUEST_FROM = 10002;
    public static final int CHANGEBUS_SEARCHLOCATION_REQUEST_TO = 10003;
    public static final int CHANGEBUS_SEARCHLOCATION_RESULT = 20000;
}
