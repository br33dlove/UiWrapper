package com.davidcryer.uiwrapperlibraryexample.framework.application;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.davidc.uiwrapper.DefaultBinder;
import com.davidc.uiwrapper.DefaultUnbinder;
import com.davidc.uiwrapper.UiBinder;
import com.davidc.uiwrapper.UiBinderFactory;
import com.davidc.uiwrapper.UiUnbinder;
import com.davidc.uiwrapper.UiUnbinderFactory;
import com.davidc.uiwrapper.UiWrapperRepositoryFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.UiWrapperRepository;

public class ExampleApplication extends Application
        implements UiWrapperRepositoryFactory<UiWrapperRepository>, UiBinderFactory, UiUnbinderFactory {
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

    @Override
    public UiBinder get(@NonNull String instanceId, @Nullable Bundle savedInstanceState) {
        return new DefaultBinder(instanceId, savedInstanceState);
    }

    @Override
    public UiUnbinder get(@NonNull String instanceId, @Nullable Bundle outState, boolean isConfigurationChange) {
        return new DefaultUnbinder(instanceId, outState, isConfigurationChange);
    }
}
