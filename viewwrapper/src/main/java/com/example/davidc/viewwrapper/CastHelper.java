package com.example.davidc.viewwrapper;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

class CastHelper {

    static <R extends ViewWrapperRepository> R viewWrapperRepositoryFromFactory(final Activity activity) {
        final Application application = activity.getApplication();
        try {
            //noinspection unchecked
            return ((ViewWrapperRepositoryFactory<R>) application).create();
        } catch (ClassCastException cce) {
            throw new ClassCastException(application.getClass().getSimpleName() + " must implement ViewWrapperRepositoryFactory, with generic type of ViewWrapperRepository subclass");
        }
    }

    static <R extends ViewWrapperRepository> ViewWrapperRepositoryProvider<R> viewWrapperRepositoryProvider(final Context context) {
        try {
            //noinspection unchecked
            return (ViewWrapperRepositoryProvider<R>) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(context.getClass().getSimpleName() + " must implement ViewWrapperRepositoryFactory, with generic type of ViewWrapperRepository subclass");
        }
    }
}
