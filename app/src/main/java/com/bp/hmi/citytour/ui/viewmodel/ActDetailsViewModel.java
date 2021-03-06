package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.ActDetailsBean;
import com.bp.hmi.citytour.http.APiClient;
import com.bp.hmi.citytour.http.ApiService;
import com.bp.hmi.citytour.http.RxRetrofitClient;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ActDetailsViewModel extends BaseViewModel {
    private static final String TAG = ActDetailsViewModel.class.getSimpleName();
    public SingleLiveEvent<ActDetailsBean> mActDetailsBean;


    /**
     * Constructor.
     *
     * @param application
     */
    public ActDetailsViewModel(@NonNull Application application) {
        super(application);
        initData();
    }

    public UiChangeObservable uiChangeObservable = new UiChangeObservable();

    public static class UiChangeObservable {
        public SingleLiveEvent homeFragmentLivEvent = new SingleLiveEvent<>();

    }

    private void initData() {
        mActDetailsBean = new SingleLiveEvent<>();
    }

    /**
     * 获取活动
     */
    public void requestActivityInfo(int id) {
        RxRetrofitClient.create(APiClient.class).getActivityDetailsData(ApiService.HOME_API_HOME_ACTIVITY_DETAILS + id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ActDetailsBean>() {
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
                    public void onNext(ActDetailsBean weatherBean) {
                        Log.d(TAG, Thread.currentThread().getName() + "---onNext......" + weatherBean.toString());
                        mActDetailsBean.postValue(weatherBean);
                    }
                });
    }

}
