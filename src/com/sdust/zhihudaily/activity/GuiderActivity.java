package com.sdust.zhihudaily.activity;

import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.fragment.StartFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


/**
 * ¿‡√˚£∫GuiderActivity
 */
public class GuiderActivity extends Activity {
	
	private StartFragment startFragment;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guider);
		
		start();
	}
	
	private void start() {
		startFragment = new StartFragment();
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.add(R.id.container,startFragment, StartFragment.TAG);
		transaction.commit();
	}
	@Override
    public void onAttachFragment(Fragment fragment) {
      
    }
	
	private void intentToMainActivity() {
		
	}

}
