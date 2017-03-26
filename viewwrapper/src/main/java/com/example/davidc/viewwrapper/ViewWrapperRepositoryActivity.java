package com.example.davidc.viewwrapper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ViewWrapperRepositoryActivity<R extends ViewWrapperRepository> extends AppCompatActivity implements ViewWrapperRepositoryProvider {
    private final static String VIEW_WRAPPER_FRAGMENT_TAG = "view wrapper repository";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!FragmentManagerHelper.hasFragment(getSupportFragmentManager(), VIEW_WRAPPER_FRAGMENT_TAG)) {
            setupViewWrapperRepositoryRetainFragment();
        }
    }

    private void setupViewWrapperRepositoryRetainFragment() {
        FragmentManagerHelper.addFragment(
                getSupportFragmentManager(),
                new ViewWrapperRepositoryFragment<>(),
                VIEW_WRAPPER_FRAGMENT_TAG
        );
    }

    @Override
    public R get() {
        return viewWrapperRepositoryFragment().get();
    }

    private ViewWrapperRepositoryFragment<R> viewWrapperRepositoryFragment() {
        //noinspection unchecked
        return (ViewWrapperRepositoryFragment<R>) FragmentManagerHelper.fragment(getSupportFragmentManager(), VIEW_WRAPPER_FRAGMENT_TAG);
    }
}
