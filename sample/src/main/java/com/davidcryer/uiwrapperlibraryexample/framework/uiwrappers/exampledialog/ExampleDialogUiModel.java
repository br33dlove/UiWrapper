package com.davidcryer.uiwrapperlibraryexample.framework.uiwrappers.exampledialog;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiModel;

public class ExampleDialogUiModel implements UiModel<ExampleDialogUi> {

    ExampleDialogUiModel() {}

    @Override
    public void onto(@NonNull ExampleDialogUi ui) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    private ExampleDialogUiModel(Parcel in) {}

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
