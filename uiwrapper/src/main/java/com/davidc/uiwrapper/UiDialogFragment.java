package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;

public abstract class UiDialogFragment<U, L> extends DialogFragment implements UiFragmentContract<U, L> {
    private final UiFragmentCore<U, L> core = new UiFragmentCore<>(this);

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedState) {
        super.onCreate(savedState);
        core.onCreate(savedState);
    }

    @Override
    @CallSuper
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedState) {
        super.onViewCreated(view, savedState);
        core.onViewCreated();
    }

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        core.onStart();
    }

    @Override
    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        core.onSaveInstanceState(outState);
    }

    @Override
    @CallSuper
    public void onStop() {
        super.onStop();
        core.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        core.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        core.onDestroy();
    }

    @SuppressWarnings("unused")
    @NonNull
    protected final L listener() {
        return core.listener();
    }
}
