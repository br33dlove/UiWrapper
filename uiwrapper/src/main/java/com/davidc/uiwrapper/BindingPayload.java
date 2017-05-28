package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class BindingPayload {
    private String instanceId;
    private Bundle savedInstanceState;

    public BindingPayload(@NonNull String instanceId, @Nullable Bundle savedInstanceState) {
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
}
