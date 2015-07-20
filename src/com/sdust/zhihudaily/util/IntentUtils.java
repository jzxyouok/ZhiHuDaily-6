/**
 * �ļ�����IntentUtils.java
 * ʱ�䣺2015��7��19������8:35:19
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.util;

import com.sdust.zhihudaily.activity.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * ������IntentUtils
 * ˵����
 */
public class IntentUtils {
	
	public static void intentToMainActivity(Activity activity) {
		Intent intent = new Intent(activity,MainActivity.class);
		activity.startActivity(intent);
		activity.finish();
		activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
		
	}

}
