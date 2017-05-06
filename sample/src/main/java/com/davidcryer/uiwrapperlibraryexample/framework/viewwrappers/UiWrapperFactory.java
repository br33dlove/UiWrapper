package com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.ExampleUiWrapper;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.viewmodel.ExampleUiModelFactory;
import com.davidcryer.uiwrapperlibraryexample.model.ResourceRepository;

public class UiWrapperFactory {
    private final ResourceRepository resourceRepository;
    private final ExampleUiModelFactory exampleUiModelFactory;

    public UiWrapperFactory(ResourceRepository resourceRepository, ExampleUiModelFactory exampleUiModelFactory) {
        this.resourceRepository = resourceRepository;
        this.exampleUiModelFactory = exampleUiModelFactory;
    }

    ExampleUiWrapper createExampleUiWrapper(@Nullable final Bundle savedInstanceState) {
        return savedInstanceState == null
                ? ExampleUiWrapper.newInstance(resourceRepository.create(), exampleUiModelFactory)
                : ExampleUiWrapper.savedElseNewInstance(resourceRepository.create(), exampleUiModelFactory, savedInstanceState);
    }
}
