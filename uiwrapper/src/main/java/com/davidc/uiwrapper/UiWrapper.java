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

public abstract class UiWrapper<U extends Ui, L extends Ui.Listener> {
    private boolean resourcesRegistered = false;
    private U ui;

    final L bind(final U ui) {
        if (!resourcesRegistered) {
            registerResources();
        }
        this.ui = ui;
        showCurrentUiState(ui);
        return uiListener();
    }

    @CallSuper
    protected void registerResources() {
        resourcesRegistered = true;
    }

    protected abstract void showCurrentUiState(@NonNull final U ui);

    protected abstract L uiListener();

    final void unbind() {
        ui = null;
    }

    protected abstract void saveState(final Bundle outState);

    @CallSuper
    protected void unregisterResources() {
        resourcesRegistered = false;
    }

    @Nullable
    protected final U ui() {
        return ui;
    }
}
