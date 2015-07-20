/**
 * 文件名：IntentUtils.java
 * 时间：2015年7月19日下午8:35:19
 * 作者：Kevin
 */
package com.sdust.zhihudaily.util;

import com.sdust.zhihudaily.activity.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * 类名：IntentUtils
 * 说明：
 */
public class IntentUtils {
	
	public static void intentToMainActivity(Activity activity) {
		Intent intent = new Intent(activity,MainActivity.class);
		activity.startActivity(intent);
		activity.finish();
		activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
		
	}

}
