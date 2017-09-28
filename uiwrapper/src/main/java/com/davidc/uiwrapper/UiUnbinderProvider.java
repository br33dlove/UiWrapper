package com.davidc.uiwrapper;

import android.app.Application;
import android.os.Bundle;

class UiUnbinderProvider {

    private UiUnbinderProvider() {

    }

    static UiUnbinder get(final Application application, final String instanceId, final Bundle outState, final boolean isConfigurationChange) {
        return application instanceof UiUnbinderFactory
                ? ((UiUnbinderFactory) application).get(instanceId, outState, isConfigurationChange)
                : defaultUnbinder(instanceId, outState, isConfigurationChange);
    }

    private static UiUnbinder defaultUnbinder(final String instanceId, final Bundle outState, final boolean isConfigurationChange) {
        return new DefaultUnbinder(instanceId, outState, isConfigurationChange);
    }
}
