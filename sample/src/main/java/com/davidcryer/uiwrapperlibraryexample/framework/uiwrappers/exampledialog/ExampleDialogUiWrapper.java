package com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.exampledialog;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiWrapper;

public class ExampleDialogUiWrapper extends UiWrapper<ExampleDialogUi, ExampleDialogUi.Listener, ExampleDialogUiModel> {

    private ExampleDialogUiWrapper(@NonNull ExampleDialogUiModel uiModel) {
        super(uiModel);
    }

    public static ExampleDialogUiWrapper newInstance(final ExampleDialogUiModelFactory uiModelFactory) {
        return new ExampleDialogUiWrapper(uiModelFactory.create());
    }

    public static ExampleDialogUiWrapper savedElseNewInstance(
            final ExampleDialogUiModelFactory uiModelFactory,
            final Bundle savedState
    ) {
        final ExampleDialogUiModel uiModel = savedUiModel(savedState);
        return uiModel == null ? newInstance(uiModelFactory) : new ExampleDialogUiWrapper(uiModel);
    }

    @NonNull
    @Override
    protected ExampleDialogUi.Listener uiListener() {
        return new ExampleDialogUi.Listener() {};
    }
}
