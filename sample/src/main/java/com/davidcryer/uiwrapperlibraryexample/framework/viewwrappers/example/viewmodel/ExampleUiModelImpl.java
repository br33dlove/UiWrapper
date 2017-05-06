package com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.viewmodel;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.ExampleUi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class ExampleUiModelImpl implements ExampleUiModel {
    private final long timeOfLastStateRecovery;
    private int resourceListenersCount;

    ExampleUiModelImpl(final long timeOfLastStateRecovery, final int resourceListenersCount) {
        this.timeOfLastStateRecovery = timeOfLastStateRecovery;
        this.resourceListenersCount = resourceListenersCount;
    }

    private ExampleUiModelImpl(final Parcel parcel) {
        timeOfLastStateRecovery = System.currentTimeMillis();
        resourceListenersCount = parcel.readInt();
    }

    @Override
    public void onto(@NonNull ExampleUi ui) {
        ui.showResourceListenersCountText(resourceListenersCountText(resourceListenersCount));
        ui.showTimeOfLastStateRecoveryText(timeOfLastStateRecoveryText(timeOfLastStateRecovery));
    }

    private static String timeOfLastStateRecoveryText(final long timeOfLastStateRecovery) {
        if (timeOfLastStateRecovery >= 0) {
            return String.format(
                    "State last recovered on: %s",
                    new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.getDefault()).format(new Date(timeOfLastStateRecovery))
            );
        } else {
            return "State not yet recovered";
        }
    }

    @Override
    public void showResourceListenersCount(ExampleUi ui, int resourceListenersCount) {
        this.resourceListenersCount = resourceListenersCount;
        if (ui != null) {
            ui.showResourceListenersCountText(resourceListenersCountText(resourceListenersCount));
        }
    }

    private static String resourceListenersCountText(final int resourceListenersCount) {
        return String.format("Number of resource listeners: %s", resourceListenersCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(resourceListenersCount);
    }

    final static Creator<ExampleUiModel> CREATOR = new Creator<ExampleUiModel>() {
        @Override
        public ExampleUiModel createFromParcel(Parcel source) {
            return new ExampleUiModelImpl(source);
        }

        @Override
        public ExampleUiModel[] newArray(int size) {
            return new ExampleUiModel[size];
        }
    };
}
