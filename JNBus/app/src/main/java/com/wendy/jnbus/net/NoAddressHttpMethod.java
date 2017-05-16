package com.wendy.jnbus.net;

import android.content.Context;
import android.util.Log;

import com.eagle.androidlib.net.SubscriberOnNextListener;
import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.persistence.BusShare;
import com.wendy.jnbus.util.PubInfo;
import com.wendy.jnbus.vo.Address;
import com.wendy.jnbus.vo.Version;
import com.wendy.jnbus.vo.fir.AppVersion;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class NoAddressHttpMethod {

    private static String TAG = "NoAddressHttpMethod";
    private static final int DEFAULT_TIMEOUT = 5;
    private static OkHttpClient.Builder builder;

    //构造方法私有
    private NoAddressHttpMethod() {
        //手动创建一个OkHttpClient并设置超时时间
        if (builder ==null){
            builder = new OkHttpClient.Builder();
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        }
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final NoAddressHttpMethod INSTANCE = new NoAddressHttpMethod();
    }
    //获取单例
    public static NoAddressHttpMethod getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 检查公交最新版本号
     * @param context
     * @param subListener
     */
    public void checkLastestVersion(Context context, SubscriberOnNextListener<Version> subListener) {
        Retrofit retrofit = new Retrofit.Builder()
                            .client(builder.build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .baseUrl("http://jinan.iwaybook.com/download/")
                            .build();
        NoAddressService service = retrofit.create(NoAddressService.class);
        Observable observable = service.checkLastestVersion()
                                        .map(new HttpResultFunc<Version>());
        HttpMethods.getInstance().toSubscribe(observable, new HttpSubscriber<Version>(subListener, context));
    }


    /**
     * 检查IP地址是否发生改变
     * @param context
     * @param subListener
     */
    public void checkIP(Context context, SubscriberOnNextListener<Address> subListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://www.iwaybook.com/server-ue2/rest/servers-v2/")
                .build();
        NoAddressService service = retrofit.create(NoAddressService.class);
        Observable observable = service.checkIp(BusShare.getKeyArea())
                .map(new HttpResultFunc<Address>());
        HttpMethods.getInstance().toSubscribe(observable, new HttpSubscriber<Address>(subListener, context));
    }

    /**
     * 检查应用升级
     * @param subListener
     */
    public void checkAppUpdate( SubscriberOnNextListener<AppVersion> subListener){
        Logger.d(TAG, "---checkAppUpdate");
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://api.fir.im/apps/latest/")
                .build();
        NoAddressService service = retrofit.create(NoAddressService.class);
        Observable observable = service.checkAppUpdate(PubInfo.FIR_APP_ID, PubInfo.FIR_API_TOKEN,"android");
        HttpMethods.getInstance().toSubscribe(observable, new HttpSubscriber<AppVersion>(subListener));// 对结果的处理
    }
}
