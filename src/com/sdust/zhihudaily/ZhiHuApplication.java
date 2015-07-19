package com.sdust.zhihudaily;

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
			sRespository = new RespositoryImp();
		}
		return sRespository;
	}
	
	
	
}