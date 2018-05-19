package com.davidcryer.uiwrapperlibraryexample.framework.application;

import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.UiWrapperFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.ExampleUiModelFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.exampledialog.ExampleDialogUiModelFactory;
import com.davidcryer.uiwrapperlibraryexample.model.ResourceRepository;

class ApplicationDependencyProvider {

    private ApplicationDependencyProvider() {}

    static UiWrapperFactory createUiWrapperFactory() {
        return new UiWrapperFactory(createResourceFactory(), createExampleUiModelFactory(), createExampleDialogUiModelFactory());
    }

    private static ResourceRepository createResourceFactory() {
        return new ResourceRepository();
    }

    private static ExampleUiModelFactory createExampleUiModelFactory() {
        return new ExampleUiModelFactory();
    }

    private static ExampleDialogUiModelFactory createExampleDialogUiModelFactory() {
        return new ExampleDialogUiModelFactory();
    }
}
