package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@SuppressWarnings("unused")
public final class UiWrapperInitializer {

    public static <T extends UiWrapper> T from(@Nullable final Bundle savedState, final NewWrapperProvider<T> newWrapperProvider, final SavedWrapperProvider<T> savedWrapperProvider) {
        if (savedState != null) {
            final T savedWrapper = savedWrapperProvider.from(savedState);
            if (savedWrapper != null) {
                return savedWrapper;
            }
        }
        return newWrapperProvider.get();
    }

    public interface NewWrapperProvider<T extends UiWrapper> {
        T get();
    }

    public interface SavedWrapperProvider<T extends UiWrapper> {
        T from(@NonNull Bundle savedState);
    }
}
