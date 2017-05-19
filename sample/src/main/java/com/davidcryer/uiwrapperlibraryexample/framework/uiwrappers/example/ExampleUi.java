package com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example;

import com.davidc.uiwrapper.Ui;

public interface ExampleUi extends Ui {
    void showTimeOfLastStateRecoveryText(final String text);
    void showResourceListenersCountText(final String text);
    void showNewExampleUi();

    interface Listener extends Ui.Listener {
        void onLaunchNewExampleUi(final ExampleUi ui);
    }
}
