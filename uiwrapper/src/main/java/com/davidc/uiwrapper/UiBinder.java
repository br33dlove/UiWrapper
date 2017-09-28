package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.Map;

public interface UiBinder {
    <U, L, M extends UiModel<U>> L bind(@NonNull final U ui, @NonNull final Map<String, UiWrapper<U, L, M>> uiWrapperMap, @NonNull final UiWrapperProvider<U, L, M> uiWrapperProvider);

    interface UiWrapperProvider<U, L, M extends UiModel<U>> {
        @NonNull
        UiWrapper<U, L, M> uiWrapper(final Bundle savedInstanceState);
    }
}
