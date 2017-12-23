package com.davidc.uiwrapper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

public abstract class UiWrapperFactoryFragment<U, L, F> extends UiFragment<U, L> {
    private UiWrapperFactoryProvider factoryProvider;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        factoryProvider = CastHelper.cast(context.getApplicationContext(), UiWrapperFactoryProvider.class);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        factoryProvider = null;
    }

    @Override
    protected final UiWrapper<U, L, ?> uiWrapper(@Nullable Bundle savedState) {
        //noinspection unchecked
        return uiWrapper((F) factoryProvider.getUiWrapperFactory(), savedState);
    }

    protected abstract UiWrapper<U, L, ?> uiWrapper(F uiWrapperFactory, @Nullable Bundle savedState);
}
