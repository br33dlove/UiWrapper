package com.davidcryer.uiwrapperlibraryexample.examplelistitem;

import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiModelFactory;

public class ExampleListItemUiModelFactory implements UiModelFactory<ExampleListItemUiModel.SavedState, ExampleListItemUiModel> {

    ExampleListItemUiModel create(final String value) {
        return new ExampleListItemUiModel(value);
    }

    @NonNull
    @Override
    public ExampleListItemUiModel create(@NonNull ExampleListItemUiModel.SavedState parcelable) {
        return new ExampleListItemUiModel(parcelable);
    }
}
