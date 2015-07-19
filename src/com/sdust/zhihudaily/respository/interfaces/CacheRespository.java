/**
 * �ļ�����CacheRespository.java
 * ʱ�䣺2015��7��19������10:10:38
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.respository.interfaces;

/**
 * ������CacheRespository ˵����
 */
public interface CacheRespository {

	void getStartImage();

	public interface Callback<T> {
		public void success(T t);

		public void failure(Exception e);
	}
}
