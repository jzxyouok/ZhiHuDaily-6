/**
 * �ļ���IntentUtils.java
 * ʱ�䣺2015��7��19������8:35:19
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.util;

import android.app.Activity;
import android.content.Intent;

import com.sdust.zhihudaily.activity.NavigationDrawerActivity;


public class IntentUtils {
	
	public static void intentToMainActivity(Activity activity) {
		Intent intent = new Intent(activity,NavigationDrawerActivity.class);
		activity.startActivity(intent);
		activity.finish();
		activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
		
	}

}
