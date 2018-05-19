package com.davidcryer.uiwrapperlibraryexample.framework.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;

import com.davidcryer.simpleactivities.SimpleAppBarActivity;
import com.davidcryer.uiwrapperlibraryexample.androidhelpers.DrawableHelper;
import com.davidcryer.uiwrapperlibraryexample.framework.activities.navigation.ExampleFragmentNavigator;
import com.davidcryer.uiwrapperlibraryexample.ui.ExampleDialogFragment;
import com.davidcryer.uiwrapperlibraryexample.ui.ExampleFragment;
import com.davidcryer.uiwrapperlibraryexample.R;

public class ExampleActivity extends SimpleAppBarActivity implements ExampleFragmentNavigator {
    private final static String ARG_INTENT_SHOW_HOME_AS_BACK = "show home as back";
    private final static String TAG_EXAMPLE_DIALOG = "example dialog";

    @Override
    protected void setupActionBar(@NonNull ActionBar actionBar) {
        final boolean showHomeAsBack = getIntent().getBooleanExtra(ARG_INTENT_SHOW_HOME_AS_BACK, false);
        actionBar.setDisplayShowHomeEnabled(showHomeAsBack);
        actionBar.setDisplayHomeAsUpEnabled(showHomeAsBack);
        actionBar.setHomeAsUpIndicator(DrawableHelper.drawableForColor(this, R.drawable.ic_arrow_back_black_24dp, android.R.color.white));
    }

    @Override
    protected void addInitialFragment() {
        add(null, ExampleFragment::newInstance);
    }

    @Override
    public void showExampleDialog() {
        if (getSupportFragmentManager().findFragmentByTag(TAG_EXAMPLE_DIALOG) == null) {
            new ExampleDialogFragment().show(getSupportFragmentManager(), TAG_EXAMPLE_DIALOG);
        }
    }

    @Override
    public void showNewExampleActivity() {
        startActivity(new Intent(this, ExampleActivity.class).putExtra(ARG_INTENT_SHOW_HOME_AS_BACK, true));
    }

    @Override
    public void showNewExampleFragment() {
        replaceWithNewExampleFragment();
    }

    private void replaceWithNewExampleFragment() {
        replace(null, ExampleFragment::new, anims(R.anim.enter_right, R.anim.exit_left), anims(R.anim.enter_left, R.anim.exit_right));
    }
}
