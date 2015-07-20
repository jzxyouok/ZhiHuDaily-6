/**
 * �ļ�����CacheRespositoryImp.java
 * ʱ�䣺2015��7��19������10:13:04
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.respository.imp;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.respository.interfaces.CacheRespository;
import com.sdust.zhihudaily.util.SharedPrefUtils;

/**
 * ������CacheRespositoryImp ˵����
 */
public class CacheRespositoryImp implements CacheRespository {

	private Context mContext;

	public CacheRespositoryImp(Context context) {
		mContext = context;
	}

	@Override
	public void getStartImage(int width, int height,
			Callback<StartImage> callback) {
		String startOldJsonStr = SharedPrefUtils.getStartJson(mContext);
		if (!TextUtils.isEmpty(startOldJsonStr)) {
			StartImage startImage = new Gson().fromJson(startOldJsonStr,
					StartImage.class);
			callback.success(startImage);
		} else {
			callback.failure(getException(StartImage.class));
		}

	}

	@Override
	public void saveStartImage(int width, int height,
			DisplayImageOptions options, StartImage startImage) {
		String oldJsonStr = SharedPrefUtils.getStartJson(mContext);
		StartImage old = new Gson().fromJson(oldJsonStr, StartImage.class);

		if (old == null || !startImage.getImg().equals(old.getImg())) {
			SharedPrefUtils.setStartJson(mContext,
					new Gson().toJson(startImage));
			// �����֮ǰ�Ĳ�һ��ʱ�����仺�������������´εĿ�ʼ����
			ImageLoader.getInstance().loadImage(startImage.getImg(),
					new ImageSize(width, height), options, null);
		}
	}

	private Exception getException(Class clazz) {
		return new Exception(clazz.getSimpleName() + " cache not found!");
	}

}
