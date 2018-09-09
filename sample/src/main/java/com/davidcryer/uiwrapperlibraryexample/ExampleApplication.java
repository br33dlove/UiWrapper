package com.davidcryer.uiwrapperlibraryexample;

import android.app.Application;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiWrapperFactoryProvider;

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
