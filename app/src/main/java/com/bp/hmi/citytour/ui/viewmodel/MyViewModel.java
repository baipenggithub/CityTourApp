package com.bp.hmi.citytour.ui.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.bp.hmi.citytour.action.BindingCommand;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.ScheduleEntity;

import java.util.List;

public class MyViewModel extends BaseViewModel {
    private static final String TAG = PavilionViewModel.class.getSimpleName();


    /**
     * Constructor.
     *
     * @param application
     */
    public MyViewModel(@NonNull Application application) {
        super(application);
        initData();
    }

    protected void initData() {

    }


    public BookViewModel.UiChangeObservable uiChangeObservable = new BookViewModel.UiChangeObservable();


}
