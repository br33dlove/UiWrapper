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

final class CastHelper {

    private CastHelper() {

    }

    static UiWrapperRepositoryFactory repositoryFactory(final Context context) {
        try {
            return ((UiWrapperRepositoryFactory) context);
        } catch (ClassCastException cce) {
            throw new ClassCastException(context.getClass().getSimpleName() + " must implement UiWrapperRepositoryFactory");
        }
    }

    static UiWrapperRepositoryProvider repositoryProvider(final Context context) {
        try {
            return (UiWrapperRepositoryProvider) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(context.getClass().getSimpleName() + " must implement UiWrapperRepositoryProvider");
        }
    }
}
