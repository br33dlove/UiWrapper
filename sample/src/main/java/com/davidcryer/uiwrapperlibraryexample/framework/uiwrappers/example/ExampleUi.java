package com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example;

public interface ExampleUi {
    void showTimeOfLastStateRecoveryText(final String text);
    void showResourceListenersCountText(final String text);
    void showButtonClickCountText(final String text);
    void showExampleDialog();
    void showNewExampleActivity();
    void showNewExampleFragment();

    interface Listener {
        void onClickShowExampleDialog(final ExampleUi ui);
        void onClickNewExampleActivity(final ExampleUi ui);
        void onClickNewExampleFragment(final ExampleUi ui);
    }
}
