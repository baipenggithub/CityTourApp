package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.action.BindingCommand;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;

public class MainActivityViewModel extends BaseViewModel {
    /**
     * Constructor.
     *
     * @param application
     */
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    protected void initData() {

    }

    protected void createRepository() {

    }

    public UiChangeObservable uiChangeObservable = new UiChangeObservable();

    public static class UiChangeObservable {
        public SingleLiveEvent homeFragmentLivEvent = new SingleLiveEvent<>();
        public SingleLiveEvent travelFragmentLivEvent = new SingleLiveEvent<>();
        public SingleLiveEvent assistantFragmentLivEvent = new SingleLiveEvent<>();
        public SingleLiveEvent videoFragmentLivEvent = new SingleLiveEvent<>();
        public SingleLiveEvent meFragmentLivEvent = new SingleLiveEvent<>();
    }

    public BindingCommand homeFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.homeFragmentLivEvent.call());

    public BindingCommand travelFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.travelFragmentLivEvent.call());

    public BindingCommand assistantFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.assistantFragmentLivEvent.call());

    public BindingCommand videoFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.videoFragmentLivEvent.call());

    public BindingCommand meFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.meFragmentLivEvent.call());

}
