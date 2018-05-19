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

public class ExampleFragment extends UiWrapperFactoryFragment<ExampleUi, ExampleUi.Listener, UiWrapperFactory> {
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
        public void showResourceListenersCountText(String text) {
            infoView.setResourceListenersCountText(text);
        }

        @Override
        public void showTimeOfLastStateRecoveryText(String text) {
            infoView.setTimeOfLastStateRecoveryText(text);
        }

        @Override
        public void showButtonClickCountText(String text) {
            infoView.setButtonClickCounterText(text);
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
}
