/**
 * 文件名：FileUtils.java
 * 时间：2015年7月19日下午6:18:07
 * 作者：Kevin
 */
package com.sdust.zhihudaily.util;

import java.io.File;

import android.content.Context;
import android.os.Environment;

/**
 * 类名：FileUtils 说明：
 */
public class FileUtils {
	private static final String HTTP_CACHE_DIR = "httpcache";

	public static File getHttpCacheDir(Context context) {
		// 判断SD卡是否存在，并且是否具有读写权限
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return new File(context.getExternalCacheDir(), HTTP_CACHE_DIR);
		}
		return new File(context.getCacheDir(), HTTP_CACHE_DIR);
	}

}
