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
import android.support.annotation.NonNull;

import java.util.Map;

public abstract class BaseUiWrapperRepository {

    protected static <U extends Ui, L extends Ui.Listener, M extends UiModel<U>> L bind(
            @NonNull final U ui,
            @NonNull final Map<String, UiWrapper<U, L, M>> uiWrapperMap,
            @NonNull final BindingPayload bindingPayload,
            @NonNull final UiWrapperProvider<U, L, M> uiWrapperProvider
    ) {
        ArgChecker.notNull(ui, "ui");
        ArgChecker.notNull(uiWrapperMap, "uiWrapperMap");
        ArgChecker.notNull(bindingPayload, "bindingPayload");
        ArgChecker.notNull(uiWrapperProvider, "uiWrapperProvider");
        UiWrapper<U, L, M> uiWrapper = uiWrapperMap.get(bindingPayload.instanceId());
        if (uiWrapper == null) {
            uiWrapper = uiWrapperProvider.uiWrapper(bindingPayload.savedInstanceState());
            ArgChecker.notNull(uiWrapper, "uiWrapper");
            uiWrapperMap.put(bindingPayload.instanceId(), uiWrapper);
        }
        return uiWrapper.bind(ui);
    }

    protected static <U extends Ui, L extends Ui.Listener, M extends UiModel<U>> void unbind(
            @NonNull final Map<String, UiWrapper<U, L, M>> uiWrapperMap,
            @NonNull final UnbindingPayload unbindingPayload
    ) {
        ArgChecker.notNull(uiWrapperMap, "uiWrapperMap");
        ArgChecker.notNull(unbindingPayload, "unbindingPayload");
        final UiWrapper<U, L, M> uiWrapper = uiWrapperMap.get(unbindingPayload.instanceId());
        if (uiWrapper != null) {
            uiWrapper.unbind();
            if (!unbindingPayload.isConfigurationChange()) {
                uiWrapper.unregisterResources();
                final Bundle outState = unbindingPayload.outState();
                if (outState != null) {
                    uiWrapper.saveState(outState);
                } else {
                    uiWrapperMap.remove(unbindingPayload.instanceId());
                }
            }
        } else if (uiWrapperMap.containsKey(unbindingPayload.instanceId())) {
            uiWrapperMap.remove(unbindingPayload.instanceId());
        }
    }

    protected interface UiWrapperProvider<U extends Ui, L extends Ui.Listener, M extends UiModel<U>> {
        @NonNull
        UiWrapper<U, L, M> uiWrapper(final Bundle savedInstanceState);
    }
}
