package com.sdust.zhihudaily.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.adapter.ThemeStoriesAdapter;
import com.sdust.zhihudaily.util.LogUtils;
import com.sdust.zhihudaily.widget.LoadMoreRecyclerView;

/**
 * Created by Kevin on 2015/8/7.
 */
public class ThemeStoriesFragment extends BaseFragment {
    public static final  String TAG = ThemeStoriesFragment.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private LoadMoreRecyclerView mRecyclerView;

    private ThemeStoriesAdapter mAdapter;

    private String mThemeId;

    private String mLastStoryId;

    private boolean isDataLoaded;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mAdapter = new ThemeStoriesAdapter();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.i(TAG, getThemeNumber() + " : " + getThemeId());
        return inflater.inflate(R.layout.fragment_theme_stories, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        mRecyclerView.setOnLoadMoreListener(new LoadMoreRecyclerView.onLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mRecyclerView.setLoadingMore(true);
                loadMore();
            }

            @Override
            public void onScrolled(RecyclerView mRecyclerView, int dx, int dy) {

            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mThemeId = getThemeId();
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (!isDataLoaded) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    refresh();
                }
            }
        });
    }

}
