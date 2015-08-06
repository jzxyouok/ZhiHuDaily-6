package com.sdust.zhihudaily.repository.imp;

import com.nostra13.universalimageloader.utils.L;
import com.sdust.zhihudaily.api.ZhiHuApi;
import com.sdust.zhihudaily.model.StartImage;
import com.sdust.zhihudaily.model.Themes;
import com.sdust.zhihudaily.repository.interfaces.NetRepository;

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
				callback.success(startImage,response.getUrl());
			}
			
			@Override
			public void failure(RetrofitError error) {
				callback.failure(error,error.getUrl());
			}

        });
		
	}

	@Override
	public void getThemes(final Callback<Themes> callback) {
		ZhiHuApi.createApi().getThemes(new retrofit.Callback<Themes>() {
			@Override
			public void success(Themes themes, Response response) {
				callback.success(themes, response.getUrl());
				L.i(TAG, "getThemes net");
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error, error.getUrl());
			}
		});
	}


}
