package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.action.BindingCommand;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.EnteredShBean;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.http.APiClient;
import com.bp.hmi.citytour.http.RxRetrofitClient;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EnteredShViewModel extends BaseViewModel {
    private static final String TAG = EnteredShViewModel.class.getSimpleName();
    public SingleLiveEvent<List<SubCardsTabTitleBean>> mSubCardsTabTitleBean;
    public SingleLiveEvent<EnteredShBean> mEnteredShBeanList;

    /**
     * Constructor.
     *
     * @param application
     */
    public EnteredShViewModel(@NonNull Application application) {
        super(application);
        initData();
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

    public BindingCommand videoFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.videoFragmentLivEvent.call());

    public BindingCommand meFragmentCommand = new BindingCommand(()
            -> uiChangeObservable.meFragmentLivEvent.call());

    private void initData() {
        mSubCardsTabTitleBean = new SingleLiveEvent<>();
        mEnteredShBeanList = new SingleLiveEvent<>();
    }

    public void getSubCardsTitle() {
        List<SubCardsTabTitleBean> subCardsList = new ArrayList<>();

        SubCardsTabTitleBean subCardsTitle1 = new SubCardsTabTitleBean();
        subCardsTitle1.setTitle("全部");
        subCardsList.add(subCardsTitle1);

        SubCardsTabTitleBean subCardsTitle2 = new SubCardsTabTitleBean();
        subCardsTitle2.setTitle("红色游");
        subCardsList.add(subCardsTitle2);

        SubCardsTabTitleBean subCardsTitle3 = new SubCardsTabTitleBean();
        subCardsTitle3.setTitle("最新");
        subCardsList.add(subCardsTitle3);

        SubCardsTabTitleBean subCardsTitle4 = new SubCardsTabTitleBean();
        subCardsTitle4.setTitle("热门");
        subCardsList.add(subCardsTitle4);

        SubCardsTabTitleBean subCardsTitle5 = new SubCardsTabTitleBean();
        subCardsTitle5.setTitle("购物");
        subCardsList.add(subCardsTitle5);

        SubCardsTabTitleBean subCardsTitle6 = new SubCardsTabTitleBean();
        subCardsTitle6.setTitle("一日游");
        subCardsList.add(subCardsTitle6);

        mSubCardsTabTitleBean.postValue(subCardsList);

    }

    public void getCardsList() {
        RxRetrofitClient.create(APiClient.class).getEnterShData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EnteredShBean>() {
                    @Override
                    public void onStart() {
                        Log.d(TAG, Thread.currentThread().getName() + "---onStart......");
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, Thread.currentThread().getName() + "---onCompleted......");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, Thread.currentThread().getName() + "---onError......" + e);
                    }

                    @Override
                    public void onNext(EnteredShBean weatherBean) {
                        Log.d(TAG, Thread.currentThread().getName() + "---onNext......" + weatherBean.toString());
                        //绑定数据
                        mEnteredShBeanList.postValue(weatherBean);
                    }
                });
    }
}
