
package com.sdust.zhihudaily.repository.imp;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.model.StartImage;
import com.sdust.zhihudaily.repository.interfaces.CacheRepository;
import com.sdust.zhihudaily.repository.interfaces.NetRepository;
import com.sdust.zhihudaily.repository.interfaces.Repository;

/**
 *仓库的实现类
 */
public class RepositoryImp implements Repository {
	
	private CacheRepository mCacheResImp;
	
	private NetRepository mNetResImp;
	
	private Context mContext;
	public RepositoryImp(Context context) {
		mContext = context;
		mCacheResImp = new CacheRepositoryImp(context);
		mNetResImp = new NetRepositoryImp();
	}

	@Override
	public void getStartImage(final int width,final int height,final DisplayImageOptions options,final Callback<StartImage> callback) {
		mCacheResImp.getStartImage(new CacheRepository.Callback<StartImage>() {

			@Override
			public void success(StartImage image) {
				callback.success(image,false);
			}

			@Override
			public void failure(Exception e) {
				callback.failure(e);
			}
		});
		
		mNetResImp.getStartImage(width,height,new NetRepository.Callback<StartImage>() {

			@Override
			public void success(StartImage startImage,String url) {
				mCacheResImp.saveStartImage(width, height, options, startImage);
			}

			@Override
			public void failure(Exception e,String url) {
				callback.failure(e);
			}
		});
	}

}
