package com.sdust.zhihudaily.respository.imp;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.sdust.zhihudaily.api.ZhiHuApi;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.respository.interfaces.*;
/**
 * �ļ�����CacheRespositoryImp.java
 * ʱ�䣺2015��7��19������10:14:04
 * ���ߣ�Kevin
 */
public class NetRespositoryImp implements NetRespository{

	@Override
	public void getStartImage(int height, int width,
			final Callback<StartImage> callback) {
		
		ZhiHuApi.createApi().getStartImage(width, height, new retrofit.Callback<StartImage>() {
      

			@Override
			public void success(StartImage startImage, Response response) {
				callback.success(startImage);
			}
			
			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}

        });
		
	}

	

}
