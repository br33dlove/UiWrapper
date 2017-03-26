package com.example.davidc.viewwrapper;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class ViewWrapper<ViewType extends View, EventsListenerType extends View.EventsListener> {
    private boolean resourcesRegistered = false;
    private ViewType view;

    EventsListenerType bind(final ViewType view) {
        if (!resourcesRegistered) {
            registerResources();
        }
        this.view = view;
        showCurrentViewState(view);
        return eventsListener();
    }

    protected abstract void registerResources();

    protected abstract void showCurrentViewState(final ViewType view);

    protected abstract EventsListenerType eventsListener();

    void unbind() {
        view = null;
    }

    protected abstract void saveState(final Bundle outState);

    protected abstract void unregisterResources();

    @Nullable
    protected ViewType view() {
        return view;
    }
}
