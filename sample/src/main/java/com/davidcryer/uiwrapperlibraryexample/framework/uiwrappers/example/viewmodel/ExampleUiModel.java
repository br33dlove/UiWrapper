package com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.viewmodel;

import com.davidc.uiwrapper.UiModel;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.ExampleUi;

public interface ExampleUiModel extends UiModel<ExampleUi> {
    void showResourceListenersCount(final ExampleUi ui, final int resourceListenersCount);
    void incrementButtonClickCounter(final ExampleUi ui);
}
