package com.sdust.zhihudaily.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.utils.L;
import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.adapter.DailyStoriesAdapter;
import com.sdust.zhihudaily.widget.LoadMoreRecyclerView;

/**
 * Created by Kevin on 2015/8/7.
 */
public class ThemeStoriesFragment extends BaseFragment {
    public static final String TAG = ThemeStoriesFragment.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private DailyStoriesAdapter mAdapter;
    private LoadMoreRecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private String mDate;

    private boolean isDataLoaded;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new DailyStoriesAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_stories,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (LoadMoreRecyclerView)view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setOnLoadMoreListener(new LoadMoreRecyclerView.onLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMore();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                changeActionBarTitle(dy);
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }
    @Override
    public void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
        if (mRecyclerView != null) {
            L.i(TAG, "recyclerView != null");
            View view = mRecyclerView.findViewById(R.id.viewPager);
            if (view != null) {
                L.i(TAG, "MyViewPager startAutoScroll");
                ((MyViewPager) view).startAutoScroll();
            }
        }
    }

    @Override
    public void onPause() {
        L.i(TAG, "onPause");
        super.onPause();
        if (recyclerView != null) {
            L.i(TAG, "recyclerView != null");
            View view = recyclerView.findViewById(R.id.viewPager);
            if (view != null) {
                L.i(TAG, "MyViewPager stopAutoScroll");
                ((MyViewPager) view).stopAutoScroll();
            }
        }
    }
    private String mTitle;
    private int lastTitlePos = -1;
    private void changeActionBarTitle(int dy) {
        int position = mLayoutManager.findFirstVisibleItemPosition();
        if (lastTitlePos == position) {
            return;
        }
        DailyStoriesAdapter.Item item = mAdapter.getItem(position);
        int type = item.getType();
        if (type == DailyStoriesAdapter.Type.TYPE_HEADER) {
            mTitle = getString(R.string.title_activity_main);
        } else if (dy > 0 && type == DailyStoriesAdapter.Type.TYPE_DATE) {
            mTitle = DateViewHolder.getDate(item.getDate(), getActivity());
        } else if (dy < 0) {
            mTitle = DateViewHolder.getDate(mAdapter.getTitleBeforePosition(position), getActivity());
        }
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mTitle);
        lastTitlePos = position;
    }
}
