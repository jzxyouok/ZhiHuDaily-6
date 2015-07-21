/**
 * 文件名：NetRespository.java
 * 时间：2015年7月19日上午10:12:42
 * 作者：Kevin
 */
package com.sdust.zhihudaily.repository.interfaces;

import android.R.integer;

import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.bean.Themes;

/**
 * 类名：NetRepository 说明：
 */
public interface NetRepository {
	void getStartImage(int width,int height,Callback<StartImage> callback);
	void getThemes(Callback<Themes> themes);
	public interface Callback<T> {
		public void success(T t,String url);
		public void failure(Exception e,String url);
	}
}
