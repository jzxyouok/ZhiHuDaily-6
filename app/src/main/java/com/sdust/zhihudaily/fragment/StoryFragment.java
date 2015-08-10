package com.sdust.zhihudaily.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.model.Story;
import com.sdust.zhihudaily.widget.AvatarsView;
import com.sdust.zhihudaily.widget.ScrollPullDownHelper;
import com.sdust.zhihudaily.widget.StoryHeaderView;

import java.lang.ref.SoftReference;

import butterknife.InjectView;

/**
 * Created by Kevin on 2015/8/10.
 */
public class StoryFragment extends Fragment {
    public static final String TAG = StoryFragment.class.getSimpleName();

    @InjectView(R.id.pb)
    ProgressBar progressBar;

    @InjectView(R.id.rl_container_header)
    RelativeLayout rlStoryHeader;

    @InjectView(R.id.scrollView)
    ScrollView scrollView;

    @InjectView(R.id.webViewContainer)
    LinearLayout llWebViewContainer;

    @InjectView(R.id.avatarsView)
    AvatarsView avatarsView;

    @InjectView(R.id.spaceView)
    View spaceView;

    private SoftReference<WebView> refWebView;

    private StoryHeaderView storyHeaderView;

    private DisplayImageOptions mOptions;

    private ScrollPullDownHelper mScrollPullDownHelper;

    private String mStoryId;

    private Story mStory;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mStoryId = getArguments().getString(DailyStoriesFragment.EXTRA_STORY_ID);
        }

        mScrollPullDownHelper = new ScrollPullDownHelper();

        this.mOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

}
