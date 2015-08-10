package com.sdust.zhihudaily.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.sdust.zhihudaily.model.Story;
import com.sdust.zhihudaily.model.Theme;

import java.util.List;

/**
 * Created by Kevin on 2015/8/9.
 */
public class ThemeStoriesAdapter extends RecyclerView.Adapter {

    public static final class Type{
        public static final int TYPE_HEADER = 0;
        
        public static final int TYPE_AVATARS = 1;
        
        public static final int TYPE_ITEM = 2;
    }
    
    
    @Override
    public int getItemViewType(int position) {
        if (!TextUtils.isEmpty(mTheme.getBackground()) && position == 0) {
            return Type.TYPE_HEADER;
        } else if (mTheme.getEditors() != null && mTheme.getEditors().size() > 0 && position == 1) {
            return Type.TYPE_AVATARS;
        } else {
            return Type.TYPE_ITEM;
        }
    }
    
    private Theme mTheme;
    
    public ThemeStoriesAdapter() {
    }
    
    public void setTheme(Theme theme) {
        mTheme = theme;
        notifyDataSetChanged();
    }
    
    public void appendStories(List<Story> stories) {
        mTheme.getStories().addAll(stories);
        notifyDataSetChanged();
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
        int count = 0;
        if (mTheme != null) {
            if (!TextUtils.isEmpty(mTheme.getBackground())) {
                count += 1;
            }
            if (mTheme.getEditors() != null && mTheme.getEditors().size() > 0) {
                count += 1;
            }
            if (mTheme.getStories() != null) {
                count = count + mTheme.getStories().size();
            }
        }
        return count;
    }
    public static final class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static final class AvatarViewHolder extends RecyclerView.ViewHolder {

        public AvatarViewHolder(View itemView) {
            super(itemView);
        }
    }

}
