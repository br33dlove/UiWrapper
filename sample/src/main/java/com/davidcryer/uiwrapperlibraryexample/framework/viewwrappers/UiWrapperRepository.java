package com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiBinder;
import com.davidc.uiwrapper.UiUnbinder;
import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.ExampleUi;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.viewmodel.ExampleUiModel;

import java.util.HashMap;
import java.util.Map;

public class UiWrapperRepository {
    private final UiWrapperFactory uiWrapperFactory;
    private final Map<String, UiWrapper<ExampleUi, ExampleUi.Listener, ExampleUiModel>> exampleUiMap = new HashMap<>();

    UiWrapperRepository(UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    public ExampleUi.Listener bind(final ExampleUi ui, final UiBinder binder) {
        return binder.bind(ui, exampleUiMap, new UiBinder.UiWrapperProvider<ExampleUi, ExampleUi.Listener, ExampleUiModel>() {
            @NonNull
            @Override
            public UiWrapper<ExampleUi, ExampleUi.Listener, ExampleUiModel> uiWrapper(Bundle savedInstanceState) {
                return uiWrapperFactory.createExampleUiWrapper(savedInstanceState);
            }
        });
    }

    public void unbind(final ExampleUi ui, final UiUnbinder unbinder) {
        unbinder.unbind(exampleUiMap);
    }
}
