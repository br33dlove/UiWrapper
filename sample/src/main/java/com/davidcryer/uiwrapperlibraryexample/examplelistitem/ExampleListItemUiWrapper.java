package com.davidcryer.uiwrapperlibraryexample.examplelistitem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.davidc.uiwrapper.UiWrapper;

public class ExampleListItemUiWrapper extends UiWrapper<ExampleListItemUi, ExampleListItemUi.Listener, ExampleListItemUiModel> {

    private ExampleListItemUiWrapper(@NonNull ExampleListItemUiModel uiModel) {
        super(uiModel);
    }

    @NonNull
    public static ExampleListItemUiWrapper newInstance(
            final ExampleListItemUiModelFactory uiModelFactory,
            final String value
    ) {
        return new ExampleListItemUiWrapper(uiModelFactory.create(value));
    }

    @Nullable
    public static ExampleListItemUiWrapper savedInstance(
            final ExampleListItemUiModelFactory uiModelFactory,
            final Bundle savedState
    ) {
        final ExampleListItemUiModel uiModel = savedUiModel(savedState, uiModelFactory);
        return uiModel == null ? null : new ExampleListItemUiWrapper(uiModel);
    }

    @Override
    protected void setUp(@NonNull ExampleListItemUi ui) {

    }

    @NonNull
    @Override
    protected ExampleListItemUi.Listener uiListener() {
        return new ExampleListItemUi.Listener() {};
    }
}
