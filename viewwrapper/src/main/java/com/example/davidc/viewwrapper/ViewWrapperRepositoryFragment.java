package com.example.davidc.viewwrapper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class ViewWrapperRepositoryFragment<R extends ViewWrapperRepository> extends Fragment implements ViewWrapperRepositoryProvider {
    private R viewWrapperRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initRepository();
    }

    private void initRepository() {
        viewWrapperRepository = CastHelper.viewWrapperRepositoryFromFactory(getActivity());
    }

    @Override
    public R get() {
        return viewWrapperRepository;
    }
}
