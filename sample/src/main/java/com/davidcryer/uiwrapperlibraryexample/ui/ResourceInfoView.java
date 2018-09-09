package com.davidcryer.uiwrapperlibraryexample.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.davidcryer.uiwrapperlibraryexample.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ResourceInfoView extends LinearLayout {
    private final static String FORMAT_RESOURCE_LISTENERS = "Number of resource listeners: %s";
    private final static String FORMAT_LAST_RECOVERY = "State last recovered on: %s";
    private final static String NO_LAST_RECOVERY = "State not yet recovered";
    private final static String SDF_LAST_RECOVERY = "EEE, d MMM yyyy HH:mm:ss";
    private final TextView resourceListenersCountTextView;
    private final TextView timeOfLastStateRecoveryTextView;

    public ResourceInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, getLayoutRes(), this);
        resourceListenersCountTextView = findViewById(R.id.resourceListenersCount);
        timeOfLastStateRecoveryTextView = findViewById(R.id.timeOfLastStateRecovery);
    }

    @LayoutRes int getLayoutRes() {
        return R.layout.resource_info_view;
    }

    public void setResourceListenersCount(final int count) {
        resourceListenersCountTextView.setText(resourceListenersCountText(count));
    }

    static String resourceListenersCountText(final int count) {
        return String.format(FORMAT_RESOURCE_LISTENERS, count);
    }

    public void setTimeOfLastStateRecovery(final long time) {
        timeOfLastStateRecoveryTextView.setText(timeOfLastStateRecoveryText(time));
    }

    static String timeOfLastStateRecoveryText(final long time) {
        if (time >= 0) {
            return String.format(FORMAT_LAST_RECOVERY, new SimpleDateFormat(SDF_LAST_RECOVERY, Locale.getDefault()).format(new Date(time)));
        } else {
            return NO_LAST_RECOVERY;
        }
    }
}
