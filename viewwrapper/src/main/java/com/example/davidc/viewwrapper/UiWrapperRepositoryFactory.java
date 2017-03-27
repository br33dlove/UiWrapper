package com.example.davidc.viewwrapper;

public interface UiWrapperRepositoryFactory<R extends UiWrapperRepository> {
    R create();
}
