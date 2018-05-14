package com.davidcryer.uiwrapperlibraryexample.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.davidc.uiwrapper.UiWrapper;
import com.davidc.uiwrapper.UiWrapperFactoryDialogFragment;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.UiWrapperFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.exampledialog.ExampleDialogUi;

public class ExampleDialogFragment extends UiWrapperFactoryDialogFragment<ExampleDialogUi, ExampleDialogUi.Listener, UiWrapperFactory> {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @NonNull
    @Override
    protected UiWrapper<ExampleDialogUi, ExampleDialogUi.Listener, ?> uiWrapper(@NonNull UiWrapperFactory uiWrapperFactory, @Nullable Bundle savedState) {
        return uiWrapperFactory.createExampleDialogUiWrapper(savedState);
    }

    @NonNull
    @Override
    protected ExampleDialogUi ui() {
        return ui;
    }

    private final ExampleDialogUi ui = new ExampleDialogUi() {

    };
}
