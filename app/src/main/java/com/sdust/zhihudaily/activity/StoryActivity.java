package com.sdust.zhihudaily.activity;

import android.os.Bundle;

import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.fragment.DailyStoriesFragment;
import com.sdust.zhihudaily.fragment.StoryFragment;

/**
 * Created by Kevin on 2015/8/8.
 */
public class StoryActivity extends  BaseAppCompatActivity {
    @Override
    protected int getContentViewLayoutId() {
        return  R.layout.activity_story;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(null);
        if (savedInstanceState == null) {
            String storyId = getIntent().getStringExtra(DailyStoriesFragment.EXTRA_STORY_ID);
            StoryFragment storyFragment = StoryFragment.newInstance(storyId);
            storyFragment.setToolBar(mActionBarToolbar);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, storyFragment, StoryFragment.TAG)
                    .commit();
        }
    }
}
