/**
 * �ļ�����MainActivity.java
 * ʱ�䣺2015��7��19������8:38:59
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.activity;

import com.sdust.zhihudaily.R;

import android.os.Bundle;

/**
 * ������MainActivity
 * ˵����
 */
public class MainActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected int getContentViewLayoutId() {
		return 0;
	}

}
