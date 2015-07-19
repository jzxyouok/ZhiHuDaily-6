/**
 * 文件名：Respository.java
 * 时间：2015年7月19日上午9:52:08
 * 作者：Kevin
 */
package com.sdust.zhihudaily.respository.interfaces;

import com.sdust.zhihudaily.bean.StartImage;

import android.provider.MediaStore.Video;

/**
 * 类名：Respository 说明：
 */
public interface Respository {
	void getStartImage(Callback<StartImage> callback) ;


	public interface Callback<T> {
		public void success(T t);

		public void failure(Exception e);
	}
}
