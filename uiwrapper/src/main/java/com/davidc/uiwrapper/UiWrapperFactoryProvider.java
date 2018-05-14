package com.davidc.uiwrapper;

import android.support.annotation.NonNull;

public interface UiWrapperFactoryProvider<F> {
    @NonNull F getUiWrapperFactory();
}
