
package com.sdust.zhihudaily.repository.interfaces;

import com.sdust.zhihudaily.model.DailyStories;
import com.sdust.zhihudaily.model.StartImage;
import com.sdust.zhihudaily.model.Story;
import com.sdust.zhihudaily.model.Theme;
import com.sdust.zhihudaily.model.Themes;


public interface NetRepository {
	void getStartImage(int width,int height,Callback<StartImage> callback);

	void getThemes(Callback<Themes> callback);

	void getLatestDailyStories(Callback<DailyStories> callback);

	void getBeforeDailyStories(String date, Callback<DailyStories> callback);

	void getThemeStories(String themeId, Callback<Theme> callback);

	void getBeforeThemeStories(String themeId, String storyId, Callback<Theme> callback);

	void getStoryDetail(String storyId, Callback<Story> callback);


	public interface Callback<T> {
		public void success(T t,String url);
		public void failure(Exception e,String url);
	}
}
