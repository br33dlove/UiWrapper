package com.example.davidc.viewwrapper;

import android.app.Activity;
import android.app.Application;

class CastHelper {

    static <R extends ViewWrapperRepository> R viewWrapperRepositoryFromFactory(final Activity activity) {
        final Application application = activity.getApplication();
        try {
            //noinspection unchecked
            return ((ViewWrapperRepositoryFactory<R>) application).create();
        } catch (ClassCastException cce) {
            throw new ClassCastException("Application " + application.getClass().getSimpleName() + " must implement ViewWrapperRepositoryFactory, with generic type of ViewWrapperRepository subclass");
        }
    }

    static <R extends ViewWrapperRepository> ViewWrapperRepositoryProvider<R> viewWrapperRepositoryProvider(final Activity activity) {
        try {
            //noinspection unchecked
            return (ViewWrapperRepositoryProvider<R>) activity;
        } catch (ClassCastException cce) {
            throw new ClassCastException("Activity " + activity.getClass().getSimpleName() + " must implement ViewWrapperRepositoryFactory, with generic type of ViewWrapperRepository subclass");
        }
    }
}
