package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;

public class DefaultBinder implements UiBinder {
    private String instanceId;
    private Bundle savedInstanceState;

    public DefaultBinder(@NonNull String instanceId, @Nullable Bundle savedInstanceState) {
        ArgChecker.notNull(instanceId, "instanceId");
        this.instanceId = instanceId;
        this.savedInstanceState = savedInstanceState;
    }

    public @NonNull String instanceId() {
        return instanceId;
    }

    public void instanceId(@NonNull final String instanceId) {
        ArgChecker.notNull(instanceId, "instanceId");
        this.instanceId = instanceId;
    }

    public Bundle savedInstanceState() {
        return savedInstanceState;
    }

    public void savedInstanceState(@Nullable final Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    public <U, L, M extends UiModel<U>> L bind(@NonNull final U ui, @NonNull final Map<String, UiWrapper<U, L, M>> uiWrapperMap, @NonNull final UiWrapperProvider<U, L, M> uiWrapperProvider) {
        ArgChecker.notNull(ui, "ui");
        ArgChecker.notNull(uiWrapperMap, "uiWrapperMap");
        ArgChecker.notNull(uiWrapperProvider, "uiWrapperProvider");
        UiWrapper<U, L, M> uiWrapper = uiWrapperMap.get(instanceId);
        if (uiWrapper == null) {
            uiWrapper = uiWrapperProvider.uiWrapper(savedInstanceState);
            ArgChecker.notNull(uiWrapper, "uiWrapper");
            uiWrapperMap.put(instanceId, uiWrapper);
        }
        return uiWrapper.bind(ui);
    }
}
