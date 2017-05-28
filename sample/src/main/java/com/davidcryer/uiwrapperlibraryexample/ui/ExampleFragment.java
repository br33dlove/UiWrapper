package com.davidcryer.uiwrapperlibraryexample.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.davidc.uiwrapper.BindingPayload;
import com.davidc.uiwrapper.UiFragment;
import com.davidc.uiwrapper.UnbindingPayload;
import com.davidcryer.uiwrapperlibraryexample.R;
import com.davidcryer.uiwrapperlibraryexample.framework.activities.navigation.ExampleFragmentNavigator;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.ExampleUi;
import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.UiWrapperRepository;

public class ExampleFragment extends UiFragment<UiWrapperRepository, ExampleUi.Listener> implements ExampleUi {
    private ExampleFragmentNavigator navigator;
    private TextView resourceListenersCountTextView;
    private TextView timeOfLastStateRecoveryTextView;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_example, container, false);
        resourceListenersCountTextView = (TextView) view.findViewById(R.id.resourceListenersCount);
        timeOfLastStateRecoveryTextView = (TextView) view.findViewById(R.id.timeOfLastStateRecovery);
        view.findViewById(R.id.newExampleUiButton).setOnClickListener(newExampleUiButtonOnClickListener);
        return view;
    }

    private final View.OnClickListener newExampleUiButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (hasListener()) {
                listener().onLaunchNewExampleUi(ExampleFragment.this);
            }
        }
    };

    @Override
    public void showResourceListenersCountText(String text) {
        resourceListenersCountTextView.setText(text);
    }

    @Override
    public void showTimeOfLastStateRecoveryText(String text) {
        timeOfLastStateRecoveryTextView.setText(text);
    }

    @Override
    public void showNewExampleUi() {
        if (navigator != null) {
            navigator.showNewExampleUi();
        }
    }

    @Override
    protected Listener bind(@NonNull UiWrapperRepository uiWrapperRepository, @NonNull BindingPayload bindingPayload) {
        return uiWrapperRepository.bind(this, bindingPayload);
    }

    @Override
    protected void unbind(@NonNull UiWrapperRepository uiWrapperRepository, @NonNull UnbindingPayload unbindingPayload) {
        uiWrapperRepository.unbind(this, unbindingPayload);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigator = null;
    }
}
