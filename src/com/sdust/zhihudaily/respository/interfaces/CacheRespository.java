/**
 * �ļ�����CacheRespository.java
 * ʱ�䣺2015��7��19������10:10:38
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.respository.interfaces;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.bean.StartImage;

/**
 * ������CacheRespository ˵����
 */
public interface CacheRespository {

	void getStartImage(int width, int height, Callback<StartImage> callback);

	void saveStartImage(int width, int height, DisplayImageOptions options,
			StartImage startImage);

	public interface Callback<T> {
		public void success(T t);

		public void failure(Exception e);
	}
}
