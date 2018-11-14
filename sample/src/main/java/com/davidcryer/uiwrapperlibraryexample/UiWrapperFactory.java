package com.davidcryer.uiwrapperlibraryexample;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.davidc.uiwrapper.UiWrapperInitializer;
import com.davidcryer.uiwrapperlibraryexample.common.ResourceManager;
import com.davidcryer.uiwrapperlibraryexample.example.ExampleUiWrapper;
import com.davidcryer.uiwrapperlibraryexample.example.ExampleUiModelFactory;
import com.davidcryer.uiwrapperlibraryexample.exampledialog.ExampleDialogUiModelFactory;
import com.davidcryer.uiwrapperlibraryexample.exampledialog.ExampleDialogUiWrapper;

public class UiWrapperFactory {
    private final ResourceManager resourceManager;
    private final ExampleUiModelFactory exampleUiModelFactory;
    private final ExampleDialogUiModelFactory exampleDialogUiModelFactory;

    public UiWrapperFactory(ResourceManager resourceManager, ExampleUiModelFactory exampleUiModelFactory, ExampleDialogUiModelFactory exampleDialogUiModelFactory) {
        this.resourceManager = resourceManager;
        this.exampleUiModelFactory = exampleUiModelFactory;
        this.exampleDialogUiModelFactory = exampleDialogUiModelFactory;
    }

    public ExampleUiWrapper createExampleUiWrapper(final int id, @Nullable final Bundle savedState) {
        return UiWrapperInitializer.from(savedState, () -> {
            return ExampleUiWrapper.newInstance(resourceManager, exampleUiModelFactory);
        }, nonNullSavedState -> {
            return ExampleUiWrapper.savedInstance(resourceManager, exampleUiModelFactory, savedState);
        });
    }

    public ExampleDialogUiWrapper createExampleDialogUiWrapper(@Nullable final Bundle savedState) {
        return UiWrapperInitializer.from(savedState, () -> {
            return ExampleDialogUiWrapper.newInstance(resourceManager, exampleDialogUiModelFactory);
        }, nonNullSavedState -> {
            return ExampleDialogUiWrapper.savedInstance(resourceManager, exampleDialogUiModelFactory, savedState);
        });
    }
}
