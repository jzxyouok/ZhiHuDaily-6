/**
 * �ļ�����FileUtils.java
 * ʱ�䣺2015��7��19������6:18:07
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.util;

import java.io.File;

import android.content.Context;
import android.os.Environment;

/**
 * ������FileUtils ˵����
 */
public class FileUtils {
	private static final String HTTP_CACHE_DIR = "httpcache";

	public static File getHttpCacheDir(Context context) {
		// �ж�SD���Ƿ���ڣ������Ƿ���ж�дȨ��
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return new File(context.getExternalCacheDir(), HTTP_CACHE_DIR);
		}
		return new File(context.getCacheDir(), HTTP_CACHE_DIR);
	}

}
