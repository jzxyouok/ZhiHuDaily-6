/**
 * �ļ�����Respository.java
 * ʱ�䣺2015��7��19������9:52:08
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.respository.interfaces;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.respository.interfaces.Respository.Callback;

import android.provider.MediaStore.Video;

/**
 * ������Respository ˵����
 */
public interface Respository {

	public interface Callback<T> {
		public void success(T t);

		public void failure(Exception e);
	}

	void getStartImage(int height, int width, DisplayImageOptions options,
			Callback<StartImage> callback);
}
