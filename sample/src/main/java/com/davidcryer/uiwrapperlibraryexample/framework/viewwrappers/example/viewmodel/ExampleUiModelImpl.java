package com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.viewmodel;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.davidcryer.uiwrapperlibraryexample.framework.viewwrappers.example.ExampleUi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class ExampleUiModelImpl implements ExampleUiModel {
    private final static String FORMAT_BUTTON_CLICK_COUNTER = "Button click count: %1$s";
    private final static String FORMAT_RESOURCE_LISTENERS = "Number of resource listeners: %s";
    private final static String FORMAT_LAST_RECOVERY = "State last recovered on: %s";
    private final static String NO_LAST_RECOVERY = "State not yet recovered";
    private final static String SDF_LAST_RECOVERY = "EEE, d MMM yyyy HH:mm:ss";
    private final long timeOfLastStateRecovery;
    private int resourceListenersCount;
    private int buttonClickCount;

    ExampleUiModelImpl(long timeOfLastStateRecovery, int resourceListenersCount, int buttonClickCount) {
        this.timeOfLastStateRecovery = timeOfLastStateRecovery;
        this.resourceListenersCount = resourceListenersCount;
        this.buttonClickCount = buttonClickCount;
    }

    @Override
    public void onto(@NonNull ExampleUi ui) {
        ui.showResourceListenersCountText(resourceListenersCountText(resourceListenersCount));
        ui.showTimeOfLastStateRecoveryText(timeOfLastStateRecoveryText(timeOfLastStateRecovery));
        ui.showButtonClickCountText(String.format(FORMAT_BUTTON_CLICK_COUNTER, buttonClickCount));
    }

    private static String timeOfLastStateRecoveryText(final long timeOfLastStateRecovery) {
        if (timeOfLastStateRecovery >= 0) {
            return String.format(FORMAT_LAST_RECOVERY, new SimpleDateFormat(SDF_LAST_RECOVERY, Locale.getDefault()).format(new Date(timeOfLastStateRecovery)));
        } else {
            return NO_LAST_RECOVERY;
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
        return String.format(FORMAT_RESOURCE_LISTENERS, resourceListenersCount);
    }

    @Override
    public void incrementButtonClickCounter(ExampleUi ui) {
        if (ui != null) {
            ui.showButtonClickCountText(String.format(FORMAT_BUTTON_CLICK_COUNTER, ++buttonClickCount));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.timeOfLastStateRecovery);
        dest.writeInt(this.resourceListenersCount);
        dest.writeInt(this.buttonClickCount);
    }

    private ExampleUiModelImpl(Parcel in) {
        this.timeOfLastStateRecovery = in.readLong();
        this.resourceListenersCount = in.readInt();
        this.buttonClickCount = in.readInt();
    }

    public static final Creator<ExampleUiModelImpl> CREATOR = new Creator<ExampleUiModelImpl>() {
        @Override
        public ExampleUiModelImpl createFromParcel(Parcel source) {
            return new ExampleUiModelImpl(source);
        }

        @Override
        public ExampleUiModelImpl[] newArray(int size) {
            return new ExampleUiModelImpl[size];
        }
    };
}
