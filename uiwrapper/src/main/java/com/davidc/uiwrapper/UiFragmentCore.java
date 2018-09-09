package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

class UiFragmentCore<U, L> {
    private final UiFragmentContract<U, L> contract;
    private UiWrapper<U, L, ?> wrapper;

    UiFragmentCore(UiFragmentContract<U, L> contract) {
        this.contract = contract;
    }

    public void onCreate(@Nullable Bundle savedState) {
        wrapper = contract.uiWrapper(savedState);
        GateKeep.notNull(wrapper, UiFragmentCore.class, "wrapper");
    }

    public void onViewCreated() {
        wrapper.bind(contract.ui());
    }

    public void onStart() {
        wrapper.registerResources();
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        wrapper.saveState(outState);
    }

    public void onStop() {
        wrapper.unregisterResources();
    }

    public void onDestroyView() {
        wrapper.unbind();
    }

    public void onDestroy() {
        wrapper = null;
    }

    @NonNull
    final L listener() {
        return wrapper.uiListener();
    }
}
