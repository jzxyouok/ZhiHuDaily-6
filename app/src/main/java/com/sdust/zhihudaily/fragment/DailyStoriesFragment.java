package com.sdust.zhihudaily.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.ZhiHuApplication;
import com.sdust.zhihudaily.adapter.DailyStoriesAdapter;
import com.sdust.zhihudaily.model.DailyStories;
import com.sdust.zhihudaily.repository.interfaces.Repository;
import com.sdust.zhihudaily.widget.LoadMoreRecyclerView;


public class DailyStoriesFragment extends BaseFragment {
    public static final String TAG = DailyStoriesFragment.class.getSimpleName();
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
        /**
         * 上拉加载更多，加载beforeDailyStories
         */
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
        /**
         * 下拉刷新，加载latestDailyStories
         */
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setAdapter(mAdapter);
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
    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
    private String mTitle;
    private int lastTitlePos = -1;
    private void changeActionBarTitle(int dy) {

    }

    private void loadMore() {

    }

    private void refresh() {
        isDataLoaded = false;

        ZhiHuApplication.getRepository().getLatestDailyStories(new Repository.Callback<DailyStories>() {
            @Override
            public void success(DailyStories dailyStories, boolean outDate) {
                isDataLoaded = true;
                mSwipeRefreshLayout.setRefreshing(false);
                mDate = dailyStories.getDate();
                mAdapter.setList(dailyStories);
            }

            @Override
            public void failure(Exception e) {
                isDataLoaded = false;
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), "刷新失败", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

}
