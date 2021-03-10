package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.bp.hmi.citytour.action.BindingCommand;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;

public class WebViewModel extends BaseViewModel {
    private static final String TAG = WebViewModel.class.getSimpleName();
    public ObservableField<String> mWebTitle;

    /**
     * Constructor.
     *
     * @param application
     */
    public WebViewModel(@NonNull Application application) {
        super(application);
        initData();
    }

    protected void initData() {
        mWebTitle = new ObservableField<>("");
    }


    public BookViewModel.UiChangeObservable uiChangeObservable = new BookViewModel.UiChangeObservable();


    public static class UiChangeObservable {
        public SingleLiveEvent homeFragmentLivEvent = new SingleLiveEvent<>();

    }

    public BindingCommand homeFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.homeFragmentLivEvent.call());

}
