package com.davidcryer.uiwrapperlibraryexample.framework.application;

import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.UiWrapperFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.viewmodel.ExampleUiModelFactory;
import com.davidcryer.uiwrapperlibraryexample.model.ResourceRepository;

class ApplicationDependencyProvider {

    private ApplicationDependencyProvider() {}

    static UiWrapperFactory createUiWrapperFactory() {
        return new UiWrapperFactory(createResourceFactory(), createExampleUiModelFactory());
    }

    private static ResourceRepository createResourceFactory() {
        return new ResourceRepository();
    }

    private static ExampleUiModelFactory createExampleUiModelFactory() {
        return new ExampleUiModelFactory();
    }
}
