/**
 * 文件名：CacheRespositoryImp.java
 * 时间：2015年7月19日上午10:13:04
 * 作者：Kevin
 */
package com.sdust.zhihudaily.respository.imp;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.respository.interfaces.CacheRespository;
import com.sdust.zhihudaily.util.SharedPrefUtils;

/**
 * 类名：CacheRespositoryImp
 * 说明：
 */
public class CacheRespositoryImp implements CacheRespository{
	
	private Context mContext;
	
	public CacheRespositoryImp(Context context) {
		mContext = context;
	}

	@Override
	public void getStartImage(int height, int width,
			Callback<StartImage> callback) {
		String startOldJsonStr = SharedPrefUtils.getStartJson(mContext);
		if(!TextUtils.isEmpty(startOldJsonStr)) {
			 StartImage startImage = new Gson().fromJson(startOldJsonStr, StartImage.class);
	         callback.success(startImage);
		}else{
			 callback.failure(getException(StartImage.class));
		}
		
	}


	private Exception getException(Class clazz) {
        return new Exception(clazz.getSimpleName() + " cache not found!");
    }
}
