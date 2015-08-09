package com.sdust.zhihudaily.repository.imp;

import com.sdust.zhihudaily.api.ZhiHuApi;
import com.sdust.zhihudaily.model.DailyStories;
import com.sdust.zhihudaily.model.StartImage;
import com.sdust.zhihudaily.model.Theme;
import com.sdust.zhihudaily.model.Themes;
import com.sdust.zhihudaily.repository.interfaces.NetRepository;
import com.sdust.zhihudaily.util.LogUtils;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class NetRepositoryImp implements NetRepository {
    private static final String TAG = NetRepositoryImp.class.getSimpleName();

    @Override
    public void getStartImage(int width, int height,
                              final Callback<StartImage> callback) {

        ZhiHuApi.createApi().getStartImage(width, height, new retrofit.Callback<StartImage>() {


            @Override
            public void success(StartImage startImage, Response response) {
                callback.success(startImage, response.getUrl());
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error, error.getUrl());
            }

        });

    }

    @Override
    public void getThemes(final Callback<Themes> callback) {
        ZhiHuApi.createApi().getThemes(new retrofit.Callback<Themes>() {
            @Override
            public void success(Themes themes, Response response) {
                callback.success(themes, response.getUrl());
                LogUtils.i(TAG, "getThemes net");
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error, error.getUrl());
            }
        });
    }

    @Override
    public void getLatestDailyStories(final Callback<DailyStories> callback) {
        ZhiHuApi.createApi().getLatestDailyStories(new retrofit.Callback<DailyStories>() {
            @Override
            public void success(DailyStories dailyStories, Response response) {
                callback.success(dailyStories, response.getUrl());
                LogUtils.i(TAG, "getLatestDailyStories net");
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error, error.getUrl());
            }
        });
    }

    @Override
    public void getBeforeDailyStories(String date, final Callback<DailyStories> callback) {
        ZhiHuApi.createApi().getBeforeDailyStories(date, new retrofit.Callback<DailyStories>() {
            @Override
            public void success(DailyStories dailyStories, Response response) {
                callback.success(dailyStories, response.getUrl());
                LogUtils.i(TAG, "getBeforeDailyStories net");
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error, error.getUrl());
            }
        });
    }

    @Override
    public void getThemeStories(String themeId, final Callback<Theme> callback) {
        ZhiHuApi.createApi().getThemeStories(themeId, new retrofit.Callback<Theme>() {
            @Override
            public void success(Theme theme, Response response) {
                callback.success(theme, response.getUrl());
                LogUtils.i(TAG, "getThemeStories net");
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error, error.getUrl());
            }
        });
    }

    @Override
    public void getBeforeThemeStories(String themeId, String storyId, final Callback<Theme> callback) {
        ZhiHuApi.createApi().getBeforeThemeStories(themeId, storyId, new retrofit.Callback<Theme>() {
            @Override
            public void success(Theme theme, Response response) {
                callback.success(theme, response.getUrl());
                LogUtils.i(TAG, "getBeforeThemeStories net");
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error, error.getUrl());
            }
        });
    }

}
