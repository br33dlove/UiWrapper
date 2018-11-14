package com.davidcryer.uiwrapperlibraryexample.examplelistitem;

import android.os.Parcel;
import android.os.Parcelable;

import com.davidc.uiwrapper.UiModel;

public class ExampleListItemUiModel implements UiModel<ExampleListItemUiModel.SavedState> {
    private final String value;

    public ExampleListItemUiModel(String value) {
        this.value = value;
    }

    ExampleListItemUiModel(final SavedState savedState) {
        this.value = savedState.value;
    }

    @Override
    public SavedState getParcelable() {
        return new SavedState(value);
    }

    public static class SavedState implements Parcelable {
        private final String value;

        public SavedState(String value) {
            this.value = value;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.value);
        }

        protected SavedState(Parcel in) {
            this.value = in.readString();
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
