package com.wendy.jnbus.net;


import com.wendy.jnbus.vo.BusDetail;
import com.wendy.jnbus.vo.BusLine;
import com.wendy.jnbus.vo.PageInfoResult;
import com.wendy.jnbus.vo.Response;
import com.wendy.jnbus.vo.Version;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface BusService {

    /**
     * 输入线路名称，查询到正在路上的车辆信息
     * @param area
     * @param busLineId 线路名
     * @return
     */
    @GET("buses/busline/{area}/{busLineId}")
    Observable<Response<List<BusDetail>>> queryBusDetail(@Path("area") String area, @Path("busLineId") String busLineId );


    /**
     * 输入线路名称，查询到该线路具体站点信息
     * @param area
     * @param busLineId 线路名
     * @return
     */
    @GET("buslines/theOtherDirection/{area}/{busLineId}")
    Observable<Response<BusLine>> queryBusLine(@Path("area") String area, @Path("busLineId") String busLineId );


    /**
     * 根据输入内容查询存在线路
     * @param area
     * @param queryContent  查询内容
     * @param start 分页-开始
     * @param len   分页-数量
     * @return
     */
    @GET("buslines/simple/{area}/{queryContent}/{start}/{len}")
    Observable<Response<PageInfoResult<BusLine>>> queryBusLine(@Path("area") String area, @Path("queryContent") String queryContent,
                                                      @Path("start") int start, @Path("len") int len);
}
