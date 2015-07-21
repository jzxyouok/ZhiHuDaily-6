/**
 * 文件名：MainActivity.java
 * 时间：2015年7月19日下午8:38:59
 * 作者：Kevin
 */
package com.sdust.zhihudaily.activity;

import com.sdust.zhihudaily.NavigationDrawerCallback;
import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.fragment.NavigationFragment;

import android.os.Bundle;
import android.text.method.MultiTapKeyListener;

/**
 * 类名：MainActivity 说明：主页
 */
public class MainActivity extends BaseActivity implements NavigationDrawerCallback{

	private NavigationFragment mNavigationFragment;

	private String mTitle = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setUpDrawer();
		if (savedInstanceState == null) {
			mNavigationFragment.selectItem(NavigationFragment
					.getDefaultNavDrawerItem());
		}
		
		 private void setUpDrawer() {
		        mNavigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		        mTitle = getTitle();
		        mNavigationFragment.setup(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mActionBarToolbar);
		    }
	}

	@Override
	protected int getContentViewLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// TODO Auto-generated method stub
		
	}

}
