package com.davidcryer.uiwrapperlibraryexample.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.uiwrapperlibraryexample.common.ResourceManager;

public class ExampleUiWrapper extends UiWrapper<ExampleUi, ExampleUi.Listener, ExampleUiModel> {
    private final ResourceManager resourceManager;

    private ExampleUiWrapper(ResourceManager resourceManager, ExampleUiModel uiModel) {
        super(uiModel);
        this.resourceManager = resourceManager;
    }

    @NonNull
    public static ExampleUiWrapper newInstance(final ResourceManager resourceManager, final ExampleUiModelFactory uiModelFactory) {
        return new ExampleUiWrapper(resourceManager, uiModelFactory.create());
    }

    @Nullable
    public static ExampleUiWrapper savedInstance(
            final ResourceManager resourceManager,
            final ExampleUiModelFactory uiModelFactory,
            final Bundle savedState
    ) {
        final ExampleUiModel uiModel = savedUiModel(savedState, uiModelFactory);
        return uiModel == null ? null : new ExampleUiWrapper(resourceManager, uiModel);
    }

    @Override
    protected void setUp(@NonNull ExampleUi ui) {
        final ExampleUiModel model = uiModel();
        ui.showResourceListenersCount(model.getResourceListenersCount());
        ui.showTimeOfLastStateRecovery(model.getTimeOfLastStateRecovery());
        ui.showButtonClickCount(model.getButtonClickCount());
    }

    @NonNull
    @Override
    protected ExampleUi.Listener uiListener() {
        return new ExampleUi.Listener() {
            @Override
            public void onClickShowExampleDialog(ExampleUi ui) {
                ui.showExampleDialog();
            }

            @Override
            public void onClickNewExampleActivity(ExampleUi ui) {
                ui.showNewExampleActivity();
                uiModel().incrementButtonClickCounter(ui);
            }

            @Override
            public void onClickNewExampleFragment(ExampleUi ui) {
                ui.showNewExampleFragment();
                uiModel().incrementButtonClickCounter(ui);
            }
        };
    }
}
