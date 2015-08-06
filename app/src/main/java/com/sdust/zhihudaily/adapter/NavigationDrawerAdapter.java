package com.sdust.zhihudaily.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.sdust.zhihudaily.interfaces.NavigationDrawerCallbacks;

/**
 * Created by Kevin on 2015/8/5.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private NavigationDrawerCallbacks mDrawerCallbacks;

    public static final class Type{
        public static final int TYPE_HEADER = 0;
        public static final int TYPE_ITEM = 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return Type.TYPE_HEADER;
        } else {
            return Type.TYPE_ITEM;
        }
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
        return 0;
    }

    public void setNavigationCallbacks(NavigationDrawerCallbacks callback) {
        this.mDrawerCallbacks = callback;
    }


}
