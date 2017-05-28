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

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.UUID;

@SuppressWarnings("unused")
public abstract class UiFragment<R extends BaseUiWrapperRepository, L extends Ui.Listener> extends Fragment {
    private final static String ARG_SAVED_INSTANCE_STATE_INSTANCE_ID = "instance id";
    private UiWrapperRepositoryProvider<R> repositoryProvider;
    private boolean isBound = false;
    private L listener;
    private String instanceId;

    @Override
    @CallSuper
    public void onAttach(Context context) {
        super.onAttach(context);
        repositoryProvider = CastHelper.repositoryProvider(context);
    }

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instanceId = savedInstanceState == null ? UUID.randomUUID().toString() : savedInstanceState.getString(ARG_SAVED_INSTANCE_STATE_INSTANCE_ID);
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(savedInstanceState);
    }

    private void bind(final Bundle savedInstanceState) {
        listener = bind(repositoryProvider.get(), new BindingPayload(instanceId, savedInstanceState));
        isBound = true;
    }

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        bindIfUnbound(null);
    }

    private void bindIfUnbound(final Bundle savedInstanceState) {
        if (!isBound) {
            bind(savedInstanceState);
        }
    }

    @Override
    @CallSuper
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_SAVED_INSTANCE_STATE_INSTANCE_ID, instanceId);
        unbind(outState);
    }

    private void unbind(final Bundle outState) {
        unbind(repositoryProvider.get(), new UnbindingPayload(instanceId, outState, getActivity().isChangingConfigurations()));
        listener = null;
        isBound = false;
    }

    @Override
    @CallSuper
    public void onStop() {
        super.onStop();
        unbindIfBound(null);
    }

    private void unbindIfBound(final Bundle outState) {
        if (isBound) {
            unbind(outState);
        }
    }

    @Override
    @CallSuper
    public void onDetach() {
        super.onDetach();
        repositoryProvider = null;
    }

    protected abstract L bind(@NonNull final R uiWrapperRepository, @NonNull final BindingPayload bindingPayload);

    protected abstract void unbind(@NonNull final R uiWrapperRepository, @NonNull final UnbindingPayload unbindingPayload);

    @SuppressWarnings("unused")
    protected final boolean hasListener() {
        return listener != null;
    }

    @SuppressWarnings("unused")
    protected final L listener() {
        return listener;
    }
}
