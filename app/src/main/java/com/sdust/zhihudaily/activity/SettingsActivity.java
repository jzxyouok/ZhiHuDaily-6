package com.sdust.zhihudaily.activity;

import android.os.Bundle;

import com.sdust.zhihudaily.R;
import com.sdust.zhihudaily.fragment.SettingsFragment;

public class SettingsActivity extends BaseAppCompatActivity {

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new SettingsFragment())
                    .commit();
        }
    }
}
