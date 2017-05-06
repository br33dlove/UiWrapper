package com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers;

import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiWrapperRepositoryFactory;

public class UiWrapperRepositoryFactoryImpl implements UiWrapperRepositoryFactory<UiWrapperRepository> {
    private final UiWrapperFactory uiWrapperFactory;

    public UiWrapperRepositoryFactoryImpl(UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    @Override
    @NonNull
    public UiWrapperRepository create() {
        return new UiWrapperRepository(uiWrapperFactory);
    }
}
