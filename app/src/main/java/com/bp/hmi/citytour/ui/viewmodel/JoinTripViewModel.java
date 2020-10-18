package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.EnterShDetailsBean;

public class JoinTripViewModel extends BaseViewModel {
    private static final String TAG = JoinTripViewModel.class.getSimpleName();
    public SingleLiveEvent<EnterShDetailsBean> mActivityData;

    /**
     * Constructor.
     *
     * @param application
     */
    public JoinTripViewModel(@NonNull Application application) {
        super(application);
        initData();
    }

    public UiChangeObservable uiChangeObservable = new UiChangeObservable();

    public static class UiChangeObservable {
        public SingleLiveEvent homeFragmentLivEvent = new SingleLiveEvent<>();

    }


    protected void initData() {
        mActivityData=new SingleLiveEvent<>();

    }
}
