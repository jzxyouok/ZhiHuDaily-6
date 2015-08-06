package com.sdust.zhihudaily;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.sdust.zhihudaily.repository.imp.RepositoryImp;
import com.sdust.zhihudaily.repository.interfaces.Repository;

/*

 */
public class ZhiHuApplication extends Application{
	
	private static Context applicationContext;
	
	private static Repository sRespository;
	@Override
	public void onCreate() {
		super.onCreate();
		applicationContext = getApplicationContext();
		initImageLoader(getApplicationContext());
		
	}
	
	
	public static Context getContext() {
		return applicationContext;
	}
	public static Repository getRespository() {
		if(sRespository == null) {
			sRespository = new RepositoryImp(applicationContext);
		}
		return sRespository;
	}
	
	
	 private void initImageLoader(final Context context) {
	        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
	                .threadPriority(Thread.NORM_PRIORITY - 2)
	                .denyCacheImageMultipleSizesInMemory()
	                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
	                .diskCacheSize(Constants.IMAGE_CACHE_SIZE) // 50 Mb
	                .tasksProcessingOrder(QueueProcessingType.LIFO)
	                .build();
	        ImageLoader.getInstance().init(config);
	    }
	
	
	
}