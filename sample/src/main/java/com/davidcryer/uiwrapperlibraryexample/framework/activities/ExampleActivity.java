package com.davidcryer.uiwrapperlibraryexample.framework.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.davidc.uiwrapper.SingleContentContainerWithAppBarActivity;
import com.davidcryer.uiwrapperlibraryexample.androidhelpers.DrawableHelper;
import com.davidcryer.uiwrapperlibraryexample.framework.activities.navigation.ExampleFragmentNavigator;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.UiWrapperRepository;
import com.davidcryer.uiwrapperlibraryexample.ui.ExampleFragment;
import com.davidcryer.uiwrapperlibraryexample.R;

public class ExampleActivity extends SingleContentContainerWithAppBarActivity<UiWrapperRepository> implements ExampleFragmentNavigator {
    private final static String ARG_INTENT_SHOW_HOME_AS_BACK = "show home as back";

    private static void start(final Context context, final boolean showHomeAsBack) {
        context.startActivity(new Intent(context, ExampleActivity.class).putExtra(ARG_INTENT_SHOW_HOME_AS_BACK, showHomeAsBack));
    }

    @Override
    protected void setupActionBar(@NonNull ActionBar actionBar) {
        final boolean showHomeAsBack = getIntent().getBooleanExtra(ARG_INTENT_SHOW_HOME_AS_BACK, false);
        actionBar.setDisplayShowHomeEnabled(showHomeAsBack);
        actionBar.setDisplayHomeAsUpEnabled(showHomeAsBack);
        actionBar.setHomeAsUpIndicator(DrawableHelper.drawableForColor(this, R.drawable.ic_arrow_back_black_24dp, android.R.color.white));
    }

    @Override
    @NonNull
    protected Fragment initialFragment() {
        return ExampleFragment.newInstance();
    }

    @Override
    public void showNewExampleUi() {
        start(this, true);
    }
}
