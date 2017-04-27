package com.davidc.uiwrapper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.UUID;

public abstract class UiFragment<R extends BaseUiWrapperRepository, L extends Ui.Listener> extends Fragment {
    private final static String ARG_SAVED_INSTANCE_STATE_INSTANCE_ID = "instance id";
    private UiWrapperRepositoryProvider<R> repositoryProvider;
    private boolean isBound = false;
    private L listener;
    private String instanceId;

    @Override
    @CallSuper
    public void onAttach(Context context) {
        super.onAttach(context);
        repositoryProvider = CastHelper.repositoryProvider(context);
    }

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instanceId = savedInstanceState == null ? UUID.randomUUID().toString() : savedInstanceState.getString(ARG_SAVED_INSTANCE_STATE_INSTANCE_ID);
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(savedInstanceState);
    }

    private void bind(final Bundle savedInstanceState) {
        listener = bind(repositoryProvider.get(), instanceId, savedInstanceState);
        isBound = true;
    }

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        bindIfUnbound(null);
    }

    private void bindIfUnbound(final Bundle savedInstanceState) {
        if (!isBound) {
            bind(savedInstanceState);
        }
    }

    @Override
    @CallSuper
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_SAVED_INSTANCE_STATE_INSTANCE_ID, instanceId);
        unbind(outState);
    }

    private void unbind(final Bundle outState) {
        unbind(repositoryProvider.get(), instanceId, outState, getActivity().isChangingConfigurations());
        listener = null;
        isBound = false;
    }

    @Override
    @CallSuper
    public void onStop() {
        super.onStop();
        unbindIfBound(null);
    }

    private void unbindIfBound(final Bundle outState) {
        if (isBound) {
            unbind(outState);
        }
    }

    @Override
    @CallSuper
    public void onDetach() {
        super.onDetach();
        repositoryProvider = null;
    }

    protected abstract L bind(final R uiWrapperRepository, final String instanceId, final Bundle savedInstanceState);

    protected abstract void unbind(final R uiWrapperRepository, final String instanceId, final Bundle outState, final boolean isConfigurationChange);

    protected final boolean hasListener() {
        return listener != null;
    }

    protected final L listener() {
        return listener;
    }
}
