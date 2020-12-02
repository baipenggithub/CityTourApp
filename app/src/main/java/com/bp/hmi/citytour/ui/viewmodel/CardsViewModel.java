package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.CardsBean;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.http.APiClient;
import com.bp.hmi.citytour.http.RxRetrofitClient;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CardsViewModel extends BaseViewModel {
    private static final String TAG = CardsViewModel.class.getSimpleName();
    public SingleLiveEvent<List<SubCardsTabTitleBean>> mSubCardsTabTitleBean;
    public SingleLiveEvent<CardsBean> mCardsBeanList;

    /**
     * Constructor.
     *
     * @param application
     */
    public CardsViewModel(@NonNull Application application) {
        super(application);
        initData();
    }


    protected void initData() {
        mSubCardsTabTitleBean = new SingleLiveEvent<>();
        mCardsBeanList = new SingleLiveEvent<>();
    }


    public void getSubCardsTitle(int tyep) {
        List<SubCardsTabTitleBean> subCardsList = new ArrayList<>();

        SubCardsTabTitleBean subCardsTitle1 = new SubCardsTabTitleBean();
        if (tyep == 0) {
            subCardsTitle1.setTitle("全部");
        } else {
            subCardsTitle1.setTitle("全部 (16)");
        }

        subCardsList.add(subCardsTitle1);

        SubCardsTabTitleBean subCardsTitle2 = new SubCardsTabTitleBean();
        if (tyep == 0) {
            subCardsTitle2.setTitle("折扣券");
        } else {
            subCardsTitle2.setTitle("会员 (2)");
        }

        subCardsList.add(subCardsTitle2);

        SubCardsTabTitleBean subCardsTitle3 = new SubCardsTabTitleBean();
        if (tyep == 0) {
            subCardsTitle3.setTitle("礼品券");
        } else {
            subCardsTitle3.setTitle("景点 (6)");
        }

        subCardsList.add(subCardsTitle3);

        SubCardsTabTitleBean subCardsTitle4 = new SubCardsTabTitleBean();
        if (tyep == 0) {
            subCardsTitle4.setTitle("特价券");
        } else {
            subCardsTitle4.setTitle("购物 (1)");
        }

        subCardsList.add(subCardsTitle4);

        SubCardsTabTitleBean subCardsTitle5 = new SubCardsTabTitleBean();

        if (tyep == 0) {
            subCardsTitle5.setTitle("换购券");
        } else {
            subCardsTitle5.setTitle("展馆 (4)");
        }
        subCardsList.add(subCardsTitle5);

        mSubCardsTabTitleBean.postValue(subCardsList);

    }


    /**
     * 获取消费券
     */
    public void requestActivityInfo() {
        RxRetrofitClient.create(APiClient.class).getCardsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CardsBean>() {
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
                    public void onNext(CardsBean weatherBean) {
                        Log.d(TAG, Thread.currentThread().getName() + "---onNext......" + weatherBean.toString());
                        //绑定数据
                        mCardsBeanList.postValue(weatherBean);
                    }
                });
    }
}
