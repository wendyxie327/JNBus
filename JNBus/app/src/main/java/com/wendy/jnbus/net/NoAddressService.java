package com.wendy.jnbus.net;

import com.wendy.jnbus.vo.Address;
import com.wendy.jnbus.vo.Response;
import com.wendy.jnbus.vo.Version;
import com.wendy.jnbus.vo.fir.AppVersion;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public interface NoAddressService {

    @GET("update.json")
    Observable<Response<Version>> checkLastestVersion();


    @GET("{area}")
    Observable<Response<Address>> checkIp(@Path("area") String area);

    @GET("{id}")
    Observable<AppVersion> checkAppUpdate(@Path("id") String appId, @Query("api_token") String api_token, @Query("type") String type);
}
