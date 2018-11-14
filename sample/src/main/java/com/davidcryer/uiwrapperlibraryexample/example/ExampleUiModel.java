package com.davidcryer.uiwrapperlibraryexample.example;

import android.os.Parcel;
import android.os.Parcelable;

import com.davidc.uiwrapper.UiModel;

class ExampleUiModel implements UiModel<ExampleUiModel.SavedState> {
    private final long timeOfLastStateRecovery;
    private int buttonClickCount;
    private int resourceListenersCount;

    ExampleUiModel(long timeOfLastStateRecovery, int resourceListenersCount, int buttonClickCount) {
        this.timeOfLastStateRecovery = timeOfLastStateRecovery;
        this.resourceListenersCount = resourceListenersCount;
        this.buttonClickCount = buttonClickCount;
    }

    ExampleUiModel(final ExampleUiModel.SavedState savedState) {
        this(savedState.timeOfLastStateRecovery, savedState.resourceListenersCount, savedState.buttonClickCount);
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
    public SavedState getParcelable() {
        return new SavedState(timeOfLastStateRecovery, resourceListenersCount, buttonClickCount);
    }

    static class SavedState implements Parcelable {
        private final long timeOfLastStateRecovery;
        private final int resourceListenersCount;
        private final int buttonClickCount;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(timeOfLastStateRecovery);
            dest.writeInt(resourceListenersCount);
            dest.writeInt(buttonClickCount);
        }

        private SavedState(long timeOfLastStateRecovery, int resourceListenersCount, int buttonClickCount) {
            this.timeOfLastStateRecovery = timeOfLastStateRecovery;
            this.resourceListenersCount = resourceListenersCount;
            this.buttonClickCount = buttonClickCount;
        }

        private SavedState(Parcel in) {
            timeOfLastStateRecovery = in.readLong();
            resourceListenersCount = in.readInt();
            buttonClickCount = in.readInt();
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel source) {
                return new SavedState(source);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
