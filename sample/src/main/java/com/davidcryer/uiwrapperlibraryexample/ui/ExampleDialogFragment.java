package com.davidcryer.uiwrapperlibraryexample.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.davidc.uiwrapper.UiWrapper;
import com.davidc.uiwrapper.UiWrapperFactoryDialogFragment;
import com.davidcryer.uiwrapperlibraryexample.R;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.UiWrapperFactory;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.exampledialog.ExampleDialogUi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExampleDialogFragment extends UiWrapperFactoryDialogFragment<ExampleDialogUi, ExampleDialogUi.Listener, UiWrapperFactory> {
    private final static String FORMAT_RESOURCE_LISTENERS = "Number of resource listeners: %s";
    private final static String FORMAT_LAST_RECOVERY = "State last recovered on: %s";
    private final static String NO_LAST_RECOVERY = "State not yet recovered";
    private final static String SDF_LAST_RECOVERY = "EEE, d MMM yyyy HH:mm:ss";
    private ResourceInfoView resourceInfoView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getContext();
        if (context == null) {
            throw new IllegalStateException("Something has gone very wrong");
        }
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(R.layout.fragment_dialog_example_info)
                .setNeutralButton("Dismiss", null)
                .show();
        resourceInfoView = dialog.findViewById(R.id.info);
        return dialog;
    }

    @NonNull
    @Override
    public UiWrapper<ExampleDialogUi, ExampleDialogUi.Listener, ?> uiWrapper(@NonNull UiWrapperFactory uiWrapperFactory, @Nullable Bundle savedState) {
        return uiWrapperFactory.createExampleDialogUiWrapper(savedState);
    }

    @NonNull
    @Override
    public ExampleDialogUi ui() {
        return ui;
    }

    private final ExampleDialogUi ui = new ExampleDialogUi() {
        @Override
        public void showTimeOfLastStateRecovery(long time) {
            resourceInfoView.setTimeOfLastStateRecoveryText(timeOfLastStateRecoveryText(time));
        }

        @Override
        public void showResourceListenersCount(int count) {
            resourceInfoView.setResourceListenersCountText(resourceListenersCountText(count));
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
