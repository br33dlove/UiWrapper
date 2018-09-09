package com.davidcryer.uiwrapperlibraryexample.exampledialog;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.uiwrapperlibraryexample.common.Resource;

public class ExampleDialogUiWrapper extends UiWrapper<ExampleDialogUi, ExampleDialogUi.Listener, ExampleDialogUiModel> {
    private final Resource resource;

    private ExampleDialogUiWrapper(@NonNull Resource resource, @NonNull ExampleDialogUiModel uiModel) {
        super(uiModel);
        this.resource = resource;
    }

    public static ExampleDialogUiWrapper newInstance(final Resource resource, final ExampleDialogUiModelFactory uiModelFactory) {
        return new ExampleDialogUiWrapper(resource, uiModelFactory.create());
    }

    public static ExampleDialogUiWrapper savedElseNewInstance(
            final Resource resource,
            final ExampleDialogUiModelFactory uiModelFactory,
            final Bundle savedState
    ) {
        final ExampleDialogUiModel uiModel = savedUiModel(savedState);
        return uiModel == null ? newInstance(resource, uiModelFactory) : new ExampleDialogUiWrapper(resource, uiModel);
    }

    @NonNull
    @Override
    protected ExampleDialogUi.Listener uiListener() {
        return new ExampleDialogUi.Listener() {};
    }

    @Override
    protected void registerResources() {
        super.registerResources();
        resource.register(resourceListener);
    }

    @Override
    protected void setUp(@NonNull ExampleDialogUi ui) {
        final ExampleDialogUiModel model = uiModel();
        ui.showResourceListenersCount(model.getResourceListenersCount());
        ui.showTimeOfLastStateRecovery(model.getTimeOfLastStateRecovery());
    }

    @Override
    protected void unregisterResources() {
        super.unregisterResources();
        resource.unregister(resourceListener);
    }

    private final Resource.Listener resourceListener = count -> uiModel().showResourceListenersCount(ui(), count);
}
