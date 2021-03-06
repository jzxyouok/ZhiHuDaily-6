package com.sdust.zhihudaily.api;


import com.sdust.zhihudaily.model.DailyStories;
import com.sdust.zhihudaily.model.StartImage;
import com.sdust.zhihudaily.model.Story;
import com.sdust.zhihudaily.model.Theme;
import com.sdust.zhihudaily.model.Themes;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;


public interface ZhiHuApiService {

    @GET("/start-image/{width}*{height}")
    void getStartImage(@Path("width") int width, @Path("height") int height, retrofit.Callback<StartImage> callback);

    @GET("/themes")
    void getThemes(retrofit.Callback<Themes> callback);

    @GET("/news/latest")
    void getLatestDailyStories(Callback<DailyStories> callback);

    @GET("/news/before/{date}")
    void getBeforeDailyStories(@Path("date") String date, Callback<DailyStories> callback);

    @GET("/theme/{themeId}")
    void getThemeStories(@Path("themeId") String themeId, Callback<Theme> callback);

    @GET("/theme/{themeId}/before/{storyId}")
    void getBeforeThemeStories(@Path("themeId") String themeId, @Path("storyId") String storyId, Callback<Theme> callback);

    @GET("/news/{storyId}")
    void getStoryDetail(@Path("storyId") String storyId, Callback<Story> callback);

}
