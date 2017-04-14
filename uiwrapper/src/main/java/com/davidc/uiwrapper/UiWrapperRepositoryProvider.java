package com.davidc.uiwrapper;

public interface UiWrapperRepositoryProvider<R extends BaseUiWrapperRepository> {
    R get();
}
