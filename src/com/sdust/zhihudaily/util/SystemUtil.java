package com.sdust.zhihudaily.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.app.Activity;
/**
 * 类名：SystemUtil
 * 说明：系统工具类
 */
public class SystemUtil {
	
	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
		
	}
	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
	}

}
