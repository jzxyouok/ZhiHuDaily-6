package com.sdust.zhihudaily.repository.imp;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.sdust.zhihudaily.api.ZhiHuApi;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.bean.Themes;
import com.sdust.zhihudaily.repository.interfaces.*;
/**
 * 文件名：CacheRespositoryImp.java
 * 时间：2015年7月19日上午10:14:04
 * 作者：Kevin
 */
public class NetRepositoryImp implements NetRepository{

	@Override
	public void getStartImage(int width, int height,
			final Callback<StartImage> callback) {
		
		ZhiHuApi.createApi().getStartImage(width, height, new retrofit.Callback<StartImage>() {
      

			@Override
			public void success(StartImage startImage, Response response) {
				callback.success(startImage,response.getUrl());
			}
			
			@Override
			public void failure(RetrofitError error) {
				callback.failure(error,null);
			}

        });
		
	}

	@Override
	public void getThemes(final Callback<Themes> callback) {
		ZhiHuApi.createApi().getThemes(new retrofit.Callback<Themes>() {
			
			@Override
			public void success(Themes themes, Response response) {
				callback.success(themes, response.getUrl());
				
			}
			
			@Override
			public void failure(RetrofitError error) {
				callback.failure(error, error.getUrl());
			}
		});
	}

	

}
