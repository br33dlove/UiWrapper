package com.example.davidc.viewwrapper;

interface ViewWrapperRepositoryProvider<R extends ViewWrapperRepository> {
    R get();
}
