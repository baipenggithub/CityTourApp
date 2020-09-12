package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.action.BindingCommand;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.CardsBean;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;

import java.util.ArrayList;
import java.util.List;

public class EnteredShViewModel extends BaseViewModel {
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
        getSubCardsTitle();
        getCardsList();
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
        List<CardsBean> bean = new ArrayList<>();
        CardsBean v1 = new CardsBean();
        v1.setTitle("中共一大会址纪念馆");
        v1.setTime("4小时");
        v1.setType(5);
        v1.setImage(R.mipmap.pic_1_03);
        bean.add(v1);


        CardsBean v2 = new CardsBean();
        v2.setTitle("孙中山故居纪念馆");
        v2.setTime("8小时");
        v1.setImage(R.mipmap.pic_1_06);
        v2.setType(5);
        bean.add(v2);


        CardsBean v3 = new CardsBean();
        v3.setTitle("上海市龙华烈士陵园");
        v3.setTime("1小时");
        v1.setImage(R.mipmap.pic_1_06);
        v3.setType(5);
        bean.add(v3);

        CardsBean v4 = new CardsBean();
        v4.setTitle("毛泽东旧居");
        v4.setTime("1.5小时");
        v4.setType(5);
        bean.add(v4);

        CardsBean v5 = new CardsBean();
        v5.setTitle("上海市长宁区宋园路21号");
        v5.setTime("2小时");
        v5.setType(5);
        bean.add(v5);


        CardsBean v6 = new CardsBean();
        v6.setTitle("百盛购物中心全场9折券");
        v6.setTime("1小时");
        v6.setType(5);
        bean.add(v6);

        mCardsBeanList.postValue(bean);
    }
}
