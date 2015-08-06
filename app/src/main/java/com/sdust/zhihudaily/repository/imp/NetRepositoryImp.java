package com.sdust.zhihudaily.repository.imp;
import com.sdust.zhihudaily.api.ZhiHuApi;
import com.sdust.zhihudaily.model.StartImage;
import com.sdust.zhihudaily.repository.interfaces.NetRepository;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class NetRepositoryImp implements NetRepository {

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
				callback.failure(error,error.getUrl());
			}

        });
		
	}

	

}
