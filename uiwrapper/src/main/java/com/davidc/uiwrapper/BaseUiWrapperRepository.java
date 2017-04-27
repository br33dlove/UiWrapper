package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;

public abstract class BaseUiWrapperRepository {

    protected static <U extends Ui, L extends Ui.Listener> L bind(
            @NonNull final U ui,
            @NonNull final String instanceId,
            @NonNull final Map<String, UiWrapper<U, L>> uiWrapperMap,
            @NonNull final UiWrapperProvider<U, L> uiWrapperProvider
    ) {
        ArgChecker.notNull(ui, "ui");
        ArgChecker.notNull(instanceId, "instanceId");
        ArgChecker.notNull(uiWrapperMap, "uiWrapperMap");
        ArgChecker.notNull(uiWrapperProvider, "uiWrapperProvider");
        UiWrapper<U, L> uiWrapper = uiWrapperMap.get(instanceId);
        if (uiWrapper == null) {
            uiWrapper = uiWrapperProvider.uiWrapper();
            ArgChecker.notNull(uiWrapper, "uiWrapper");
            uiWrapperMap.put(instanceId, uiWrapper);
        }
        return uiWrapper.bind(ui);
    }

    protected static <U extends Ui, L extends Ui.Listener> void unbind(
            @NonNull final String instanceId,
            @NonNull final Map<String, UiWrapper<U, L>> uiWrapperMap,
            @Nullable final Bundle outState,
            final boolean isConfigurationChange
    ) {
        ArgChecker.notNull(instanceId, "instanceId");
        ArgChecker.notNull(uiWrapperMap, "uiWrapperMap");
        final UiWrapper<U, L> uiWrapper = uiWrapperMap.get(instanceId);
        if (uiWrapper != null) {
            uiWrapper.unbind();
            if (!isConfigurationChange) {
                uiWrapper.unregisterResources();
                if (outState != null) {
                    uiWrapper.saveState(outState);
                } else {
                    uiWrapperMap.remove(instanceId);
                }
            }
        } else if (uiWrapperMap.containsKey(instanceId)) {
            uiWrapperMap.remove(instanceId);
        }
    }

    protected interface UiWrapperProvider<V extends Ui, L extends Ui.Listener> {
        @NonNull
        UiWrapper<V, L> uiWrapper();
    }
}
