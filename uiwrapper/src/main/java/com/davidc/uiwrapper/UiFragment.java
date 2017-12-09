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
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

@SuppressWarnings("unused")
public abstract class UiFragment<U, L> extends Fragment {
    private UiWrapper<U, L, ?> wrapper;

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedState) {
        super.onCreate(savedState);
        wrapper = uiWrapper(savedState);
    }

    protected abstract UiWrapper<U, L, ?> uiWrapper(@Nullable Bundle savedState);

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedState) {
        super.onViewCreated(view, savedState);
        wrapper.bind(ui());
    }

    protected abstract U ui();

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        wrapper.registerResources();
    }

    @Override
    @CallSuper
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        wrapper.saveState(outState);
    }

    @Override
    @CallSuper
    public void onStop() {
        super.onStop();
        wrapper.unregisterResources();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        wrapper.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wrapper = null;
    }

    @SuppressWarnings("unused")
    protected final L listener() {
        return wrapper.uiListener();
    }
}
