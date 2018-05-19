package com.davidcryer.uiwrapperlibraryexample.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.davidcryer.uiwrapperlibraryexample.R;

public class FragmentResourceInfoView extends ResourceInfoView {
    private TextView buttonClickCounterTextView;

    public FragmentResourceInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        buttonClickCounterTextView = findViewById(R.id.buttonClickCounter);
    }

    @Override
    int getLayoutRes() {
        return R.layout.fragment_resource_info_view;
    }

    public void setButtonClickCounterText(final String text) {
        buttonClickCounterTextView.setText(text);
    }
}
