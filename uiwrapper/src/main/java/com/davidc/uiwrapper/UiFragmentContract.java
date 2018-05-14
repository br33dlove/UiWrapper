package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface UiFragmentContract<U, L> {
    @NonNull UiWrapper<U, L, ?> uiWrapper(@Nullable Bundle savedState);
    @NonNull U ui();
}
