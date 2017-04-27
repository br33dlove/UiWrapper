//Copyright 2017 David Cryer
//
//        Licensed under the Apache License, Version 2.0 (the "License");
//        you may not use this file except in compliance with the License.
//        You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//        Unless required by applicable law or agreed to in writing, software
//        distributed under the License is distributed on an "AS IS" BASIS,
//        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//        See the License for the specific language governing permissions and
//        limitations under the License.

package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class UiWrapper<U extends Ui, L extends Ui.Listener, M extends UiModel<U>> {
    private final static String BUNDLE_ARG_UI_MODEL = "ui model";
    private final M uiModel;
    private boolean resourcesRegistered = false;
    private U ui;

    protected UiWrapper(@NonNull final M uiModel) {
        ArgChecker.notNull(uiModel, "uiModel");
        this.uiModel = uiModel;
    }

    final L bind(final U ui) {
        if (!resourcesRegistered) {
            registerResources();
        }
        this.ui = ui;
        uiModel.onto(ui);
        return uiListener();
    }

    @CallSuper
    protected void registerResources() {
        resourcesRegistered = true;
    }

    protected abstract L uiListener();

    final void unbind() {
        ui = null;
    }

    final void saveState(final Bundle outState) {
        outState.putParcelable(BUNDLE_ARG_UI_MODEL, uiModel);
    }

    @SuppressWarnings("unused")
    @Nullable
    protected static <U extends Ui, M extends UiModel<U>> M savedUiModel(final Bundle savedState) {
        return savedState.getParcelable(BUNDLE_ARG_UI_MODEL);
    }

    @CallSuper
    protected void unregisterResources() {
        resourcesRegistered = false;
    }

    @SuppressWarnings("unused")
    @Nullable
    protected final U ui() {
        return ui;
    }

    @SuppressWarnings("unused")
    @NonNull
    protected final M uiModel() {
        return uiModel;
    }
}
