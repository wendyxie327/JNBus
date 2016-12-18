package com.wendy.jnbus.net;

/**
 * Created by Wendy on 2016/12/16.
 */

import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.vo.Response;

import rx.functions.Func1;

/**
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *
 * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */
public class HttpResultFunc<T> implements Func1<Response<T>, T> {

    @Override
    public T call(Response<T> response) {

        if (response!=null){
            Logger.d("HttpMethods","httpResult="+response.toString());
            return response.getResult();
        }
        return null;
    }
}