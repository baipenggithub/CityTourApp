package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.bp.hmi.citytour.action.BindingCommand;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;

public class MapViewModel extends BaseViewModel {
    private static final String TAG = MapViewModel.class.getSimpleName();
    public ObservableField<String> mMapTitle;

    /**
     * Constructor.
     *
     * @param application
     */
    public MapViewModel(@NonNull Application application) {
        super(application);
        initData();
    }

    protected void initData() {
        mMapTitle = new ObservableField<>("云展览");
    }


    public BookViewModel.UiChangeObservable uiChangeObservable = new BookViewModel.UiChangeObservable();


    public static class UiChangeObservable {
        public SingleLiveEvent homeFragmentLivEvent = new SingleLiveEvent<>();

    }

    public BindingCommand homeFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.homeFragmentLivEvent.call());

}
