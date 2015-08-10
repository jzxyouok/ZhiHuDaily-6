package com.sdust.zhihudaily.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.ZhiHuApplication;
import com.sdust.zhihudaily.model.Story;
import com.sdust.zhihudaily.repository.interfaces.Repository;
import com.sdust.zhihudaily.widget.AvatarsView;
import com.sdust.zhihudaily.widget.ScrollPullDownHelper;
import com.sdust.zhihudaily.widget.StoryHeaderView;

import java.lang.ref.SoftReference;

import butterknife.ButterKnife;
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

    private SoftReference<WebView> refWebView;//加载webview可能会造成OOM，所以将其设置为软引用，防止OOM

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        storyHeaderView = StoryHeaderView.newInstance(container);
        avatarsView = (AvatarsView) inflater.inflate(R.layout.layout_avatars, container, false);
        refWebView = new SoftReference<WebView>(new WebView(getActivity()));
        if (isWebViewOK()) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            refWebView.get().setLayoutParams(lp);
        }
        return inflater.inflate(R.layout.fragment_story, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);

        scrollView.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);

        if (isWebViewOK()) {
            llWebViewContainer.addView(refWebView.get());
        }
    }
    private boolean isWebViewOK() {
        return refWebView != null && refWebView.get() != null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refresh();
    }

    private void refresh() {
        ZhiHuApplication.getRepository().getStoryDetail(mStoryId, new Repository.Callback<Story>() {
            @Override
            public void success(Story story, boolean outDate) {
                if (getActivity() == null) {
                    return;
                }
                progressBar.setVisibility(View.GONE);
                mStory = story;
                //bindData(story);
            }

            @Override
            public void failure(Exception e) {
                progressBar.setVisibility(View.GONE);
                e.printStackTrace();
            }
        });
    }

}
