package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface UiBinderFactory {
    UiBinder get(@NonNull final String instanceId, @Nullable final Bundle savedInstanceState);
}
