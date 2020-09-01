package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.action.BindingCommand;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.CardsBean;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.ui.repository.EnteredShRepository;

import java.util.List;

public class EnteredShViewModel extends BaseViewModel<EnteredShRepository> implements EnteredShRepository.IEnteredShResultCallback {
    private static final String TAG = EnteredShViewModel.class.getSimpleName();
    public SingleLiveEvent<List<SubCardsTabTitleBean>> mSubCardsTabTitleBean;
    public SingleLiveEvent<List<CardsBean>> mCardsBeanList;

    /**
     * Constructor.
     *
     * @param application
     */
    public EnteredShViewModel(@NonNull Application application) {
        super(application);
    }

    public MainActivityViewModel.UiChangeObservable uiChangeObservable = new MainActivityViewModel.UiChangeObservable();

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

    public BindingCommand videoFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.videoFragmentLivEvent.call());

    public BindingCommand meFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.meFragmentLivEvent.call());

    @Override
    protected void initData() {
        mSubCardsTabTitleBean = new SingleLiveEvent<>();
        mCardsBeanList = new SingleLiveEvent<>();
    }

    @Override
    protected void createRepository() {
        mRepository = new EnteredShRepository(getApplication());
        mRepository.addEnteredShResultCallBack(this);

        mRepository.getSubCardsTitle();
        mRepository.getCardsList();
    }

    @Override
    public void subEnteredShResult(int code, List<SubCardsTabTitleBean> result) {
        mSubCardsTabTitleBean.postValue(result);
    }

    @Override
    public void enteredShResult(int code, List<CardsBean> result) {
        mCardsBeanList.postValue(result);
    }
}
