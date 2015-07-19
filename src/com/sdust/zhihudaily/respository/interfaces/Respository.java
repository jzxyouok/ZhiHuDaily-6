/**
 * 文件名：Respository.java
 * 时间：2015年7月19日上午9:52:08
 * 作者：Kevin
 */
package com.sdust.zhihudaily.respository.interfaces;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.respository.interfaces.Respository.Callback;

import android.provider.MediaStore.Video;

/**
 * 类名：Respository 说明：
 */
public interface Respository {

	public interface Callback<T> {
		public void success(T t);

		public void failure(Exception e);
	}

	void getStartImage(int height, int width, DisplayImageOptions options,
			Callback<StartImage> callback);
}
