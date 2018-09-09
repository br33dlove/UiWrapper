package com.davidcryer.uiwrapperlibraryexample;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.davidc.uiwrapper.UiWrapperInitializer;
import com.davidcryer.uiwrapperlibraryexample.example.ExampleUiWrapper;
import com.davidcryer.uiwrapperlibraryexample.example.ExampleUiModelFactory;
import com.davidcryer.uiwrapperlibraryexample.exampledialog.ExampleDialogUiModelFactory;
import com.davidcryer.uiwrapperlibraryexample.exampledialog.ExampleDialogUiWrapper;
import com.davidcryer.uiwrapperlibraryexample.common.ResourceRepository;

public class UiWrapperFactory {
    private final ResourceRepository resourceRepository;
    private final ExampleUiModelFactory exampleUiModelFactory;
    private final ExampleDialogUiModelFactory exampleDialogUiModelFactory;

    public UiWrapperFactory(ResourceRepository resourceRepository, ExampleUiModelFactory exampleUiModelFactory, ExampleDialogUiModelFactory exampleDialogUiModelFactory) {
        this.resourceRepository = resourceRepository;
        this.exampleUiModelFactory = exampleUiModelFactory;
        this.exampleDialogUiModelFactory = exampleDialogUiModelFactory;
    }

    public ExampleUiWrapper createExampleUiWrapper(@Nullable final Bundle savedState) {
        return UiWrapperInitializer.from(savedState, () -> {
            return ExampleUiWrapper.newInstance(resourceRepository.create(), exampleUiModelFactory);
        }, nonNullSavedState -> {
            return ExampleUiWrapper.savedElseNewInstance(resourceRepository.create(), exampleUiModelFactory, savedState);
        });
    }

    public ExampleDialogUiWrapper createExampleDialogUiWrapper(@Nullable final Bundle savedState) {
        return UiWrapperInitializer.from(savedState, () -> {
            return ExampleDialogUiWrapper.newInstance(resourceRepository.create(), exampleDialogUiModelFactory);
        }, nonNullSavedState -> {
            return ExampleDialogUiWrapper.savedElseNewInstance(resourceRepository.create(), exampleDialogUiModelFactory, savedState);
        });
    }
}
