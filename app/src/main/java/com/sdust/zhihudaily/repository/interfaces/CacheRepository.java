
package com.sdust.zhihudaily.repository.interfaces;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.model.StartImage;


public interface CacheRepository {

	void getStartImage(Callback<StartImage> callback);

	void saveStartImage(int width, int height, DisplayImageOptions options,
			StartImage startImage);

	public interface Callback<T> {
		public void success(T t);

		public void failure(Exception e);
	}
}
