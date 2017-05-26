package com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.BaseUiWrapperRepository;
import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.ExampleUi;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.viewmodel.ExampleUiModel;

import java.util.HashMap;
import java.util.Map;

public class UiWrapperRepository extends BaseUiWrapperRepository {
    private final UiWrapperFactory uiWrapperFactory;
    private final Map<String, UiWrapper<ExampleUi, ExampleUi.Listener, ExampleUiModel>> exampleUiWrapperMap = new HashMap<>();

    UiWrapperRepository(UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    public ExampleUi.Listener bind(final ExampleUi ui, final String instanceId, final Bundle savedInstanceState) {
        return bind(ui, instanceId, exampleUiWrapperMap, new UiWrapperProvider<ExampleUi, ExampleUi.Listener, ExampleUiModel>() {
            @Override
            @NonNull
            public UiWrapper<ExampleUi, ExampleUi.Listener, ExampleUiModel> uiWrapper() {
                return uiWrapperFactory.createExampleUiWrapper(savedInstanceState);
            }
        });
    }

    public void unbind(final ExampleUi ui, final String instanceId, final Bundle outState, final boolean isConfigurationChange) {
        unbind(instanceId, exampleUiWrapperMap, outState, isConfigurationChange);
    }
}
