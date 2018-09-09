package com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.example;

import android.os.Parcel;

import com.davidc.uiwrapper.UiModel;

class ExampleUiModel implements UiModel {
    private final long timeOfLastStateRecovery;
    private int resourceListenersCount;
    private int buttonClickCount;

    ExampleUiModel(long timeOfLastStateRecovery, int resourceListenersCount, int buttonClickCount) {
        this.timeOfLastStateRecovery = timeOfLastStateRecovery;
        this.resourceListenersCount = resourceListenersCount;
        this.buttonClickCount = buttonClickCount;
    }

    void showResourceListenersCount(ExampleUi ui, int resourceListenersCount) {
        this.resourceListenersCount = resourceListenersCount;
        if (ui != null) {
            ui.showResourceListenersCount(resourceListenersCount);
        }
    }

    void incrementButtonClickCounter(ExampleUi ui) {
        if (ui != null) {
            ui.showButtonClickCount(++buttonClickCount);
        }
    }

    public long getTimeOfLastStateRecovery() {
        return timeOfLastStateRecovery;
    }

    public int getResourceListenersCount() {
        return resourceListenersCount;
    }

    public int getButtonClickCount() {
        return buttonClickCount;
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

    private ExampleUiModel(Parcel in) {
        this.timeOfLastStateRecovery = in.readLong();
        this.resourceListenersCount = in.readInt();
        this.buttonClickCount = in.readInt();
    }

    public static final Creator<ExampleUiModel> CREATOR = new Creator<ExampleUiModel>() {
        @Override
        public ExampleUiModel createFromParcel(Parcel source) {
            return new ExampleUiModel(source);
        }

        @Override
        public ExampleUiModel[] newArray(int size) {
            return new ExampleUiModel[size];
        }
    };
}
