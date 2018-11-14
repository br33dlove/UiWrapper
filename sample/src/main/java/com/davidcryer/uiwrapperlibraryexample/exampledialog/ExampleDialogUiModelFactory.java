package com.davidcryer.uiwrapperlibraryexample.exampledialog;

import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiModelFactory;

public class ExampleDialogUiModelFactory implements UiModelFactory<ExampleDialogUiModel.SavedState, ExampleDialogUiModel> {

    public ExampleDialogUiModel create() {
        return new ExampleDialogUiModel();
    }

    @NonNull
    @Override
    public ExampleDialogUiModel create(@NonNull ExampleDialogUiModel.SavedState parcelable) {
        return new ExampleDialogUiModel(parcelable);
    }
}
