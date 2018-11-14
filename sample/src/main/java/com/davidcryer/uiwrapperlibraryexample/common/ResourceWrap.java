package com.davidcryer.uiwrapperlibraryexample.common;

import java.util.HashSet;
import java.util.Set;

public class ResourceWrap {
    private final Set<Listener> listeners = new HashSet<>();
    private final Resource resource;

    ResourceWrap(final Resource resource) {
        this.resource = resource;
    }

    public void resource(final ResourceRequest request) {
        request.response(resource.value());
    }

    public void value(final String value) {
        resource.value(value);
    }

    public void register(final Listener listener) {
        if (listener == null) {
            return;
        }
        listeners.add(listener);
        notifyListenersOfListenerCountChange();
    }

    public void unregister(final Listener listener) {
        if (listener == null) {
            return;
        }
        listeners.remove(listener);
        notifyListenersOfListenerCountChange();
    }

    private void notifyListenersOfListenerCountChange() {
        final int listenerCount = listeners.size();
        for (final Listener listener : listeners) {
            listener.listenerCount(listenerCount);
        }
    }

    public interface ResourceRequest {
        void response(final String value);
    }

    public interface Listener {
        void listenerCount(final int count);
    }
}
