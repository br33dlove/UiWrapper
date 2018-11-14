package com.davidcryer.uiwrapperlibraryexample.exampledialog;

import com.davidcryer.uiwrapperlibraryexample.common.ResourceSubmission;

public interface ExampleDialogUi {
    void dismiss();
    void error(String e);

    interface Listener {
        void onClickCreateResource(ExampleDialogUi ui, ResourceSubmission submission);
    }
}
