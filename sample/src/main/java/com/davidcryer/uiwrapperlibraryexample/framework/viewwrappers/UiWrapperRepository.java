package com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.BaseUiWrapperRepository;
import com.davidc.uiwrapper.BindingPayload;
import com.davidc.uiwrapper.UiWrapper;
import com.davidc.uiwrapper.UnbindingPayload;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.ExampleUi;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.viewmodel.ExampleUiModel;

import java.util.HashMap;
import java.util.Map;

public class UiWrapperRepository extends BaseUiWrapperRepository {
    private final UiWrapperFactory uiWrapperFactory;
    private final Map<String, UiWrapper<ExampleUi, ExampleUi.Listener, ExampleUiModel>> exampleUiMap = new HashMap<>();

    UiWrapperRepository(UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    public ExampleUi.Listener bind(final ExampleUi ui, final BindingPayload bindingPayload) {
        return bind(ui, exampleUiMap, bindingPayload, new UiWrapperProvider<ExampleUi, ExampleUi.Listener, ExampleUiModel>() {
            @NonNull
            @Override
            public UiWrapper<ExampleUi, ExampleUi.Listener, ExampleUiModel> uiWrapper(Bundle savedInstanceState) {
                return uiWrapperFactory.createExampleUiWrapper(savedInstanceState);
            }
        });
    }

    public void unbind(final ExampleUi ui, final UnbindingPayload unbindingPayload) {
        unbind(exampleUiMap, unbindingPayload);
    }
}
