package com.davidc.uiwrapper;

interface UiWrapperRepositoryProvider<R extends BaseUiWrapperRepository> {
    R get();
}
