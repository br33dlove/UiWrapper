package com.davidc.uiwrapper;

import android.support.annotation.NonNull;

public interface UiWrapperRepositoryFactory<R extends BaseUiWrapperRepository> {
    @NonNull
    R create();
}
