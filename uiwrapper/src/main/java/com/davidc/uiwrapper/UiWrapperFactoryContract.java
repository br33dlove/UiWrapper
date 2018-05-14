package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface UiWrapperFactoryContract<U, L, F> {
    @NonNull UiWrapper<U, L, ?> uiWrapper(@NonNull F uiWrapperFactory, @Nullable Bundle savedState);
}
