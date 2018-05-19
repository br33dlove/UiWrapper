package com.davidcryer.uiwrapperlibraryexample.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.davidc.uiwrapper.UiWrapper;
import com.davidc.uiwrapper.UiWrapperFactoryDialogFragment;
import com.davidcryer.uiwrapperlibraryexample.R;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.UiWrapperFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.exampledialog.ExampleDialogUi;

public class ExampleDialogFragment extends UiWrapperFactoryDialogFragment<ExampleDialogUi, ExampleDialogUi.Listener, UiWrapperFactory> {
    private ResourceInfoView resourceInfoView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getContext();
        if (context == null) {
            throw new IllegalStateException("Something has gone very wrong");
        }
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(R.layout.fragment_dialog_example_info)
                .setNeutralButton("Dismiss", null)
                .show();
        resourceInfoView = dialog.findViewById(R.id.info);
        return dialog;
    }

    @NonNull
    @Override
    public UiWrapper<ExampleDialogUi, ExampleDialogUi.Listener, ?> uiWrapper(@NonNull UiWrapperFactory uiWrapperFactory, @Nullable Bundle savedState) {
        return uiWrapperFactory.createExampleDialogUiWrapper(savedState);
    }

    @NonNull
    @Override
    public ExampleDialogUi ui() {
        return ui;
    }

    private final ExampleDialogUi ui = new ExampleDialogUi() {
        @Override
        public void showTimeOfLastStateRecoveryText(String text) {
            resourceInfoView.setTimeOfLastStateRecoveryText(text);
        }

        @Override
        public void showResourceListenersCountText(String text) {
            resourceInfoView.setResourceListenersCountText(text);
        }
    };
}
