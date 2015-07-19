package com.sdust.zhihudaily.api;

import com.google.gson.GsonBuilder;
import com.sdust.zhihudaily.ZhiHuApplication;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;


public class RestApi {

    private static RestAdapter restAdapter;

    public static <T> T createApi(Class<T> clazz, String api) {
        if (restAdapter == null) {
            synchronized (RestApi.class) {
                if (restAdapter == null) {
                    restAdapter = new RestAdapter.Builder()
                            .setEndpoint(api)
                            .setClient(new OkClient(OkHttp.createHttpClient(ZhiHuApplication.getContext())))
                            .setConverter(new GsonConverter(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
                            .build();
                }
            }
        }
        return restAdapter.create(clazz);
    }
}
