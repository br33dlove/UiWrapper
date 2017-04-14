package com.davidc.uiwrapper;

public interface UiWrapperRepositoryFactory<R extends BaseUiWrapperRepository> {
    R create();
}
