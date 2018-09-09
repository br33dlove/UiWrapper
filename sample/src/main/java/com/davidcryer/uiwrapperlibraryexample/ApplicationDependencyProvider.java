package com.davidcryer.uiwrapperlibraryexample;

import com.davidcryer.uiwrapperlibraryexample.example.ExampleUiModelFactory;
import com.davidcryer.uiwrapperlibraryexample.exampledialog.ExampleDialogUiModelFactory;
import com.davidcryer.uiwrapperlibraryexample.common.ResourceRepository;

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
