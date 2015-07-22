package com.sdust.zhihudaily.fragment;

import java.util.List;

import com.sdust.zhihudaily.NavigationDrawerCallback;
import com.sdust.zhihudaily.ZhiHuApplication;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.adapter.NavigationAdapter;
import com.sdust.zhihudaily.bean.Theme;
import com.sdust.zhihudaily.bean.Themes;
import com.sdust.zhihudaily.repository.interfaces.Repository;

public class NavigationFragment extends Fragment implements
		NavigationDrawerCallback {
	private RecyclerView mRecyclerView;
	private NavigationAdapter mAdapter;
	private NavigationDrawerCallback mCallbacks;
	private int mCurrentSelectedPosition;
	private DrawerLayout mDrawerLayout;
	private boolean mFromSavedInstance;
	private View mFragmentContainerView;
	private List<Theme> mThemes;
	private ActionBarDrawerToggle mDrawerToggle;
	private static final String STATE_SELECTED_POSITION = "selected_navigation_position";

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallbacks = (NavigationDrawerCallback) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					"Activity must implement NavigationDrawerCallbacks.");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		if (savedInstanceState != null) {
			mCurrentSelectedPosition = savedInstanceState
					.getInt(STATE_SELECTED_POSITION);
			mFromSavedInstance = true;
		}
		mAdapter = new NavigationAdapter();
		mAdapter.setNavigationDrawerCallbacks(this);

	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_navigation, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRecyclerView.setAdapter(mAdapter);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
		getThemes();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		if (position == mCurrentSelectedPosition) {
			closeDrawer();
			return;
		}
		closeDrawer();
		mCurrentSelectedPosition = position;
		if (mCallbacks != null) {
			mCallbacks.onNavigationDrawerItemSelected(position);
		}
	}

	public void closeDrawer() {
		if (mDrawerLayout != null)
			mDrawerLayout.closeDrawer(mFragmentContainerView);
	}

	private void getThemes() {
		ZhiHuApplication.getRepository().getThemes(
				new Repository.Callback<Themes>() {

					@Override
					public void success(Themes t) {
						mThemes = t.getOthers();
						mAdapter.setThemes(mThemes);
					}

					@Override
					public void failure(Exception e) {
						e.printStackTrace();
					}

				});
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public static int getDefaultNavDrawerItem() {
		return 0;
	}

	public void selectItem(int position) {
		if (position == mCurrentSelectedPosition) {
			closeDrawer();
			return;
		}
		mAdapter.selectPosition(position);
	}

	public void setup(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
		mFragmentContainerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;
		mDrawerLayout.setStatusBarBackground(R.color.style_color_primary);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
				toolbar, R.string.navigation_drawer_open,
				R.string.navigation_drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				if (!isAdded())
					return;
				getActivity().supportInvalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				if (!isAdded())
					return;
				/*
				 * if (!mUserLearnedDrawer) { mUserLearnedDrawer = true;
				 * SharedPrefUtils.markUserLearnedDrawer(getActivity()); }
				 */
				getActivity().supportInvalidateOptionsMenu(); // calls

			}
		};

		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

}
