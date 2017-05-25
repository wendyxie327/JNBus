package com.eagle.androidlib.net;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 类描述：用于处理收到的请求为{}空body的情况，解决报错java.io.EOFException
 *          使用方法如下
 *          Retrofit retrofit = new Retrofit.Builder()
                 .endpoint(..)
                 .addConverterFactory(new NullOnEmptyConverterFactory())
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
 * 创建人：XieWQ
 * 创建时间：2017/5/25 0025 下午 12:01
 */
public class NullOnEmptyConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
        return new Converter<ResponseBody, Object>() {
            @Override
            public Object convert(ResponseBody body) throws IOException {
                if (body.contentLength() == 0) return null;
                return delegate.convert(body);
            }
        };
    }
}
