package com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example;

import android.os.Bundle;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.viewmodel.ExampleUiModel;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.viewmodel.ExampleUiModelFactory;
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
        final ExampleUiModel uiModel = savedUiModel(savedInstanceState);
        return uiModel == null ? newInstance(resource, uiModelFactory) : new ExampleUiWrapper(resource, uiModel);
    }

    private final Resource.Listener resourceListener = new Resource.Listener() {
        @Override
        public void listenerCount(int count) {
            uiModel().showResourceListenersCount(ui(), count);
        }
    };

    @Override
    protected void registerResources() {
        super.registerResources();
        resource.register(resourceListener);
    }

    @Override
    protected ExampleUi.Listener uiListener() {
        return new ExampleUi.Listener() {
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

    @Override
    protected void unregisterResources() {
        super.unregisterResources();
        resource.unregister(resourceListener);
    }
}
