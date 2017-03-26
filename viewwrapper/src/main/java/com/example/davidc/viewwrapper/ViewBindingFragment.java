package com.example.davidc.viewwrapper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.UUID;

public abstract class ViewBindingFragment<R extends ViewWrapperRepository, L extends View.EventsListener> extends Fragment {
    private final static String ARG_SAVED_INSTANCE_STATE_INSTANCE_ID = "instance id";
    private ViewWrapperRepositoryProvider<R> repositoryProvider;
    private boolean isBound = false;
    private L eventsListener;
    private String instanceId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repositoryProvider = CastHelper.viewWrapperRepositoryProvider(getActivity());
        instanceId = savedInstanceState == null ? UUID.randomUUID().toString() : savedInstanceState.getString(ARG_SAVED_INSTANCE_STATE_INSTANCE_ID);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(savedInstanceState);
    }

    private void bind(final Bundle savedInstanceState) {
        eventsListener = bind(repositoryProvider.get(), instanceId, savedInstanceState);
        isBound = true;
    }

    @Override
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        unbind(outState);
    }

    private void unbind(final Bundle outState) {
        unbind(repositoryProvider.get(), instanceId, outState, getActivity().isChangingConfigurations());
        eventsListener = null;
        isBound = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        unbindIfBound(null);
    }

    private void unbindIfBound(final Bundle outState) {
        if (isBound) {
            unbind(outState);
        }
    }

    protected abstract L bind(final R viewWrapperRepository, final String instanceId, final Bundle savedInstanceState);

    protected abstract void unbind(final R viewWrapperRepository, final String instanceId, final Bundle outState, final boolean isConfigurationChange);

    protected boolean hasEventsListener() {
        return eventsListener != null;
    }

    protected L eventsListener() {
        return eventsListener;
    }
}
