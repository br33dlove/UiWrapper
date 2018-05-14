package com.davidcryer.uiwrapperlibraryexample.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.davidc.uiwrapper.UiWrapper;
import com.davidc.uiwrapper.UiWrapperFactoryFragment;
import com.davidcryer.uiwrapperlibraryexample.R;
import com.davidcryer.uiwrapperlibraryexample.framework.activities.navigation.ExampleFragmentNavigator;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.UiWrapperFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.ExampleUi;

public class ExampleFragment extends UiWrapperFactoryFragment<ExampleUi, ExampleUi.Listener, UiWrapperFactory> {
    private ExampleFragmentNavigator navigator;
    private TextView resourceListenersCountTextView;
    private TextView timeOfLastStateRecoveryTextView;
    private TextView buttonClickCounterTextView;

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
        resourceListenersCountTextView = view.findViewById(R.id.resourceListenersCount);
        timeOfLastStateRecoveryTextView = view.findViewById(R.id.timeOfLastStateRecovery);
        buttonClickCounterTextView = view.findViewById(R.id.buttonClickCounter);
        view.findViewById(R.id.newExampleActivityButton).setOnClickListener(newExampleActivityButtonOnClickListener);
        view.findViewById(R.id.newExampleFragmentButton).setOnClickListener(newExampleFragmentButtonOnClickListener);
        return view;
    }

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
            resourceListenersCountTextView.setText(text);
        }

        @Override
        public void showTimeOfLastStateRecoveryText(String text) {
            timeOfLastStateRecoveryTextView.setText(text);
        }

        @Override
        public void showButtonClickCountText(String text) {
            buttonClickCounterTextView.setText(text);
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
