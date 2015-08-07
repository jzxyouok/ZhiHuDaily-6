/**
 * �ļ���NetRepository.java
 * ʱ�䣺2015��7��19������10:12:42
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.repository.interfaces;

import com.sdust.zhihudaily.model.DailyStories;
import com.sdust.zhihudaily.model.StartImage;
import com.sdust.zhihudaily.model.Themes;

/**
 * ����NetRepository ˵����
 */
public interface NetRepository {
	void getStartImage(int width,int height,Callback<StartImage> callback);
	void getThemes(Callback<Themes> callback);
	void getLatestDailyStories(Callback<DailyStories> callback);

	void getBeforeDailyStories(String date, Callback<DailyStories> callback);

	public interface Callback<T> {
		public void success(T t,String url);
		public void failure(Exception e,String url);
	}
}
