package com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example;

public interface ExampleUi {
    void showTimeOfLastStateRecoveryText(final String text);
    void showResourceListenersCountText(final String text);
    void showNewExampleUi();

    interface Listener {
        void onLaunchNewExampleUi(final ExampleUi ui);
    }
}
