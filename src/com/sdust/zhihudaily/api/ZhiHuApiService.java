package com.sdust.zhihudaily.api;



import com.sdust.zhihudaily.bean.StartImage;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;


public interface ZhiHuApiService {

    @GET("/start-image/{width}*{height}")
    void getStartImage(@Path("width") int width, @Path("height") int height, retrofit.Callback<StartImage> callback);

    
}
