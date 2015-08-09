/**
 * �ļ���Repository.java
 * ʱ�䣺2015��7��19������9:52:08
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.repository.interfaces;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.model.DailyStories;
import com.sdust.zhihudaily.model.StartImage;
import com.sdust.zhihudaily.model.Theme;
import com.sdust.zhihudaily.model.Themes;


public interface Repository {

	public interface Callback<T> {
		public void success(T t,boolean outDate);

		public void failure(Exception e);
	}
	void getThemes(Callback<Themes> callback);

	void getStartImage(int width, int height, DisplayImageOptions options,
			Callback<StartImage> callback);
	void getLatestDailyStories(Callback<DailyStories> callback);

	void getBeforeDailyStories(String date, Callback<DailyStories> callback);

	void getThemeStories(String themeId, Callback<Theme> callback);

	void getBeforeThemeStories(String themeId, String storyId, Callback<Theme> callback);

}
