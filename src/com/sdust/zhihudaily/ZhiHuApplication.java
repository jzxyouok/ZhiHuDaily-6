package com.sdust.zhihudaily;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.sdust.zhihudaily.respository.imp.RespositoryImp;
import com.sdust.zhihudaily.respository.interfaces.Respository;

import android.app.Application;
import android.content.Context;

public class ZhiHuApplication extends Application{
	
	private static Context applicationContext;
	
	private static Respository sRespository;
	@Override
	public void onCreate() {
		super.onCreate();
		applicationContext = getApplicationContext();
		
		
	}
	
	public static Respository getRespository() {
		if(sRespository == null) {
			sRespository = new RespositoryImp(applicationContext);
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