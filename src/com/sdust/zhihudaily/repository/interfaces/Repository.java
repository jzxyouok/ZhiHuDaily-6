/**
 * �ļ�����Respository.java
 * ʱ�䣺2015��7��19������9:52:08
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.repository.interfaces;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.bean.Themes;
import com.sdust.zhihudaily.repository.interfaces.Repository.Callback;

import android.provider.MediaStore.Video;

/**
 * ������Repository ˵����
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
