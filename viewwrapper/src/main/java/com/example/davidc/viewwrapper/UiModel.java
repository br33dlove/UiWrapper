package com.example.davidc.viewwrapper;

import android.os.Parcelable;

public interface UiModel<U extends Ui> extends Parcelable {
    void onto(U ui);
}
