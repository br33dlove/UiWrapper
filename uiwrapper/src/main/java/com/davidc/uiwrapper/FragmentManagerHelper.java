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
