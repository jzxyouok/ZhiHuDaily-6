/**
 * 文件名：ZhiHuApi.java
 * 时间：2015年7月19日下午4:43:07
 * 作者：Kevin
 */
package com.sdust.zhihudaily.api;

/**
 * 类名：ZhiHuApi
 * 说明：
 */
public class ZhiHuApi {

    private static final String API = "http://news.at.zhihu.com/api/4";

    private static ZhiHuApiService dailyApiService;

    public static ZhiHuApiService createApi() {
        if (dailyApiService == null) {
            synchronized (ZhiHuApi.class) {
                if (dailyApiService == null) {
                    dailyApiService = RestApi.createApi(ZhiHuApiService.class, API);
                }
            }
        }
        return dailyApiService;
    }

}
