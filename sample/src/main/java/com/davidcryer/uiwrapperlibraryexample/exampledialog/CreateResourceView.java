package com.davidcryer.uiwrapperlibraryexample.exampledialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.davidcryer.uiwrapperlibraryexample.R;
import com.davidcryer.uiwrapperlibraryexample.common.ResourceSubmission;

public class CreateResourceView extends FrameLayout {
    private final TextInputLayout valueEditLayout;
    private final EditText valueEdit;
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            error(null);
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    public CreateResourceView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        valueEditLayout = findViewById(R.id.valueEditLayout);
        valueEdit = findViewById(R.id.valueEdit);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setUpTitleTouchListener() {
        valueEdit.setOnTouchListener((v, event) -> {
            switch (event.getActionIndex()) {
                case MotionEvent.ACTION_DOWN: {
                    error(null);
                }
            }
            return false;
        });
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        valueEdit.addTextChangedListener(watcher);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        valueEdit.removeTextChangedListener(watcher);
    }

    ResourceSubmission value() {
        return new ResourceSubmission(valueEdit.getText().toString());
    }

    void error(final String s) {
        valueEditLayout.setError(s);
    }
}
