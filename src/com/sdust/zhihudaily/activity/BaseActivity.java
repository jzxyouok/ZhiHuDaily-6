/**
 * 
 */
package com.sdust.zhihudaily.activity;

/**
 * @author Kevin
 *
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.sdust.zhihudaily.R;



public abstract  class BaseActivity extends AppCompatActivity {
    public Toolbar mActionBarToolbar;

    protected abstract int getContentViewLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayoutId());
        mActionBarToolbar = (Toolbar) findViewById(R.id.actionbarToolbar);
      
        setupActionBar();
    }

    private void setupActionBar() {
        setSupportActionBar(mActionBarToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && !(this instanceof MainActivity)) {
            //左上角加图标返回
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
  
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
