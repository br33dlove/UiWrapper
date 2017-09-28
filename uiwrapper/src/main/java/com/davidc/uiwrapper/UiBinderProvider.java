package com.davidc.uiwrapper;

import android.app.Application;
import android.os.Bundle;

class UiBinderProvider {

    private UiBinderProvider() {

    }

    static UiBinder get(final Application application, final String instanceId, final Bundle savedInstanceState) {
        return application instanceof UiBinderFactory
                ? ((UiBinderFactory) application).get(instanceId, savedInstanceState)
                : defaultBinder(instanceId, savedInstanceState);
    }

    private static UiBinder defaultBinder(final String instanceId, final Bundle savedInstanceState) {
        return new DefaultBinder(instanceId, savedInstanceState);
    }
}
