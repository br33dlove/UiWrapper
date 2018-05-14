package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;

public abstract class UiDialogFragment<U, L> extends DialogFragment {
    private UiWrapper<U, L, ?> wrapper;

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedState) {
        super.onCreate(savedState);
        wrapper = uiWrapper(savedState);
        ArgChecker.notNull(wrapper, UiFragment.class, "wrapper");
    }

    @NonNull
    protected abstract UiWrapper<U, L, ?> uiWrapper(@Nullable Bundle savedState);

    @Override
    @CallSuper
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedState) {
        super.onViewCreated(view, savedState);
        wrapper.bind(ui());
    }

    @NonNull
    protected abstract U ui();

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        wrapper.registerResources();
    }

    @Override
    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        wrapper.saveState(outState);
    }

    @Override
    @CallSuper
    public void onStop() {
        super.onStop();
        wrapper.unregisterResources();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        wrapper.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wrapper = null;
    }

    @SuppressWarnings("unused")
    @NonNull
    protected final L listener() {
        return wrapper.uiListener();
    }
}
