package com.sdust.zhihudaily.respository.imp;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.sdust.zhihudaily.api.ZhiHuApi;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.respository.interfaces.*;
/**
 * 文件名：CacheRespositoryImp.java
 * 时间：2015年7月19日上午10:14:04
 * 作者：Kevin
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
