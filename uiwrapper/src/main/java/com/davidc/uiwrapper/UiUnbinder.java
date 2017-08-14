package com.davidc.uiwrapper;

import android.support.annotation.NonNull;

import java.util.Map;

public interface UiUnbinder {
    <U extends Ui, L extends Ui.Listener, M extends UiModel<U>> void unbind(@NonNull final Map<String, UiWrapper<U, L, M>> uiWrapperMap);
}
