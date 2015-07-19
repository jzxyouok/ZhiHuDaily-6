package com.sdust.zhihudaily.fragment;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.ZhiHuApplication;
import com.sdust.zhihudaily.bean.StartImage;
import com.sdust.zhihudaily.respository.interfaces.Respository;
import com.sdust.zhihudaily.util.SystemUtils;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 类名：StartFragment
 * 说明：开始界面
 */
public class StartFragment extends Fragment{
	
	private TextView mAuthorView;
	private ImageView mLogoImg;
	private ImageView mStartImg;
	private int mHeight;
	private int mWidth;
	private Animation mStartAnim;
	
	private DisplayImageOptions mOptions;
	
	public static final String TAG = StartFragment.class.getSimpleName();
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mHeight = SystemUtils.getScreenHeight(activity);
		mWidth = SystemUtils.getScreenWidth(activity);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mStartAnim = AnimationUtils.loadAnimation(getActivity(),R.anim.start);
		mOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(false)
        .cacheOnDisk(true)
        .considerExifParams(true)
        .build();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_start, container,false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mStartImg = (ImageView) view.findViewById(R.id.img_start);
		mStartImg.startAnimation(mStartAnim);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		loadImage();
	}
	
	private void loadImage() {
		ZhiHuApplication.getRespository().getStartImage(mHeight,mWidth,mOptions,new Respository.Callback<StartImage>() {

			@Override
			public void success(StartImage image) {
				mAuthorView.setText(image.getText());
				
				 ImageLoader.getInstance().displayImage(image.getImg(), mStartImg, mOptions);
			}

			@Override
			public void failure(Exception e) {
				 Log.i(TAG, "default image.");
	                mStartImg.setBackgroundResource(R.drawable.bg_splash);
	                mAuthorView.setText(getResources().getString(R.string.start_text));
	                e.printStackTrace();
			}
		});
		
	}
}
