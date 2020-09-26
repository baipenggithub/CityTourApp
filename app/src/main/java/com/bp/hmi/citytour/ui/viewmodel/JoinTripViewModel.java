package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.ActivityTabBean;
import com.bp.hmi.citytour.http.APiClient;
import com.bp.hmi.citytour.http.RxRetrofitClient;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class JoinTripViewModel extends BaseViewModel {
    private static final String TAG = JoinTripViewModel.class.getSimpleName();
    public SingleLiveEvent<ActivityTabBean> mActivityData;

    /**
     * Constructor.
     *
     * @param application
     */
    public JoinTripViewModel(@NonNull Application application) {
        super(application);
        initData();
    }

    public UiChangeObservable uiChangeObservable = new UiChangeObservable();

    public static class UiChangeObservable {
        public SingleLiveEvent homeFragmentLivEvent = new SingleLiveEvent<>();

    }


    protected void initData() {
        mActivityData=new SingleLiveEvent<>();

    }

    /**
     * 获取活动
     */
    public void requestActivityInfo() {
        RxRetrofitClient.create(APiClient.class).getActivityData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ActivityTabBean>() {
                    @Override
                    public void onStart() {
                        //showDialog();
                        Log.d(TAG, Thread.currentThread().getName() + "---onStart......");
                    }

                    @Override
                    public void onCompleted() {
                        // dismissDialog();
                        Log.d(TAG, Thread.currentThread().getName() + "---onCompleted......");
                    }

                    @Override
                    public void onError(Throwable e) {
                        //dismissDialog();
                        Log.d(TAG, Thread.currentThread().getName() + "---onError......" + e);
                    }

                    @Override
                    public void onNext(ActivityTabBean weatherBean) {
                        Log.d(TAG, Thread.currentThread().getName() + "---onNext......" + weatherBean.toString());
                        //绑定数据
                        mActivityData.postValue(weatherBean);
                    }
                });
    }
}
