package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.CardsBean;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;

import java.util.ArrayList;
import java.util.List;

public class CardsViewModel extends BaseViewModel {
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
        getSubCardsTitle();
        getCardsList();
    }

    public void getSubCardsTitle() {
        List<SubCardsTabTitleBean> subCardsList = new ArrayList<>();

        SubCardsTabTitleBean subCardsTitle1 = new SubCardsTabTitleBean();
        subCardsTitle1.setTitle("全部");
        subCardsList.add(subCardsTitle1);

        SubCardsTabTitleBean subCardsTitle2 = new SubCardsTabTitleBean();
        subCardsTitle2.setTitle("折扣券");
        subCardsList.add(subCardsTitle2);

        SubCardsTabTitleBean subCardsTitle3 = new SubCardsTabTitleBean();
        subCardsTitle3.setTitle("礼品券");
        subCardsList.add(subCardsTitle3);

        SubCardsTabTitleBean subCardsTitle4 = new SubCardsTabTitleBean();
        subCardsTitle4.setTitle("特价券");
        subCardsList.add(subCardsTitle4);

        SubCardsTabTitleBean subCardsTitle5 = new SubCardsTabTitleBean();
        subCardsTitle5.setTitle("换购券");
        subCardsList.add(subCardsTitle5);

        mSubCardsTabTitleBean.postValue(subCardsList);

    }

    public void getCardsList() {
        List<CardsBean> bean = new ArrayList<>();
        CardsBean v1 = new CardsBean();
        v1.setTitle("百盛购物中心全场9折券");
        v1.setTime("有效时间至2020年12月20日");
        v1.setType(1);
        bean.add(v1);


        CardsBean v2 = new CardsBean();
        v2.setTitle("百盛购物中心全场9折券");
        v2.setTime("有效时间至2020年12月20日");
        v2.setType(1);
        bean.add(v2);


        CardsBean v3 = new CardsBean();
        v3.setTitle("百盛购物中心全场9折券");
        v3.setTime("有效时间至2020年12月20日");
        v3.setType(1);
        bean.add(v3);

        CardsBean v4 = new CardsBean();
        v4.setTitle("百盛购物中心全场9折券");
        v4.setTime("有效时间至2020年12月20日");
        v4.setType(1);
        bean.add(v4);

        CardsBean v5 = new CardsBean();
        v5.setTitle("百盛购物中心全场9折券");
        v5.setTime("有效时间至2020年12月20日");
        v5.setType(1);
        bean.add(v5);


        CardsBean v6 = new CardsBean();
        v6.setTitle("百盛购物中心全场9折券");
        v6.setTime("有效时间至2020年12月20日");
        v6.setType(1);
        bean.add(v6);

        mCardsBeanList.postValue(bean);
    }
}
