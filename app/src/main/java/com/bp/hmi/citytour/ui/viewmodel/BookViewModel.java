package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.action.BindingCommand;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;

public class BookViewModel extends BaseViewModel{
    private static final String TAG = BookViewModel.class.getSimpleName();
    /**
     * Constructor.
     *
     * @param application
     */
    public BookViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void createRepository() {

    }

    public UiChangeObservable uiChangeObservable = new UiChangeObservable();

    @Override
    public void accept(Object o) throws Exception {

    }

    public static class UiChangeObservable {
        public SingleLiveEvent homeFragmentLivEvent = new SingleLiveEvent<>();

    }


    public BindingCommand homeFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.homeFragmentLivEvent.call());
}
