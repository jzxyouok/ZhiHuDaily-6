/**
 * �ļ�����MainActivity.java
 * ʱ�䣺2015��7��19������8:38:59
 * ���ߣ�Kevin
 */
package com.sdust.zhihudaily.activity;

import com.sdust.zhihudaily.NavigationDrawerCallback;
import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.fragment.NavigationFragment;

import android.os.Bundle;
import android.text.method.MultiTapKeyListener;

/**
 * ������MainActivity ˵������ҳ
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
