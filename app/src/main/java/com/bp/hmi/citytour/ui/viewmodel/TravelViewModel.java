package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.HomeVideoBean;

import java.util.ArrayList;
import java.util.List;

public class TravelViewModel extends BaseViewModel {
    private static final String TAG = TravelViewModel.class.getSimpleName();
    public SingleLiveEvent<List<HomeVideoBean.ResultBean.ItemsBean>> mVideoData;


    /**
     * Constructor.
     *
     * @param application
     */
    public TravelViewModel(@NonNull Application application) {
        super(application);
        initData();
    }

    public UiChangeObservable uiChangeObservable = new UiChangeObservable();

    public static class UiChangeObservable {

    }


    private void initData() {
        mVideoData = new SingleLiveEvent<>();

    }

    public void getVoidData() {
        List<HomeVideoBean.ResultBean.ItemsBean> bean = new ArrayList<>();
        HomeVideoBean.ResultBean.ItemsBean v3 = new HomeVideoBean.ResultBean.ItemsBean();
        v3.setName("假日周边好去处");
        v3.setId(R.mipmap.item_1);
        v3.setLike(true);
        v3.setFavorite(false);
        v3.setLikeSum(20);
        v3.setFavoriteSum(10);
        bean.add(v3);

        HomeVideoBean.ResultBean.ItemsBean v4 = new HomeVideoBean.ResultBean.ItemsBean();
        v4.setId(R.mipmap.item_2);
        v4.setName("一帘幽梦|海香林..");
        v4.setLike(false);
        v4.setFavorite(false);
        v4.setLikeSum(21);
        v4.setFavoriteSum(30);
        bean.add(v4);

        HomeVideoBean.ResultBean.ItemsBean v5 = new HomeVideoBean.ResultBean.ItemsBean();
        v5.setId(R.mipmap.item_3);
        v5.setName("大美无言,盛没无疆");
        v5.setLike(true);
        v5.setFavorite(true);
        v5.setLikeSum(32);
        v5.setFavoriteSum(16);
        bean.add(v5);


        HomeVideoBean.ResultBean.ItemsBean v6 = new HomeVideoBean.ResultBean.ItemsBean();
        v6.setId(R.mipmap.item_1);
        v6.setName("周末画展合集");
        v6.setLike(false);
        v6.setFavorite(true);
        v6.setLikeSum(60);
        v6.setFavoriteSum(70);
        bean.add(v6);


        HomeVideoBean.ResultBean.ItemsBean v7 = new HomeVideoBean.ResultBean.ItemsBean();
        v7.setId(R.mipmap.item_2);
        v7.setName("上海夜景十大看点");
        v7.setLike(false);
        v7.setFavorite(true);
        v7.setLikeSum(60);
        v7.setFavoriteSum(70);
        bean.add(v7);

        HomeVideoBean.ResultBean.ItemsBean v8 = new HomeVideoBean.ResultBean.ItemsBean();
        v8.setId(R.mipmap.item_3);
        v8.setName("春日最美公园介绍");
        v8.setLike(false);
        v8.setFavorite(true);
        v8.setLikeSum(60);
        v8.setFavoriteSum(70);
        bean.add(v8);

        mVideoData.postValue(bean);
    }
}
