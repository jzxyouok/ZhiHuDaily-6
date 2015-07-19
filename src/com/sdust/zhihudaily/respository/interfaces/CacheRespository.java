/**
 * 文件名：CacheRespository.java
 * 时间：2015年7月19日上午10:10:38
 * 作者：Kevin
 */
package com.sdust.zhihudaily.respository.interfaces;

/**
 * 类名：CacheRespository 说明：
 */
public interface CacheRespository {

	void getStartImage();

	public interface Callback<T> {
		public void success(T t);

		public void failure(Exception e);
	}
}
