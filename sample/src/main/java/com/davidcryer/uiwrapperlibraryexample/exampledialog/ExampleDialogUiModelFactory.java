package com.davidcryer.uiwrapperlibraryexample.exampledialog;

public class ExampleDialogUiModelFactory {
    private final static long DEFAULT_TIME_OF_LAST_STATE_RECOVERY = -1;
    private final static int DEFAULT_RESOURCE_LISTENERS_COUNT = 0;

    public ExampleDialogUiModel create() {
        return new ExampleDialogUiModel(DEFAULT_TIME_OF_LAST_STATE_RECOVERY, DEFAULT_RESOURCE_LISTENERS_COUNT);
    }
}
