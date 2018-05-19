package com.davidcryer.uiwrapperlibraryexample.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.davidcryer.uiwrapperlibraryexample.R;

public class ResourceInfoView extends LinearLayout {
    private TextView resourceListenersCountTextView;
    private TextView timeOfLastStateRecoveryTextView;

    public ResourceInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, getLayoutRes(), this);
        resourceListenersCountTextView = findViewById(R.id.resourceListenersCount);
        timeOfLastStateRecoveryTextView = findViewById(R.id.timeOfLastStateRecovery);
    }

    @LayoutRes int getLayoutRes() {
        return R.layout.resource_info_view;
    }

    public void setResourceListenersCountText(final String text) {
        resourceListenersCountTextView.setText(text);
    }

    public void setTimeOfLastStateRecoveryText(final String text) {
        timeOfLastStateRecoveryTextView.setText(text);
    }
}
