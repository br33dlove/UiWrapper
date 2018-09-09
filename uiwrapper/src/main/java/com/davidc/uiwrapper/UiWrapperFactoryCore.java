package com.davidc.uiwrapper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

class UiWrapperFactoryCore<U, L, F> {
    private final UiWrapperFactoryContract<U, L, F> contract;
    private UiWrapperFactoryProvider factoryProvider;

    UiWrapperFactoryCore(UiWrapperFactoryContract<U, L, F> contract) {
        this.contract = contract;
    }

    public void onAttach(Context context) {
        factoryProvider = CastHelper.cast(context.getApplicationContext(), UiWrapperFactoryProvider.class);
    }

    public void onDetach() {
        factoryProvider = null;
    }

    @NonNull
    public final UiWrapper<U, L, ?> uiWrapper(@Nullable Bundle savedState) {
        //noinspection unchecked
        final F factory = (F) factoryProvider.getUiWrapperFactory();
        GateKeep.notNull(factory, UiWrapperFactoryCore.class, "factoryProvider.getUiWrapperFactory()");
        return contract.uiWrapper(factory, savedState);
    }
}
