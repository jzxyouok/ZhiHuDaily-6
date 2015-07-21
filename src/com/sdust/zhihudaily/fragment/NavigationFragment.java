package com.sdust.zhihudaily.fragment;

import com.sdust.zhihudaily.NavigationDrawerCallback;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sdust.zhihudaily.R;
public class NavigationFragment extends Fragment implements NavigationDrawerCallback{
		private RecyclerView recyclerView;
	
	  @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	        return inflater.inflate(R.layout.fragment_navigation, container, false);
	    }
	  
	  @Override
	    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
	        super.onViewCreated(view, savedInstanceState);
	        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
	        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
	        recyclerView.setAdapter(mAdapter);
	    }
	  
	  @Override
		public void onNavigationDrawerItemSelected(int position) {
			
		}
}
