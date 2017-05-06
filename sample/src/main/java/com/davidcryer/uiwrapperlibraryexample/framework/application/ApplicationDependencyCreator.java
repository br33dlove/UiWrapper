package com.davidcryer.uiwrapperlibraryexample.framework.application;

import com.davidc.uiwrapper.UiWrapperRepositoryFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.UiWrapperFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.UiWrapperRepositoryFactoryImpl;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.UiWrapperRepository;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.viewmodel.ExampleUiModelFactory;
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
