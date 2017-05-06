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

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

final class FragmentManagerHelper {

    private FragmentManagerHelper() {

    }

    static boolean noFragmentBoundToView(
        final FragmentManager fragmentManager,
        @IdRes final int view
    ) {
        return fragmentManager.findFragmentById(view) == null;
    }

    static boolean hasFragment(final FragmentManager fm, final String tag) {
        return fragment(fm, tag) != null;
    }

    static void addFragment(final FragmentManager fm, final Fragment fragment, final String tag) {
        fm.beginTransaction().add(fragment, tag).commit();
    }

    static void addFragment(
            final FragmentManager fragmentManager,
            final Fragment fragment,
            @IdRes final int view
    ) {
        fragmentManager
                .beginTransaction()
                .add(view, fragment)
                .addToBackStack(null)
                .commit();
    }

    static Fragment fragment(final FragmentManager fm, final String tag) {
        return fm.findFragmentByTag(tag);
    }

    static boolean hasMoreThanFragmentOnBackStack(final FragmentManager fragmentManager) {
        return fragmentManager.getBackStackEntryCount() > 1;
    }
}
