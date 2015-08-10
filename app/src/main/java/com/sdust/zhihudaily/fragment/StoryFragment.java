package com.sdust.zhihudaily.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.ZhiHuApplication;
import com.sdust.zhihudaily.model.Editor;
import com.sdust.zhihudaily.model.Story;
import com.sdust.zhihudaily.repository.interfaces.Repository;
import com.sdust.zhihudaily.util.IntentUtils;
import com.sdust.zhihudaily.util.WebUtils;
import com.sdust.zhihudaily.widget.AvatarsView;
import com.sdust.zhihudaily.widget.ScrollPullDownHelper;
import com.sdust.zhihudaily.widget.StoryHeaderView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

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
     *
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
    @Override
    public void onResume() {
        super.onResume();
        if (isWebViewOK()) {
            refWebView.get().onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isWebViewOK()) {
            refWebView.get().onPause();
        }
    }

    /**
     * 释放掉
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isWebViewOK()) {
            refWebView.get().removeAllViews();
            refWebView.get().destroy();
            llWebViewContainer.removeView(refWebView.get());
            refWebView = null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_story, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            if (mStory != null) {
                IntentUtils.share(getActivity(), mStory);
            }
        }
        return super.onOptionsItemSelected(item);
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
                bindData(story);
            }

            @Override
            public void failure(Exception e) {
                progressBar.setVisibility(View.GONE);
                e.printStackTrace();
            }
        });
    }

    private void bindData(Story story) {
        boolean hasImage = !TextUtils.isEmpty(story.getImage());
        bindHeaderView(hasImage);
        bindAvatarsView();
        bindWebView(hasImage);
    }

    private void bindHeaderView(boolean hasImage) {
        // bind header view
        if (hasImage) {
            if (mActionBarToolbar != null) {
                mActionBarToolbar.getBackground().setAlpha(0);
            }
            spaceView.setVisibility(View.VISIBLE);
            rlStoryHeader.addView(storyHeaderView);
            storyHeaderView.bindData(mStory.getTitle(), mStory.getImageSource(), mStory.getImage(), mOptions);
        } else {
            spaceView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mActionBarToolbar.getHeight()));
        }
    }

    private void bindAvatarsView() {
        List<Editor> recommenders = mStory.getRecommenders();
        if (recommenders != null && recommenders.size() > 0) {
            avatarsView.setVisibility(View.VISIBLE);
            List<String> avatars = new ArrayList<>(recommenders.size());
            for (Editor editor : recommenders) {
                avatars.add(editor.getAvatar());
            }
            avatarsView.bindData(getString(R.string.avatar_title_referee), avatars);
        } else {
            avatarsView.setVisibility(View.GONE);
        }
    }

    private void bindWebView(boolean hasImage) {
        if (TextUtils.isEmpty(mStory.getBody())) {
            WebSettings settings = refWebView.get().getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setAppCacheEnabled(true);
            refWebView.get().setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            refWebView.get().loadUrl(mStory.getShareUrl());
        } else {
            String data = WebUtils.BuildHtmlWithCss(mStory.getBody(), mStory.getCssList(), false);
            refWebView.get().loadDataWithBaseURL(WebUtils.BASE_URL, data, WebUtils.MIME_TYPE, WebUtils.ENCODING, WebUtils.FAIL_URL);
            if (hasImage) {
                addSrollListener();
            }
        }
    }

    private void addSrollListener() {
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (!isAdded()) {
                    return;
                }
                changeHeaderPosition();
                changeToolbarAlpha();
            }
        });
    }

    private void changeHeaderPosition() {
        int scrollY = scrollView.getScrollY();

        float storyHeaderViewHeight = getStoryHeaderViewHeight();
        if (scrollY < 0) {
            storyHeaderViewHeight += Math.abs(scrollY);
        }
        storyHeaderView.getLayoutParams().height = (int) storyHeaderViewHeight;

        int headerScrollY = (scrollY > 0) ? (scrollY / 2) : 0;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            storyHeaderView.setScrollY(headerScrollY);
            storyHeaderView.requestLayout();
        }
    }

    private void changeToolbarAlpha() {

        int scrollY = scrollView.getScrollY();
        int storyHeaderViewHeight = getStoryHeaderViewHeight();
        int toolbarHeight = mActionBarToolbar.getHeight();
        float contentHeight = storyHeaderViewHeight - toolbarHeight;
        float ratio = Math.min(scrollY / contentHeight, 1.0f);
        mActionBarToolbar.getBackground().setAlpha((int) (ratio * 0xFF));
        if (scrollY <= contentHeight) {
            mActionBarToolbar.setY(0f);
            return;
        }

        if (scrollY + scrollView.getHeight() > llWebViewContainer.getMeasuredHeight() + storyHeaderViewHeight) {
            return;
        }

        boolean isPullingDown = mScrollPullDownHelper.onScrollChanged(scrollY);
        float toolBarPositionY = isPullingDown ? 0 : (contentHeight - scrollY);
        if (scrollY < storyHeaderViewHeight + toolbarHeight) {
            toolBarPositionY = storyHeaderViewHeight - scrollY - toolbarHeight;
        }

        mActionBarToolbar.setY(toolBarPositionY);
    }

    private int getStoryHeaderViewHeight() {
        return getResources().getDimensionPixelSize(R.dimen.view_header_story_height);
    }


}
