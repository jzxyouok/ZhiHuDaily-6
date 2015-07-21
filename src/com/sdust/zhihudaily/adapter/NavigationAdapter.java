/**
 * 
 */
package com.sdust.zhihudaily.adapter;

import java.util.ArrayList;
import java.util.List;

import com.sdust.zhihudaily.NavigationDrawerCallback;
import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.bean.Theme;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Kevin
 *
 */
public class NavigationAdapter extends
		RecyclerView.Adapter<RecyclerView.ViewHolder> {

	List<Theme> mThemes;
	NavigationDrawerCallback mCallBack;
	private int mSelectedPosition = -1;

	public NavigationAdapter() {
		mThemes = new ArrayList<Theme>();
	}

	public static final class Type {
		public static final int TYPE_HEADER = 0;
		public static final int TYPE_ITEM = 1;
		public static final int TYPE_BOTTOM_SPACE = 2;
	}

	@Override
	public int getItemCount() {
		return mThemes != null ? mThemes.size() + 2 : 2;
	}

	public void setThemes(List<Theme> themes) {
		mThemes.clear();
		mThemes.addAll(themes);
		notifyDataSetChanged();
	}

	public void setNavigationDrawerCallbacks(NavigationDrawerCallback callbacks) {
		mCallBack = callbacks;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
			final int position) {
		int viewType = getItemViewType(position);

		switch (viewType) {
		case Type.TYPE_HEADER:
			HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
			bindHeaderData(headerViewHolder, position);
			break;
		case Type.TYPE_ITEM:
			ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
			bindItemData(itemViewHolder, position);
			break;
		case Type.TYPE_BOTTOM_SPACE:
			break;
		default:
			throw new IllegalArgumentException(" error view type!");
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

		View itemView = null;
		switch (viewType) {
		case Type.TYPE_HEADER:
			itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
					R.layout.item_nav_header, viewGroup, false);
			return new HeaderViewHolder(itemView);
		case Type.TYPE_ITEM:
			itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
					R.layout.item_nav_theme, viewGroup);
			final ItemViewHolder holder = new ItemViewHolder(itemView,
					mCallBack);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					selectPosition(holder.getPosition() - 1);
				}
			});
			return holder;

		}
		return null;
	}

	@Override
	public int getItemViewType(int position) {

		return position == 0 ? Type.TYPE_HEADER : Type.TYPE_ITEM;

	}

	public static class HeaderViewHolder extends RecyclerView.ViewHolder {
		ImageView ivHeader;

		public HeaderViewHolder(View itemView) {
			super(itemView);
			ivHeader = (ImageView) itemView.findViewById(R.id.ivHeader);
		}
	}

	public static class ItemViewHolder extends RecyclerView.ViewHolder {
		TextView tvItemName;
		ImageView imageView;

		public ItemViewHolder(View itemView,
				final NavigationDrawerCallback callBacks) {
			super(itemView);
			tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
			imageView = (ImageView) itemView.findViewById(R.id.ivItemIcon);
		}
	}

	public void selectPosition(int position) {
		int realPosition = position + 1;
		int lastPosition = mSelectedPosition;

		if (lastPosition != -1 && lastPosition != realPosition) {
			notifyItemChanged(lastPosition);
		}

		if (mSelectedPosition != realPosition) {
			mSelectedPosition = realPosition;
			notifyItemChanged(mSelectedPosition);
		}

		if (mCallBack != null) {
			mCallBack.onNavigationDrawerItemSelected(position);
		}
	}

	private void bindHeaderData(HeaderViewHolder viewHolder, int position) {
	}

	private void bindItemData(ItemViewHolder viewHolder, int position) {
		Resources resources = viewHolder.itemView.getContext().getResources();
		     
		if (position == 1) {
			viewHolder.imageView.setVisibility(View.VISIBLE);
			viewHolder.imageView.setBackgroundResource(R.drawable.menu_home);
			viewHolder.tvItemName.setText(R.string.title_activity_main);
		} else {
			viewHolder.imageView.setVisibility(View.GONE);
			viewHolder.tvItemName.setText(mThemes.get(position - 2).getName());
		}

		if (mSelectedPosition == position) {
			viewHolder.itemView.setBackgroundResource(R.color.navigation_item_selected);
			viewHolder.tvItemName.setTextColor(resources
					.getColor(R.color.navdrawer_text_color_selected));
		} else if (position != 0) {
			viewHolder.itemView
					.setBackgroundColor(android.R.attr.selectableItemBackground);
			viewHolder.tvItemName.setTextColor(resources
					.getColor(R.color.navdrawer_text_color));
		}
	}
}
