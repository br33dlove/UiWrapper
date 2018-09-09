package com.davidcryer.uiwrapperlibraryexample.example;

public class ExampleUiModelFactory {
    private final static long DEFAULT_TIME_OF_LAST_STATE_RECOVERY = -1;
    private final static int DEFAULT_RESOURCE_LISTENERS_COUNT = 0;
    private final static int DEFAULT_BUTTON_CLICK_COUNT = 0;

    public ExampleUiModel create() {
        return new ExampleUiModel(DEFAULT_TIME_OF_LAST_STATE_RECOVERY, DEFAULT_RESOURCE_LISTENERS_COUNT, DEFAULT_BUTTON_CLICK_COUNT);
    }
}
