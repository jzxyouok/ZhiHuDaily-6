/**
 * �ļ���Repository.java
 * ʱ�䣺2015��7��19������9:52:08
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.repository.interfaces;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.model.StartImage;


public interface Repository {

	public interface Callback<T> {
		public void success(T t,boolean outDate);

		public void failure(Exception e);
	}

	void getStartImage(int width, int height, DisplayImageOptions options,
			Callback<StartImage> callback);
}
