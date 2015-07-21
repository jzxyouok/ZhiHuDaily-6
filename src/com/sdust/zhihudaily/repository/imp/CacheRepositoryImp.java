/**
 * 文件名：CacheRespositoryImp.java
 * 时间：2015年7月19日上午10:13:04
 * 作者：Kevin
 */
package com.sdust.zhihudaily.repository.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.sdust.zhihudaily.bean.HTTPCache;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.bean.Themes;
import com.sdust.zhihudaily.db.CacheDao;
import com.sdust.zhihudaily.repository.interfaces.CacheRepository;
import com.sdust.zhihudaily.util.SharedPrefUtils;

/**
 * 类名：CacheRespositoryImp 说明：
 */
public class CacheRepositoryImp implements CacheRepository {

	private Context mContext;
	private CacheDao mCacheDao;
	private Gson mGson;
	private static DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	public CacheRepositoryImp(Context context) {
		mContext = context;
		mCacheDao = new CacheDao(context);
		mGson = new Gson();
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
			// 如果和之前的不一样时，将其缓存下来。留作下次的开始界面
			ImageLoader.getInstance().loadImage(startImage.getImg(),
					new ImageSize(width, height), options, null);
		}
	}

	private Exception getException(Class clazz) {
		return new Exception(clazz.getSimpleName() + " cache not found!");
	}

	@Override
	public void saveThemes(Themes themes, String url) {
		saveCacheToDB(themes, url);
	}

	private void saveCacheToDB(Object o, String url) {
		HTTPCache cache = new HTTPCache(url, mGson.toJson(o), Long.valueOf(df
				.format(Calendar.getInstance().getTimeInMillis())));
		mCacheDao.updateCache(cache);
	}

	@Override
	public void getThemes(String url, Callback<Themes> callback) {
		getDataObject(url, Themes.class, callback);
	}
	private <T> void getDataObject(String url, Class<T> classOfT, CacheRepository.Callback callback) {
        String json = mCacheDao.getCache(url).getResponse();
        T t = mGson.fromJson(json, classOfT);
        if (t != null) {
            callback.success(t);
        } else {
            callback.failure(getException(classOfT));
        }
    }
}
