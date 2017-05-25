package com.wendy.jnbus.net;

import com.eagle.androidlib.net.NullOnEmptyConverterFactory;
import com.wendy.jnbus.persistence.BusShare;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liukun on 16/3/9.
 */
public class HttpMethods {


    private static final String TAG = "HttpMethods";

    //基路径请以“/”结尾，Service类中，配置路径，不要以“/”开头，否则容易造成路径访问问题。
    public static final String BASE_URL = "http://60.216.101.229/server-ue2/rest/buslines/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private BusService busService;

    public BusService getBusService() {
        return busService;
    }

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        String busUrl = "http://" + BusShare.getKeyBusIp() + "/server-ue2/rest/";

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(  new OkHttpClient.Builder().addInterceptor(versionTokenInterceptor).build()) //设置统一头信息
                .baseUrl(busUrl)
                .build();

        busService = retrofit.create(BusService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
         o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }


    /**
     * 统一头信息：请求版本号
     */
    Interceptor versionTokenInterceptor = new Interceptor() {
        @Override public Response intercept(Chain chain) throws IOException {
            Request authorised = chain.request().newBuilder()
                    .header("version", "android-insigma.waybook.jinan-"+BusShare.getKeyVersionCode())
//                    .addHeader("Connection","close") // 解决错误， java.io.EOFException
//                    .addHeader("http.keepAlive", "false")
                    .build();
            return chain.proceed(authorised);
        }
    };

}
