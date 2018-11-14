package com.davidc.uiwrapper;

import android.os.Parcelable;
import android.support.annotation.NonNull;

public interface UiModelFactory<P extends Parcelable, M extends UiModel<P>> {
    @NonNull M create(@NonNull final P parcelable);
}
