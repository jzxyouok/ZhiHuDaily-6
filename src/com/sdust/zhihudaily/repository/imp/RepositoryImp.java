/**
 * �ļ�����RespositoryImp.java
 * ʱ�䣺2015��7��19������10:01:30
 * ���ߣ���ά��
 */
package com.sdust.zhihudaily.repository.imp;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.repository.interfaces.CacheRepository;
import com.sdust.zhihudaily.repository.interfaces.NetRepository;
import com.sdust.zhihudaily.repository.interfaces.Repository;

/**
 * ������RespositoryImp
 * ˵����
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
