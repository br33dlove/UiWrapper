package com.davidcryer.uiwrapperlibraryexample.framework.application;

import android.app.Application;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiWrapperFactoryProvider;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.UiWrapperFactory;

public class ExampleApplication extends Application implements UiWrapperFactoryProvider<UiWrapperFactory> {
    private UiWrapperFactory uiWrapperFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        uiWrapperFactory = ApplicationDependencyProvider.createUiWrapperFactory();
    }

    @NonNull
    @Override
    public UiWrapperFactory getUiWrapperFactory() {
        return uiWrapperFactory;
    }
}
