package com.wendy.jnbus.net;

import android.content.Context;

import com.eagle.androidlib.net.SubscriberOnNextListener;
import com.wendy.jnbus.persistence.BusShare;
import com.wendy.jnbus.vo.BusDetail;
import com.wendy.jnbus.vo.BusLine;
import com.wendy.jnbus.vo.PageInfoResult;

import java.util.List;

import rx.Observable;

/**
 * Created by Wendy on 2016/12/16.
 */
public class BusHttpMethod {

    /**
     * 根据线路名称，查看路上都有哪些车辆
     * @param subListener
     * @param busLineId
     */
    public static void queryBusDetail(Context context,SubscriberOnNextListener<List<BusDetail>> subListener,String busLineId){
        Observable observable = HttpMethods.getInstance().getBusService().queryBusDetail(BusShare.getKeyArea(), busLineId)
                .map(new HttpResultFunc<List<BusDetail>>());
        HttpMethods.getInstance().toSubscribe(observable,new HttpSubscriber<List<BusDetail>>(subListener, context));
    }


    /**
     * 根据线路名称，查看线路具体情况，走了哪些站
     * @param context
     * @param subListener
     * @param busLineId
     */
    public static void queryBusLine(Context context,SubscriberOnNextListener<BusLine> subListener,String busLineId){
        Observable observable = HttpMethods.getInstance().getBusService().queryBusLine(BusShare.getKeyArea(), busLineId)
                .map(new HttpResultFunc<BusLine>());
        HttpMethods.getInstance().toSubscribe(observable,new HttpSubscriber<BusLine>(subListener, context));
    }


    /**
     * 根据用户输入信息，查询线路
     * @param context
     * @param subListener
     * @param searchLine
     * @param start
     * @param len
     */
    public static void queryBusLine(Context context, SubscriberOnNextListener<PageInfoResult<BusLine>> subListener, String searchLine, int start , int len ){
        Observable observable = HttpMethods.getInstance().getBusService().queryBusLine(BusShare.getKeyArea(),
                searchLine, start, len)
                .map(new HttpResultFunc());
        HttpMethods.getInstance().toSubscribe(observable, new HttpSubscriber<PageInfoResult<BusLine>>(subListener, context));
    }
}
