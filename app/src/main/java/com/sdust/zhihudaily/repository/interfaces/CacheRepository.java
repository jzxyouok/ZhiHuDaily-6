
package com.sdust.zhihudaily.repository.interfaces;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.model.DailyStories;
import com.sdust.zhihudaily.model.StartImage;
import com.sdust.zhihudaily.model.Themes;


public interface CacheRepository {

	void getStartImage(Callback<StartImage> callback);
	void getThemes(String url, Callback<Themes> callback);

	void saveThemes(Themes themes, String url);
	void getLatestDailyStories(String url, Callback<DailyStories> callback);

	void saveLatestDailyStories(DailyStories dailyStories, String url);

	void getBeforeDailyStories(String url, Callback<DailyStories> callback);

	void saveBeforeDailyStories(DailyStories dailyStories, String url);

	void saveStartImage(int width, int height, DisplayImageOptions options,
			StartImage startImage);

	public interface Callback<T> {
		public void success(T t);

		public void failure(Exception e);
	}
}
