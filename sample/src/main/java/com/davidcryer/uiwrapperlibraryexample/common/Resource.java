package com.davidcryer.uiwrapperlibraryexample.common;

import java.util.HashSet;
import java.util.Set;

public class Resource {
    private final Set<Listener> listeners = new HashSet<>();

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

    public interface Listener {
        void listenerCount(final int count);
    }
}
