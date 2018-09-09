package com.davidcryer.uiwrapperlibraryexample.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davidc.uiwrapper.UiWrapper;
import com.davidc.uiwrapper.UiWrapperFactoryFragment;
import com.davidcryer.uiwrapperlibraryexample.R;
import com.davidcryer.uiwrapperlibraryexample.framework.activities.navigation.ExampleFragmentNavigator;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.UiWrapperFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.ExampleUi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExampleFragment extends UiWrapperFactoryFragment<ExampleUi, ExampleUi.Listener, UiWrapperFactory> {
    private final static String FORMAT_BUTTON_CLICK_COUNTER = "Button click count: %1$s";
    private final static String FORMAT_RESOURCE_LISTENERS = "Number of resource listeners: %s";
    private final static String FORMAT_LAST_RECOVERY = "State last recovered on: %s";
    private final static String NO_LAST_RECOVERY = "State not yet recovered";
    private final static String SDF_LAST_RECOVERY = "EEE, d MMM yyyy HH:mm:ss";
    private ExampleFragmentNavigator navigator;
    private FragmentResourceInfoView infoView;

    public static ExampleFragment newInstance() {
        return new ExampleFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        navigator = (ExampleFragmentNavigator) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_example, container, false);
        infoView = view.findViewById(R.id.info);
        view.findViewById(R.id.showExampleDialogButton).setOnClickListener(showExampleDialogButtonOnClickListener);
        view.findViewById(R.id.newExampleActivityButton).setOnClickListener(newExampleActivityButtonOnClickListener);
        view.findViewById(R.id.newExampleFragmentButton).setOnClickListener(newExampleFragmentButtonOnClickListener);
        return view;
    }

    private final View.OnClickListener showExampleDialogButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener().onClickShowExampleDialog(ui);
        }
    };

    private final View.OnClickListener newExampleActivityButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener().onClickNewExampleActivity(ui);
        }
    };

    private final View.OnClickListener newExampleFragmentButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener().onClickNewExampleFragment(ui);
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        navigator = null;
    }

    @NonNull
    @Override
    public UiWrapper<ExampleUi, ExampleUi.Listener, ?> uiWrapper(@NonNull UiWrapperFactory uiWrapperFactory, @Nullable Bundle savedState) {
        return uiWrapperFactory.createExampleUiWrapper(savedState);
    }

    @NonNull
    @Override
    public ExampleUi ui() {
        return ui;
    }

    private final ExampleUi ui = new ExampleUi() {
        @Override
        public void showResourceListenersCount(int count) {
            infoView.setResourceListenersCountText(resourceListenersCountText(count));
        }

        @Override
        public void showTimeOfLastStateRecovery(long time) {
            infoView.setTimeOfLastStateRecoveryText(timeOfLastStateRecoveryText(time));
        }

        @Override
        public void showButtonClickCount(int count) {
            infoView.setButtonClickCounterText(String.format(FORMAT_BUTTON_CLICK_COUNTER, count));
        }

        @Override
        public void showExampleDialog() {
            navigator.showExampleDialog();
        }

        @Override
        public void showNewExampleActivity() {
            if (navigator != null) {
                navigator.showNewExampleActivity();
            }
        }

        @Override
        public void showNewExampleFragment() {
            if (navigator != null) {
                navigator.showNewExampleFragment();
            }
        }
    };

    static String timeOfLastStateRecoveryText(final long timeOfLastStateRecovery) {
        if (timeOfLastStateRecovery >= 0) {
            return String.format(FORMAT_LAST_RECOVERY, new SimpleDateFormat(SDF_LAST_RECOVERY, Locale.getDefault()).format(new Date(timeOfLastStateRecovery)));
        } else {
            return NO_LAST_RECOVERY;
        }
    }

    static String resourceListenersCountText(final int resourceListenersCount) {
        return String.format(FORMAT_RESOURCE_LISTENERS, resourceListenersCount);
    }
}
