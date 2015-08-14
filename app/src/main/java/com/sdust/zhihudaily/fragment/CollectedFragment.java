package com.sdust.zhihudaily.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.adapter.CollectedAdapter;
import com.sdust.zhihudaily.db.CollectedDao;
import com.sdust.zhihudaily.model.Story;
import com.sdust.zhihudaily.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Kevin on 2015/8/11.
 */
public class CollectedFragment extends Fragment {

    private CollectedAdapter mAdapter;
    @InjectView(R.id.recyclerView)
    LoadMoreRecyclerView mRecyclerView;
    private int mItemCount;


    private List<Story> mCollectedStories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState == null) {
            mAdapter = new CollectedAdapter();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collected, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

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
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                CollectedDao dao = new CollectedDao(getActivity());
                mCollectedStories = dao.getAllCollected();
                loadFirst();
            }
        });


    }

    private void loadFirst() {
        if (mCollectedStories.size() <= 20) {
            mAdapter.setStories(mCollectedStories);
        } else {
            List<Story> stories = new ArrayList<Story>();
            for (int i = 0; i < 20; i++) {
                stories.add(mCollectedStories.get(i));
            }
            mAdapter.setStories(stories);
            mAdapter.notifyDataSetChanged();
            mItemCount = 20;
        }
    }

    private void loadMore() {
        if (mCollectedStories.size() - mItemCount >= 10) {
            for (int i = mItemCount; i < (mItemCount + 10); i++) {
                mAdapter.appendList(mCollectedStories.get(i));
            }
            mAdapter.notifyItemRangeInserted(mItemCount, 10);
            mItemCount += 10;
        } else {
            for (int i = mItemCount; i < mCollectedStories.size(); i++) {
                mAdapter.appendList(mCollectedStories.get(i));
            }
            int count = mCollectedStories.size() - mItemCount;
            mAdapter.notifyItemRangeInserted(mItemCount, count);
            mItemCount += count;
        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_collected, menu);
    }

}
