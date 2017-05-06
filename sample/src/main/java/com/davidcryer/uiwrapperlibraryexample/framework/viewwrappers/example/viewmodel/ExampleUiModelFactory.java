package com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.viewmodel;

public class ExampleUiModelFactory {
    private final static long DEFAULT_TIME_OF_LAST_STATE_RECOVERY = -1;
    private final static int DEFAULT_RESOURCE_LISTENERS_COUNT = 0;

    public ExampleUiModel create() {
        return new ExampleUiModelImpl(DEFAULT_TIME_OF_LAST_STATE_RECOVERY, DEFAULT_RESOURCE_LISTENERS_COUNT);
    }
}
