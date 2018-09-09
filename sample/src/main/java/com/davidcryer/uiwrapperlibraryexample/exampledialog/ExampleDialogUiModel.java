package com.davidcryer.uiwrapperlibraryexample.exampledialog;

import android.os.Parcel;

import com.davidc.uiwrapper.UiModel;

public class ExampleDialogUiModel implements UiModel {
    private final long timeOfLastStateRecovery;
    private int resourceListenersCount;

    ExampleDialogUiModel(long timeOfLastStateRecovery, int resourceListenersCount) {
        this.timeOfLastStateRecovery = timeOfLastStateRecovery;
        this.resourceListenersCount = resourceListenersCount;
    }

    void showResourceListenersCount(ExampleDialogUi ui, int resourceListenersCount) {
        this.resourceListenersCount = resourceListenersCount;
        if (ui != null) {
            ui.showResourceListenersCount(resourceListenersCount);
        }
    }

    public long getTimeOfLastStateRecovery() {
        return timeOfLastStateRecovery;
    }

    public int getResourceListenersCount() {
        return resourceListenersCount;
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
