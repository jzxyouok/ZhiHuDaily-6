/**
 * �ļ�����CacheRespository.java
 * ʱ�䣺2015��7��19������10:10:38
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.repository.interfaces;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.bean.Themes;

/**
 * ������CacheRespository ˵����
 */
public interface CacheRepository {

	void getStartImage(int width, int height, Callback<StartImage> callback);

	void saveStartImage(int width, int height, DisplayImageOptions options,
			StartImage startImage);
	
	void saveThemes(Themes themes,String url);
	
	void getThemes(String url,Callback<Themes> callback);
	public interface Callback<T> {
		public void success(T t);

		public void failure(Exception e);
	}
}
