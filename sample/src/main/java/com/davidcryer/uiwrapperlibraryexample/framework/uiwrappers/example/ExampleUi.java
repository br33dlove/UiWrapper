package com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example;

public interface ExampleUi {
    void showTimeOfLastStateRecovery(final long time);
    void showResourceListenersCount(final int count);
    void showButtonClickCount(final int count);
    void showExampleDialog();
    void showNewExampleActivity();
    void showNewExampleFragment();

    interface Listener {
        void onClickShowExampleDialog(final ExampleUi ui);
        void onClickNewExampleActivity(final ExampleUi ui);
        void onClickNewExampleFragment(final ExampleUi ui);
    }
}
