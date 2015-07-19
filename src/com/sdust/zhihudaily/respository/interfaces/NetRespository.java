/**
 * �ļ�����NetRespository.java
 * ʱ�䣺2015��7��19������10:12:42
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.respository.interfaces;

/**
 * ������NetRespository ˵����
 */
public interface NetRespository {
	void getStartImage();

	public interface Callback<T> {
		public void success(T t);

		public void failure(Exception e);
	}
}
