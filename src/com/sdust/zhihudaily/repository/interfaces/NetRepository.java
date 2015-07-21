/**
 * �ļ�����NetRespository.java
 * ʱ�䣺2015��7��19������10:12:42
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.repository.interfaces;

import com.sdust.zhihudaily.bean.StartImage;

/**
 * ������NetRepository ˵����
 */
public interface NetRepository {
	void getStartImage(int width,int height,Callback<StartImage> callback);

	public interface Callback<T> {
		public void success(T t);
		public void failure(Exception e);
	}
}
