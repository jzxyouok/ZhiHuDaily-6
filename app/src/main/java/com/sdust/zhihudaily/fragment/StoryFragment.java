package com.sdust.zhihudaily.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

/**
 * Created by Kevin on 2015/8/10.
 */
public class StoryFragment extends Fragment {
    public static final String TAG = StoryFragment.class.getSimpleName();

    private String mStoryId;
    private Toolbar mActionBarToolbar;

    public StoryFragment() {

    }

    /**
     * 无法通过重载构造器传递StoryId
     * @param storyId
     * @return
     */
    public static StoryFragment newInstance(String storyId) {
        StoryFragment fragment = new StoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DailyStoriesFragment.EXTRA_STORY_ID, storyId);
        fragment.setArguments(bundle);
        return fragment;
    }
    public void setToolBar(Toolbar toolbar) {
        this.mActionBarToolbar = toolbar;
    }


}
