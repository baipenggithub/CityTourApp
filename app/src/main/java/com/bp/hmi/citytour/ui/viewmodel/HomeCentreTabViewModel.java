package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.ActivityTabBean;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.http.APiClient;
import com.bp.hmi.citytour.http.RxRetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeCentreTabViewModel extends BaseViewModel {
    private static final String TAG = HomeCentreTabViewModel.class.getSimpleName();
    public ObservableField<String> mTitle = new ObservableField<>();
    public SingleLiveEvent<ActivityTabBean> mActivityData;
    public SingleLiveEvent<List<SubCardsTabTitleBean>> mSubCardsTabTitleBean;

    /**
     * Constructor.
     *
     * @param application
     */
    public HomeCentreTabViewModel(@NonNull Application application) {
        super(application);
        initData();
    }


    protected void initData() {
        mSubCardsTabTitleBean = new SingleLiveEvent<>();
        mActivityData = new SingleLiveEvent<>();
    }

    public void getSubCardsTitle() {
        List<SubCardsTabTitleBean> subCardsList = new ArrayList<>();

        SubCardsTabTitleBean subCardsTitle1 = new SubCardsTabTitleBean();
        subCardsTitle1.setTitle("全部");
        subCardsList.add(subCardsTitle1);

        SubCardsTabTitleBean subCardsTitle2 = new SubCardsTabTitleBean();
        subCardsTitle2.setTitle("最新");
        subCardsList.add(subCardsTitle2);

        SubCardsTabTitleBean subCardsTitle3 = new SubCardsTabTitleBean();
        subCardsTitle3.setTitle("热度");
        subCardsList.add(subCardsTitle3);

        SubCardsTabTitleBean subCardsTitle4 = new SubCardsTabTitleBean();
        subCardsTitle4.setTitle("距离");
        subCardsList.add(subCardsTitle4);

        SubCardsTabTitleBean subCardsTitle5 = new SubCardsTabTitleBean();
        subCardsTitle5.setTitle("类型");
        subCardsList.add(subCardsTitle5);

        mSubCardsTabTitleBean.postValue(subCardsList);

    }


    /**
     * 获取活动
     */
    public void requestActivityInfo(String type) {
        Map<String, String> params = new HashMap<>();
        params.put("isNew", "0");
        params.put("type", type);
        RxRetrofitClient.create(APiClient.class).getActivityData(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ActivityTabBean>() {
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
                    public void onNext(ActivityTabBean weatherBean) {
                        Log.d(TAG, Thread.currentThread().getName() + "---onNext......" + weatherBean.toString());
                        mActivityData.postValue(weatherBean);
                    }
                });
    }

    /**
     * 获取购物
     */
    public void requestShopping() {
        Map<String, String> params = new HashMap<>();
        params.put("isNew", "");
        params.put("type", "3");
        RxRetrofitClient.create(APiClient.class).getActivityData(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ActivityTabBean>() {
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
                    public void onNext(ActivityTabBean weatherBean) {
                        Log.d(TAG, Thread.currentThread().getName() + "---onNext......" + weatherBean.toString());
                        mActivityData.postValue(weatherBean);
                    }
                });
    }
}
