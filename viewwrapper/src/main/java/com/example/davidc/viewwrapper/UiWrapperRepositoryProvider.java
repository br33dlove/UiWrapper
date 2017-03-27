package com.example.davidc.viewwrapper;

interface UiWrapperRepositoryProvider<R extends UiWrapperRepository> {
    R get();
}
