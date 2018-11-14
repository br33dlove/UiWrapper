package com.davidcryer.uiwrapperlibraryexample.exampledialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.davidc.uiwrapper.UiWrapper;
import com.davidc.uiwrapper.UiWrapperFactoryDialogFragment;
import com.davidcryer.uiwrapperlibraryexample.R;
import com.davidcryer.uiwrapperlibraryexample.UiWrapperFactory;

public class ExampleDialogFragment extends UiWrapperFactoryDialogFragment<ExampleDialogUi, ExampleDialogUi.Listener, UiWrapperFactory> {
    private CreateResourceView createResourceView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getContext();
        if (context == null) {
            throw new IllegalStateException("Something has gone very wrong");
        }
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(R.layout.fragment_dialog_example_info)
                .setPositiveButton("Create", null)
                .setNeutralButton("Dismiss", null)
                .create();
        createResourceView = dialog.findViewById(R.id.createResource);
        dialog.setOnShowListener(d -> dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(l -> onClickSubmit()));
        dialog.show();
        return dialog;
    }

    private void onClickSubmit() {
        if (createResourceView != null) {
            listener().onClickCreateResource(ui(), createResourceView.value());
        }
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
        public void dismiss() {
            ExampleDialogFragment.this.dismiss();
        }

        @Override
        public void error(String e) {
            createResourceView.error(e);
        }
    };
}
