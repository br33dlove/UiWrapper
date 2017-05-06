package com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.viewmodel;

import com.davidc.uiwrapper.UiModel;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.ExampleUi;

public interface ExampleUiModel extends UiModel<ExampleUi> {
    void showResourceListenersCount(final ExampleUi ui, final int resourceListenersCount);
}
