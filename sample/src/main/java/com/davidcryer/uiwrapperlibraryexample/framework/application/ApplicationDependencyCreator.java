package com.davidcryer.uiwrapperlibraryexample.framework.application;

import com.davidc.uiwrapper.UiWrapperRepositoryFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.UiWrapperFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.UiWrapperRepositoryFactoryImpl;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.UiWrapperRepository;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.viewmodel.ExampleUiModelFactory;
import com.davidcryer.uiwrapperlibraryexample.model.ResourceRepository;

class ApplicationDependencyCreator {

    private ApplicationDependencyCreator() {

    }

    static UiWrapperRepositoryFactory<UiWrapperRepository> createUiWrapperRepositoryFactory() {
        return new UiWrapperRepositoryFactoryImpl(createUiWrapperFactory());
    }

    private static UiWrapperFactory createUiWrapperFactory() {
        return new UiWrapperFactory(createResourceFactory(), createExampleUiModelFactory());
    }

    private static ResourceRepository createResourceFactory() {
        return new ResourceRepository();
    }

    private static ExampleUiModelFactory createExampleUiModelFactory() {
        return new ExampleUiModelFactory();
    }
}
