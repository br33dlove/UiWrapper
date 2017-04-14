package com.davidc.uiwrapper;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

class CastHelper {

    static <R extends BaseUiWrapperRepository> R repositoryFromFactory(final Activity activity) {
        final Application application = activity.getApplication();
        try {
            //noinspection unchecked
            return ((UiWrapperRepositoryFactory<R>) application).create();
        } catch (ClassCastException cce) {
            throw new ClassCastException(application.getClass().getSimpleName() + " must implement UiWrapperRepositoryFactory, with generic type of BaseUiWrapperRepository subclass");
        }
    }

    static <R extends BaseUiWrapperRepository> UiWrapperRepositoryProvider<R> repositoryProvider(final Context context) {
        try {
            //noinspection unchecked
            return (UiWrapperRepositoryProvider<R>) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(context.getClass().getSimpleName() + " must implement UiWrapperRepositoryProvider, with generic type of BaseUiWrapperRepository subclass");
        }
    }
}
