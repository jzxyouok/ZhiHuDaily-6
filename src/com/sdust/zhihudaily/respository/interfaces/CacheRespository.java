/**
 * 文件名：CacheRespository.java
 * 时间：2015年7月19日上午10:10:38
 * 作者：Kevin
 */
package com.sdust.zhihudaily.respository.interfaces;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.bean.StartImage;

/**
 * 类名：CacheRespository 说明：
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
