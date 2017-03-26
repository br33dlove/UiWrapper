package com.example.davidc.viewwrapper;

import android.os.Bundle;

import java.util.Map;

public class BaseViewWrapperRepository {

    protected static <V extends View, L extends View.EventsListener> L bind(
            final V view,
            final String instanceId,
            final Map<String, ViewWrapper<V, L>> viewWrapperMap,
            final ViewWrapperProvider<V, L> viewWrapperProvider
    ) {
        ViewWrapper<V, L> viewWrapper = viewWrapperMap.get(instanceId);
        if (viewWrapper == null) {
            viewWrapper = viewWrapperProvider.viewWrapper();
            viewWrapperMap.put(instanceId, viewWrapper);
        }
        return viewWrapper.bind(view);
    }

    protected static <V extends View, L extends View.EventsListener> void unbind(
            final String instanceId,
            final Map<String, ViewWrapper<V, L>> viewWrapperMap,
            final Bundle outState,
            final boolean isConfigurationChange
    ) {
        final ViewWrapper<V, L> viewWrapper = viewWrapperMap.get(instanceId);
        if (viewWrapper != null) {
            viewWrapper.unbind();
            if (!isConfigurationChange) {
                viewWrapper.unregisterResources();
                if (outState != null) {
                    viewWrapper.saveState(outState);
                } else {
                    viewWrapperMap.remove(instanceId);
                }
            }
        } else if (viewWrapperMap.containsKey(instanceId)) {
            viewWrapperMap.remove(instanceId);
        }
    }

    protected interface ViewWrapperProvider<V extends View, L extends View.EventsListener> {
        ViewWrapper<V, L> viewWrapper();
    }
}
