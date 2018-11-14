package com.davidcryer.uiwrapperlibraryexample.examplelist;

import android.os.Parcel;
import android.os.Parcelable;

import com.davidc.uiwrapper.UiModel;
import com.davidcryer.uiwrapperlibraryexample.common.Collections;
import com.davidcryer.uiwrapperlibraryexample.examplelistitem.ExampleListItemUiModel;
import com.davidcryer.uiwrapperlibraryexample.examplelistitem.ExampleListItemUiWrapper;

import java.util.List;

public class ExampleListUiModel implements UiModel<ExampleListUiModel.SavedState> {
    private List<ExampleListItemUiWrapper> items;

    public ExampleListUiModel(List<ExampleListItemUiWrapper> items) {
        this.items = items;
    }

    @Override
    public SavedState getParcelable() {
        return new SavedState(Collections.mapByLoop(items, i -> i.get));
    }

    static class SavedState implements Parcelable {
        private final List<ExampleListItemUiModel.SavedState> states;

        public SavedState(List<ExampleListItemUiModel.SavedState> states) {
            this.states = states;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(this.states);
        }

        private SavedState(Parcel in) {
            this.states = in.createTypedArrayList(ExampleListItemUiModel.SavedState.CREATOR);
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
