package com.davidcryer.uiwrapperlibraryexample.framework.application;

import android.app.Application;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiWrapperRepositoryFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.UiWrapperRepository;

public class ExampleApplication extends Application implements UiWrapperRepositoryFactory<UiWrapperRepository> {
    private UiWrapperRepositoryFactory<UiWrapperRepository> viewWrapperRepositoryFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        viewWrapperRepositoryFactory = ApplicationDependencyCreator.createUiWrapperRepositoryFactory();
    }

    @Override
    @NonNull
    public UiWrapperRepository create() {
        return viewWrapperRepositoryFactory.create();
    }
}
