package com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example;

import android.os.Bundle;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.viewmodel.ExampleUiModel;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.viewmodel.ExampleUiModelFactory;
import com.davidcryer.uiwrapperlibraryexample.model.Resource;

public class ExampleUiWrapper extends UiWrapper<ExampleUi, ExampleUi.Listener, ExampleUiModel> {
    private final Resource resource;

    private ExampleUiWrapper(Resource resource, ExampleUiModel uiModel) {
        super(uiModel);
        this.resource = resource;
    }

    public static ExampleUiWrapper newInstance(final Resource resource, final ExampleUiModelFactory uiModelFactory) {
        return new ExampleUiWrapper(resource, uiModelFactory.create());
    }

    public static ExampleUiWrapper savedElseNewInstance(
            final Resource resource,
            final ExampleUiModelFactory uiModelFactory,
            final Bundle savedInstanceState
    ) {
        final ExampleUiModel uiModel = UiWrapper.savedUiModel(savedInstanceState);
        return uiModel == null ? newInstance(resource, uiModelFactory) : new ExampleUiWrapper(resource, uiModel);
    }

    @Override
    protected ExampleUi.Listener uiListener() {
        return new ExampleUi.Listener() {
            @Override
            public void onLaunchNewExampleUi(ExampleUi ui) {
                ui.showNewExampleUi();
            }
        };
    }

    @Override
    protected void registerResources() {
        super.registerResources();
        resource.register(resourceListener);
    }

    private final Resource.Listener resourceListener = new Resource.Listener() {
        @Override
        public void listenerCount(int count) {
            uiModel().showResourceListenersCount(ui(), count);
        }
    };

    @Override
    protected void unregisterResources() {
        super.unregisterResources();
        resource.unregister(resourceListener);
    }
}
