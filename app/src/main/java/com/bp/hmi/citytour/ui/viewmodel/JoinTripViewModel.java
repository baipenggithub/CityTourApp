package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;

public class JoinTripViewModel extends BaseViewModel {
    private static final String TAG = JoinTripViewModel.class.getSimpleName();

    /**
     * Constructor.
     *
     * @param application
     */
    public JoinTripViewModel(@NonNull Application application) {
        super(application);
    }

    public UiChangeObservable uiChangeObservable = new UiChangeObservable();

    public static class UiChangeObservable {
        public SingleLiveEvent homeFragmentLivEvent = new SingleLiveEvent<>();

    }


    @Override
    protected void initData() {

    }

    @Override
    protected void createRepository() {

    }
}
