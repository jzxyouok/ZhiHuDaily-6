package com.sdust.zhihudaily.network;


import com.sdust.zhihudaily.util.NetWorkUtils;
import com.sdust.zhihudaily.ZhiHuApplication;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class CacheInterceptor implements Interceptor {
    private static final String CACHE_CONTROL = "Cache-Control";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        
        if (request.method().equals("GET")) {
        	//如果当前有网络，则强制要求
            if (NetWorkUtils.isNetWorkAvailable(ZhiHuApplication.getContext())) {
                request.newBuilder().addHeader(CACHE_CONTROL, "only-if-cached").build();
            } else {
                request.newBuilder().addHeader(CACHE_CONTROL, "public, max-stale=2419200").build();
            }
        }
        Response response = chain.proceed(request);
        // Re-write response CC header to force use of cache
        return response.newBuilder()
                .header("Cache-Control", "public, max-age=" + 60 * 10) // 10min
                .build();
    }
}
