package com.davidc.uiwrapper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class UiWrapperFactoryFragment<U, L, F> extends UiFragment<U, L> implements UiWrapperFactoryContract<U, L, F> {
    private final UiWrapperFactoryCore<U, L, F> core = new UiWrapperFactoryCore<>(this);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        core.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        core.onDetach();
    }

    @NonNull
    @Override
    public final UiWrapper<U, L, ?> uiWrapper(@Nullable Bundle savedState) {
        return core.uiWrapper(savedState);
    }
}
