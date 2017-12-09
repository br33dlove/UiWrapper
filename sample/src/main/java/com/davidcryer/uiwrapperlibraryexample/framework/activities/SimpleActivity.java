package com.davidcryer.uiwrapperlibraryexample.framework.activities;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.davidcryer.uiwrapperlibraryexample.R;

public abstract class SimpleActivity extends AppCompatActivity {

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        setupToolbar();
        addInitialFragmentIfNone();
    }

    private void setupToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            setupActionBar(actionBar);
        }
    }

    protected abstract void setupActionBar(@NonNull final ActionBar actionBar);

    private void addInitialFragmentIfNone() {
        if (FragmentManagerHelper.noFragmentBoundToView(getSupportFragmentManager(), getContentFragmentViewContainer())) {
            addInitialFragment();
        }
    }

    protected abstract void addInitialFragment();

    protected void addFragment(final Fragment fragment, final String tag) {
        FragmentManagerHelper.addFragment(getSupportFragmentManager(), fragment, getContentFragmentViewContainer(), tag);
    }

    @IdRes
    protected int getContentFragmentViewContainer() {
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
