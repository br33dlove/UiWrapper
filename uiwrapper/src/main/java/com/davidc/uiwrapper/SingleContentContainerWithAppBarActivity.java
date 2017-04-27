package com.davidc.uiwrapper;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

public abstract class SingleContentContainerWithAppBarActivity<Repo extends BaseUiWrapperRepository> extends UiWrapperRepositoryActivity<Repo> {

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_with_content);
        setupToolbar();
        addInitialFragment();
    }

    private void setupToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            setupActionBar(actionBar);
        }
    }

    protected abstract void setupActionBar(@NonNull final ActionBar actionBar);

    private void addInitialFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (FragmentManagerHelper.noFragmentBoundToView(fragmentManager, getContentFragmentViewContainer())) {
            FragmentManagerHelper.addFragment(fragmentManager, initialFragment(), getContentFragmentViewContainer());
        }
    }

    @NonNull
    protected abstract Fragment initialFragment();

    @IdRes
    private int getContentFragmentViewContainer() {
        return R.id.content;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (FragmentManagerHelper.hasMoreThanFragmentOnBackStack(fragmentManager)) {
            super.onBackPressed();
            return;
        }
        finish();
    }
}
