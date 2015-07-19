/**
 * �ļ�����CacheRespository.java
 * ʱ�䣺2015��7��19������10:10:38
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.respository.interfaces;

import com.sdust.zhihudaily.bean.StartImage;

/**
 * ������CacheRespository ˵����
 */
public interface CacheRespository {

	void getStartImage(int height,int width,Callback<StartImage> callback);

	public interface Callback<T> {
		public void success(T t);

		public void failure(Exception e);
	}
}
