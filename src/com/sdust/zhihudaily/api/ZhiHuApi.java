/**
 * �ļ�����ZhiHuApi.java
 * ʱ�䣺2015��7��19������4:43:07
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.api;

/**
 * ������ZhiHuApi
 * ˵����
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
