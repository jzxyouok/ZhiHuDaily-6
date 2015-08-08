package com.sdust.zhihudaily.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.sdust.zhihudaily.model.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2015/8/7.
 */
public class DailyStoriesAdapter extends RecyclerView.Adapter {
    public static final String TAG = DailyStoriesAdapter.class.getSimpleName();
    protected  List<Item> mItems;
    protected List<Item> mTmpItem;

    public class Type{
        public static final int TYPE_HEADER = 0;
        public static final int TYPE_DATE = 1;
        public static final int TYPE_STORY = 2;
    }
    public DailyStoriesAdapter() {
        mItems = new ArrayList<Item>();
        mTmpItem = new ArrayList<Item>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
    public static class Item {
        private int type;
        private String date;
        private Story story;
        private List<Story> stories;//Header包含的几个滚动的文章

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Story getStory() {
            return story;
        }

        public void setStory(Story story) {
            this.story = story;
        }

        public List<Story> getStories() {
            return stories;
        }

        public void setStories(List<Story> stories) {
            this.stories = stories;
        }
    }
}
