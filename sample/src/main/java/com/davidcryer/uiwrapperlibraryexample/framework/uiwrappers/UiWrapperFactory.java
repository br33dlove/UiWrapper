package com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.ExampleUiWrapper;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.viewmodel.ExampleUiModelFactory;
import com.davidcryer.uiwrapperlibraryexample.model.ResourceRepository;

public class UiWrapperFactory {
    private final ResourceRepository resourceRepository;
    private final ExampleUiModelFactory exampleUiModelFactory;

    public UiWrapperFactory(ResourceRepository resourceRepository, ExampleUiModelFactory exampleUiModelFactory) {
        this.resourceRepository = resourceRepository;
        this.exampleUiModelFactory = exampleUiModelFactory;
    }

    public ExampleUiWrapper createExampleUiWrapper(@Nullable final Bundle savedState) {
        return savedState == null
                ? ExampleUiWrapper.newInstance(resourceRepository.create(), exampleUiModelFactory)
                : ExampleUiWrapper.savedElseNewInstance(resourceRepository.create(), exampleUiModelFactory, savedState);
    }
}
