/**
 * 文件名：RespositoryImp.java
 * 时间：2015年7月19日上午10:01:30
 * 作者：修维康
 */
package com.sdust.zhihudaily.repository.imp;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.repository.interfaces.CacheRepository;
import com.sdust.zhihudaily.repository.interfaces.NetRepository;
import com.sdust.zhihudaily.repository.interfaces.Repository;

/**
 * 类名：RespositoryImp
 * 说明：
 */
public class RepositoryImp implements Repository{
	
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
		mCacheResImp.getStartImage(width,height,new CacheRepository.Callback<StartImage>() {

			@Override
			public void success(StartImage image) {
				callback.success(image);
			}

			@Override
			public void failure(Exception e) {
				callback.failure(e);
			}
		});
		
		mNetResImp.getStartImage(width,height,new NetRepository.Callback<StartImage>() {

			@Override
			public void success(StartImage startImage) {
				mCacheResImp.saveStartImage(width, height, options, startImage);
			}

			@Override
			public void failure(Exception e) {
				callback.failure(e);
			}
		});
	}

}
