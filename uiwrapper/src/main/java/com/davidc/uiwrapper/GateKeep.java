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

import android.support.annotation.NonNull;

final class GateKeep {
    private final static String FORMAT_CLASS_VAR_NAME = "%1$s.%2$s";

    private GateKeep() {}

    static void notNull(final Object o, @NonNull final Class clazz, @NonNull final String varName) {
        if (o == null) {
            throw new IllegalArgumentException(defaultExceptionMessage(classVarName(clazz.getSimpleName(), varName)));
        }
    }

    private static String classVarName(@NonNull final String className, @NonNull final String varName) {
        return String.format(FORMAT_CLASS_VAR_NAME, className, varName);
    }

    private static String defaultExceptionMessage(@NonNull final String classVarName) {
        return String.format("%1$s cannot be null", classVarName);
    }
}
