/**
 * 文件名：Respository.java
 * 时间：2015年7月19日上午9:52:08
 * 作者：Kevin
 */
package com.sdust.zhihudaily.repository.interfaces;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.bean.Themes;
import com.sdust.zhihudaily.repository.interfaces.Repository.Callback;

import android.provider.MediaStore.Video;

/**
 * 类名：Repository 说明：
 */
public interface Repository {

	public interface Callback<T> {
		public void success(T t);

		public void failure(Exception e);
	}

	void getStartImage(int width, int height, DisplayImageOptions options,
			Callback<StartImage> callback);
	
	void getThemes(Callback<Themes> themes);
}
