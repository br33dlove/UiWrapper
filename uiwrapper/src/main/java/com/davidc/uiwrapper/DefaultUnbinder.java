package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;

public class DefaultUnbinder implements UiUnbinder {
    private String instanceId;
    private Bundle outState;
    private boolean isConfigurationChange;

    public DefaultUnbinder(@NonNull String instanceId, @Nullable Bundle outState, boolean isConfigurationChange) {
        ArgChecker.notNull(instanceId, "instanceId");
        this.instanceId = instanceId;
        this.outState = outState;
        this.isConfigurationChange = isConfigurationChange;
    }

    @Override
    public <U, L, M extends UiModel<U>> void unbind(@NonNull final Map<String, UiWrapper<U, L, M>> uiWrapperMap) {
        ArgChecker.notNull(uiWrapperMap, "uiWrapperMap");
        final UiWrapper<U, L, M> uiWrapper = uiWrapperMap.get(instanceId);
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
}
