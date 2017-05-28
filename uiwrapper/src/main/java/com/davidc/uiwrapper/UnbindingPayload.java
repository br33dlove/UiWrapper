package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class UnbindingPayload {
    private String instanceId;
    private Bundle outState;
    private boolean isConfigurationChange;

    public UnbindingPayload(@NonNull String instanceId, @Nullable Bundle outState, boolean isConfigurationChange) {
        ArgChecker.notNull(instanceId, "instanceId");
        this.instanceId = instanceId;
        this.outState = outState;
        this.isConfigurationChange = isConfigurationChange;
    }

    public String instanceId() {
        return instanceId;
    }

    public void instanceId(@NonNull final String instanceId) {
        ArgChecker.notNull(instanceId, "instanceId");
        this.instanceId = instanceId;
    }

    public Bundle outState() {
        return outState;
    }

    public void outState(@Nullable final Bundle outState) {
        this.outState = outState;
    }

    public boolean isConfigurationChange() {
        return isConfigurationChange;
    }

    public void isConfigurationChange(final boolean isConfigurationChange) {
        this.isConfigurationChange = isConfigurationChange;
    }
}
