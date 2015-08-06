package com.sdust.zhihudaily.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;

/**
 * Created by Kevin on 2015/8/5.
 */
public class CheckableLinearLayout extends LinearLayout implements Checkable{

    private boolean mChecked;

    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
    public CheckableLinearLayout(Context context) {
        this(context, null);
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean performClick() {

        toggle();
        return super.performClick();
    }

    @Override
    public void setChecked(boolean checked) {
        if(checked != mChecked){
            mChecked = checked;
            refreshDrawableState();
            setCheckedWithRecursive(this,checked);
        }
    }

    private void setCheckedWithRecursive(ViewGroup parent,boolean checked){
        int count = parent.getChildCount();
        for(int i = 0;i < count;i++) {
            View v = parent.getChildAt(i);
            if(v instanceof Checkable) {
                ((Checkable) v).setChecked(checked);
            }
            if(v instanceof  ViewGroup) {
                setCheckedWithRecursive((ViewGroup) v,checked);
            }
        }
    }


    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = getBackground();
        if(drawable != null) {
            int[] drawbleState = getDrawableState();
            drawable.setState(drawbleState);
            invalidate();
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }
}
