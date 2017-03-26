package com.example.davidc.viewwrapper;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class ViewWrapper<ViewType extends View, EventsListenerType extends View.EventsListener> {
    private ViewType view;

    EventsListenerType bind(final ViewType view) {
        this.view = view;
        showCurrentViewState(view);
        return eventsListener();
    }

    protected abstract void showCurrentViewState(final ViewType view);

    protected abstract EventsListenerType eventsListener();

    void unbind() {
        view = null;
    }

    protected abstract void saveState(final Bundle outState);

    protected abstract void releaseResources();

    @Nullable
    protected ViewType view() {
        return view;
    }
}
