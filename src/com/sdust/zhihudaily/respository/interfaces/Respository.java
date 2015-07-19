/**
 * �ļ�����Respository.java
 * ʱ�䣺2015��7��19������9:52:08
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.respository.interfaces;

import com.sdust.zhihudaily.bean.StartImage;

import android.provider.MediaStore.Video;

/**
 * ������Respository ˵����
 */
public interface Respository {
	void getStartImage(Callback<StartImage> callback) ;


	public interface Callback<T> {
		public void success(T t);

		public void failure(Exception e);
	}
}
