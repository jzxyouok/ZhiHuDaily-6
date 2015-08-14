package com.sdust.zhihudaily.network;


import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class CacheInterceptor implements Interceptor {
    private static final String CACHE_CONTROL = "Cache-Control";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Response response = chain.proceed(request);
        return response.newBuilder()
                .header(CACHE_CONTROL, "public, max-age=" + 60 * 10) // 10min
                .build();
    }
}
