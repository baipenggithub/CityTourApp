package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;

import java.util.ArrayList;
import java.util.List;

public class VideoViewModel extends BaseViewModel {
    public SingleLiveEvent<List<SubCardsTabTitleBean>> mSubCardsTabTitleBean;

    /**
     * Constructor.
     *
     * @param application
     */
    public VideoViewModel(@NonNull Application application) {
        super(application);
        initData();
        createRepository();
    }

    protected void initData() {
        mSubCardsTabTitleBean = new SingleLiveEvent<>();

    }

    protected void createRepository() {
        getSubCardsTitle();

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
        subCardsTitle3.setTitle("热门");
        subCardsList.add(subCardsTitle3);

        SubCardsTabTitleBean subCardsTitle4 = new SubCardsTabTitleBean();
        subCardsTitle4.setTitle("发现");
        subCardsList.add(subCardsTitle4);

        SubCardsTabTitleBean subCardsTitle5 = new SubCardsTabTitleBean();
        subCardsTitle5.setTitle("直播");
        subCardsList.add(subCardsTitle5);
        mSubCardsTabTitleBean.postValue(subCardsList);

    }
}
