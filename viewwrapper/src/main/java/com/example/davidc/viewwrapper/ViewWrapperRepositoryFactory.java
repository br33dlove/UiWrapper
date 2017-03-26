package com.example.davidc.viewwrapper;

public interface ViewWrapperRepositoryFactory<R extends ViewWrapperRepository> {
    R create();
}
