
package com.sdust.zhihudaily.repository.imp;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.model.StartImage;
import com.sdust.zhihudaily.model.Themes;
import com.sdust.zhihudaily.repository.interfaces.CacheRepository;
import com.sdust.zhihudaily.repository.interfaces.NetRepository;
import com.sdust.zhihudaily.repository.interfaces.Repository;

/**
 *仓库的实现类
 */
public class RepositoryImp implements Repository {
	
	private CacheRepository mCacheReImp;
	
	private NetRepository mNetReImp;
	
	private Context mContext;
	public RepositoryImp(Context context) {
		mContext = context;
		mCacheReImp = new CacheRepositoryImp(context);
		mNetReImp = new NetRepositoryImp();
	}

	@Override
	public void getStartImage(final int width,final int height,final DisplayImageOptions options,final Callback<StartImage> callback) {
		mCacheReImp.getStartImage(new CacheRepository.Callback<StartImage>() {

			@Override
			public void success(StartImage image) {
				callback.success(image,false);
			}

			@Override
			public void failure(Exception e) {
				callback.failure(e);
			}
		});
		
		mNetReImp.getStartImage(width,height,new NetRepository.Callback<StartImage>() {

			@Override
			public void success(StartImage startImage,String url) {
				mCacheReImp.saveStartImage(width, height, options, startImage);
			}

			@Override
			public void failure(Exception e,String url) {
				callback.failure(e);
			}
		});
	}
	@Override
	public void getThemes(final Callback<Themes> callback) {
		mNetReImp.getThemes(new NetRepository.Callback<Themes>() {
			@Override
			public void success(Themes themes, String url) {
				callback.success(themes, false);
				mCacheReImp.saveThemes(themes, url);
			}

			@Override
			public void failure(final Exception error, String url) {
				mCacheReImp.getThemes(url, new CacheRepository.Callback<Themes>() {
					@Override
					public void success(Themes themes) {
						callback.success(themes, false);
					}

					@Override
					public void failure(Exception e) {
						callback.failure(error);
					}
				});
			}
		});
	}


}
