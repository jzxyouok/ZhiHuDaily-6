/**
 * �ļ�����RespositoryImp.java
 * ʱ�䣺2015��7��19������10:01:30
 * ���ߣ���ά��
 */
package com.sdust.zhihudaily.respository.imp;

import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.respository.interfaces.CacheRespository;
import com.sdust.zhihudaily.respository.interfaces.NetRespository;
import com.sdust.zhihudaily.respository.interfaces.Respository;

/**
 * ������RespositoryImp
 * ˵����
 */
public class RespositoryImp implements Respository{
	
	private CacheRespository mCacheResImp;
	
	private NetRespository mNetResImp;
	
	public RespositoryImp() {
		mCacheResImp = new CacheRespositoryImp();
		mNetResImp = new NetRespositoryImp();
	}

	@Override
	public void getStartImage(Callback<StartImage> callback) {
		mCacheResImp.getStartImage(new CacheRespository.Callback<StartImage>() {

			@Override
			public void success(StartImage image) {
				
			}

			@Override
			public void failure(Exception e) {
				
			}
		});
		
		
		mNetResImp.getStartImage();
		
	}

}
