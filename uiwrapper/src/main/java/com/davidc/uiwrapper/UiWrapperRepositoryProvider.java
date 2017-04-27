package com.davidc.uiwrapper;

import android.support.annotation.NonNull;

interface UiWrapperRepositoryProvider<R extends BaseUiWrapperRepository> {
    @NonNull
    R get();
}
