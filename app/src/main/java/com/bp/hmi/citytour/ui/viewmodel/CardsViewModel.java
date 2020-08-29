package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.CardsBean;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.ui.repository.CardsRepository;

import java.util.List;

public class CardsViewModel extends BaseViewModel<CardsRepository> implements CardsRepository.ICardsResultCallback {
    private static final String TAG = CardsViewModel.class.getSimpleName();
    public SingleLiveEvent<List<SubCardsTabTitleBean>> mSubCardsTabTitleBean;
    public SingleLiveEvent<List<CardsBean>> mCardsBeanList;

    /**
     * Constructor.
     *
     * @param application
     */
    public CardsViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void initData() {
        mSubCardsTabTitleBean = new SingleLiveEvent<>();
        mCardsBeanList = new SingleLiveEvent<>();
    }

    @Override
    protected void createRepository() {
        mRepository = new CardsRepository(getApplication());
        mRepository.addCardsResultCallBack(this);

        mRepository.getSubCardsTitle();
        mRepository.getCardsList();
    }

    @Override
    public void subCardsResult(int code, List<SubCardsTabTitleBean> result) {
        mSubCardsTabTitleBean.postValue(result);
    }

    @Override
    public void cardsResult(int code, List<CardsBean> result) {
        mCardsBeanList.postValue(result);
    }
}
