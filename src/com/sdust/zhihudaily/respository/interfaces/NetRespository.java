/**
 * 文件名：NetRespository.java
 * 时间：2015年7月19日上午10:12:42
 * 作者：Kevin
 */
package com.sdust.zhihudaily.respository.interfaces;

import com.sdust.zhihudaily.bean.StartImage;

/**
 * 类名：NetRespository 说明：
 */
public interface NetRespository {
	void getStartImage(int height,int width,Callback<StartImage> callback);

	public interface Callback<T> {
		public void success(T t);
		public void failure(Exception e);
	}
}
