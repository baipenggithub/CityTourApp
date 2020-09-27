package com.bp.hmi.citytour.ui.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.bp.hmi.citytour.action.BindingCommand;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.ScheduleEntity;

import java.util.List;

public class VoiceViewModel extends BaseViewModel {
    private static final String TAG = PavilionViewModel.class.getSimpleName();
    public SingleLiveEvent<List<ScheduleEntity>> mBookData;
    public ObservableField<String> mBookSum;

    /**
     * Constructor.
     *
     * @param application
     */
    public VoiceViewModel(@NonNull Application application) {
        super(application);
        initData();
    }

    protected void initData() {
        mBookData = new SingleLiveEvent<>();
        mBookSum = new ObservableField<>("1");
    }


    public BookViewModel.UiChangeObservable uiChangeObservable = new BookViewModel.UiChangeObservable();


    public static class UiChangeObservable {
        public SingleLiveEvent homeFragmentLivEvent = new SingleLiveEvent<>();

    }

    public BindingCommand homeFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.homeFragmentLivEvent.call());

}
