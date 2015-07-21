package com.sdust.zhihudaily.fragment;

import com.sdust.zhihudaily.NavigationDrawerCallback;
import com.sdust.zhihudaily.ZhiHuApplication;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.adapter.NavigationAdapter;

public class NavigationFragment extends Fragment implements
		NavigationDrawerCallback {
	private RecyclerView mRecyclerView;
	private NavigationAdapter mAdapter;
	private NavigationDrawerCallback mCallbacks;
	private int mCurrentSelectedPosition;
	private DrawerLayout mDrawerLayout;
	private boolean mFromSavedInstance;
	 private View mFragmentContainerView;
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
		
		if(savedInstanceState != null) {
			mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
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
	        if (mDrawerLayout != null) mDrawerLayout.closeDrawer(mFragmentContainerView);
	    }
	    
	    private void refresh() {
	    	 ZhiHuApplication.getRepository().getThemes(new Repository.Callback<Themes>() {
	             @Override
	             public void success(Themes themes, boolean outDate) {
	                 mThemes = themes.getOthers();
	                 mAdapter.setThemes(mThemes);
	             }

	             @Override
	             public void failure(Exception e) {
	                 e.printStackTrace();
	             }
	         });
	    }
	    
	    

}
