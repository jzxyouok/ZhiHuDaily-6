package com.sdust.zhihudaily.fragment;

import android.app.Activity;
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
import com.sdust.zhihudaily.adapter.NavigationDrawerAdapter;
import com.sdust.zhihudaily.interfaces.NavigationDrawerCallbacks;
import com.sdust.zhihudaily.model.Theme;

import java.util.List;


public class NavigationFragment extends Fragment implements NavigationDrawerCallbacks {
    public static final String TAG = NavigationFragment.class.getSimpleName();


    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";



    /**
     * 指向NavigationActivity的引用
     */
    private NavigationDrawerCallbacks mCallbacks;


    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;

    private int mCurrentSelectedPosition = -1;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;
    private NavigationDrawerAdapter mDrawerAdapter;
    private List<Theme> mThemes;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }
        mDrawerAdapter = new NavigationDrawerAdapter();
        mDrawerAdapter.setNavigationCallbacks(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    private RecyclerView mRecyclerView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//设置RecyclerView为竖着的
        mRecyclerView.setAdapter(mDrawerAdapter);
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


    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public void openDrawer() {
        if (mDrawerLayout != null) mDrawerLayout.openDrawer(mFragmentContainerView);
    }

    public void closeDrawer() {
        if (mDrawerLayout != null) mDrawerLayout.closeDrawer(mFragmentContainerView);
    }


    public void setup(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerLayout.setStatusBarBackground(R.color.style_color_primary);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);


       mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                toolbar,                          /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) return;
                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) return;
                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();//添加导航的按钮
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

}
