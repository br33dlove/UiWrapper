package com.davidcryer.uiwrapperlibraryexample.exampledialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.uiwrapperlibraryexample.common.BadResourceSubmissionException;
import com.davidcryer.uiwrapperlibraryexample.common.ResourceManager;
import com.davidcryer.uiwrapperlibraryexample.common.ResourceSubmission;

public class ExampleDialogUiWrapper extends UiWrapper<ExampleDialogUi, ExampleDialogUi.Listener, ExampleDialogUiModel> {
    private final ResourceManager resourceManager;

    private ExampleDialogUiWrapper(@NonNull ResourceManager resourceManager, @NonNull ExampleDialogUiModel uiModel) {
        super(uiModel);
        this.resourceManager = resourceManager;
    }

    @NonNull
    public static ExampleDialogUiWrapper newInstance(
            final ResourceManager resourceManager,
            final ExampleDialogUiModelFactory uiModelFactory
    ) {
        return new ExampleDialogUiWrapper(resourceManager, uiModelFactory.create());
    }

    @Nullable
    public static ExampleDialogUiWrapper savedInstance(
            final ResourceManager resourceManager,
            final ExampleDialogUiModelFactory uiModelFactory,
            final Bundle savedState
    ) {
        final ExampleDialogUiModel uiModel = savedUiModel(savedState, uiModelFactory);
        return uiModel == null ? null : new ExampleDialogUiWrapper(resourceManager, uiModel);
    }

    @NonNull
    @Override
    protected ExampleDialogUi.Listener uiListener() {
        return new ExampleDialogUi.Listener() {
            @Override
            public void onClickCreateResource(ExampleDialogUi ui, ResourceSubmission submission) {
                try {
                    resourceManager.create(submission);
                    ui.dismiss();
                } catch (BadResourceSubmissionException e) {
                    ui.error(e.getLocalizedMessage());
                }
            }
        };
    }

    @Override
    protected void setUp(@NonNull ExampleDialogUi ui) {

    }
}
