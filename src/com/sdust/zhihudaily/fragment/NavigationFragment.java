package com.sdust.zhihudaily.fragment;

import com.sdust.zhihudaily.NavigationDrawerCallback;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.adapter.NavigationAdapter;
public class NavigationFragment extends Fragment implements NavigationDrawerCallback{
		private RecyclerView mRecyclerView;
		private NavigationAdapter mAdapter;
	  @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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
		public void onNavigationDrawerItemSelected(int position) {
			
		}
}
