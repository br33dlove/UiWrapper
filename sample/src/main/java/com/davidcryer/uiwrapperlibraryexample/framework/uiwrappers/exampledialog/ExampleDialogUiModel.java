package com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.exampledialog;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiModel;
import com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example.ExampleUi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExampleDialogUiModel implements UiModel<ExampleDialogUi> {
    private final static String FORMAT_RESOURCE_LISTENERS = "Number of resource listeners: %s";
    private final static String FORMAT_LAST_RECOVERY = "State last recovered on: %s";
    private final static String NO_LAST_RECOVERY = "State not yet recovered";
    private final static String SDF_LAST_RECOVERY = "EEE, d MMM yyyy HH:mm:ss";
    private final long timeOfLastStateRecovery;
    private int resourceListenersCount;

    ExampleDialogUiModel(long timeOfLastStateRecovery, int resourceListenersCount) {
        this.timeOfLastStateRecovery = timeOfLastStateRecovery;
        this.resourceListenersCount = resourceListenersCount;
    }

    @Override
    public void onto(@NonNull ExampleDialogUi ui) {
        ui.showResourceListenersCountText(resourceListenersCountText(resourceListenersCount));
        ui.showTimeOfLastStateRecoveryText(timeOfLastStateRecoveryText(timeOfLastStateRecovery));
    }

    private static String timeOfLastStateRecoveryText(final long timeOfLastStateRecovery) {
        if (timeOfLastStateRecovery >= 0) {
            return String.format(FORMAT_LAST_RECOVERY, new SimpleDateFormat(SDF_LAST_RECOVERY, Locale.getDefault()).format(new Date(timeOfLastStateRecovery)));
        } else {
            return NO_LAST_RECOVERY;
        }
    }

    void showResourceListenersCount(ExampleDialogUi ui, int resourceListenersCount) {
        this.resourceListenersCount = resourceListenersCount;
        if (ui != null) {
            ui.showResourceListenersCountText(resourceListenersCountText(resourceListenersCount));
        }
    }

    private static String resourceListenersCountText(final int resourceListenersCount) {
        return String.format(FORMAT_RESOURCE_LISTENERS, resourceListenersCount);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.timeOfLastStateRecovery);
        dest.writeInt(this.resourceListenersCount);
    }

    private ExampleDialogUiModel(Parcel in) {
        this.timeOfLastStateRecovery = in.readLong();
        this.resourceListenersCount = in.readInt();
    }

    public static final Creator<ExampleDialogUiModel> CREATOR = new Creator<ExampleDialogUiModel>() {
        @Override
        public ExampleDialogUiModel createFromParcel(Parcel source) {
            return new ExampleDialogUiModel(source);
        }

        @Override
        public ExampleDialogUiModel[] newArray(int size) {
            return new ExampleDialogUiModel[size];
        }
    };
}
