package com.davidcryer.uiwrapperlibraryexample.example;

import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiModelFactory;

public class ExampleUiModelFactory implements UiModelFactory<ExampleUiModel.SavedState, ExampleUiModel> {
    private final static long DEFAULT_TIME_OF_LAST_STATE_RECOVERY = -1;
    private final static int DEFAULT_RESOURCE_LISTENERS_COUNT = 0;
    private final static int DEFAULT_BUTTON_CLICK_COUNT = 0;

    public ExampleUiModel create() {
        return new ExampleUiModel(DEFAULT_TIME_OF_LAST_STATE_RECOVERY, DEFAULT_RESOURCE_LISTENERS_COUNT, DEFAULT_BUTTON_CLICK_COUNT);
    }

    @NonNull
    @Override
    public ExampleUiModel create(@NonNull ExampleUiModel.SavedState parcelable) {
        return new ExampleUiModel(parcelable);
    }
}
