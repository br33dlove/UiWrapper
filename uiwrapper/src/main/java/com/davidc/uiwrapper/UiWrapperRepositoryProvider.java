package com.davidc.uiwrapper;

interface UiWrapperRepositoryProvider<R extends UiWrapperRepository> {
    R get();
}
