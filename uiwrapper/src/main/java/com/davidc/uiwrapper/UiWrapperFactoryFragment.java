package com.davidc.uiwrapper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

    @NonNull
    @Override
    protected final UiWrapper<U, L, ?> uiWrapper(@Nullable Bundle savedState) {
        //noinspection unchecked
        final F factory = (F) factoryProvider.getUiWrapperFactory();
        ArgChecker.notNull(factory, UiWrapperFactoryFragment.class, "getUiWrapperFactory()");
        return uiWrapper(factory, savedState);
    }

    @NonNull
    protected abstract UiWrapper<U, L, ?> uiWrapper(@NonNull F uiWrapperFactory, @Nullable Bundle savedState);
}
