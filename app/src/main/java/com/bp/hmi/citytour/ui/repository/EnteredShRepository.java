package com.bp.hmi.citytour.ui.repository;

import android.app.Application;

import com.bp.hmi.citytour.base.BaseRepository;
import com.bp.hmi.citytour.bean.CardsBean;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : BaiPengMac
 *     e-mail : xxx@xx
 *     time   : 2020/06/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class EnteredShRepository extends BaseRepository {
    private static final String TAG = EnteredShRepository.class.getSimpleName();
    private IEnteredShResultCallback mIEnteredShResultCallback;

    public EnteredShRepository(Application application) {
        mApplication = application;
    }

    public void addEnteredShResultCallBack(IEnteredShResultCallback callback) {
        if (null != callback) {
            mIEnteredShResultCallback = callback;
        }
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

        mIEnteredShResultCallback.subEnteredShResult(0, subCardsList);

    }

    public void getCardsList() {
        List<CardsBean> bean = new ArrayList<>();
        CardsBean v1 = new CardsBean();
        v1.setTitle("百盛购物中心全场9折券");
        v1.setTime("4小时");
        v1.setType(3);
        bean.add(v1);


        CardsBean v2 = new CardsBean();
        v2.setTitle("百盛购物中心全场9折券");
        v2.setTime("8小时");
        v2.setType(5);
        bean.add(v2);


        CardsBean v3 = new CardsBean();
        v3.setTitle("百盛购物中心全场9折券");
        v3.setTime("1小时");
        v3.setType(8);
        bean.add(v3);

        CardsBean v4 = new CardsBean();
        v4.setTitle("百盛购物中心全场9折券");
        v4.setTime("1.5小时");
        v4.setType(6);
        bean.add(v4);

        CardsBean v5 = new CardsBean();
        v5.setTitle("百盛购物中心全场9折券");
        v5.setTime("2小时");
        v5.setType(4);
        bean.add(v5);


        CardsBean v6 = new CardsBean();
        v6.setTitle("百盛购物中心全场9折券");
        v6.setTime("1小时");
        v6.setType(20);
        bean.add(v6);

        mIEnteredShResultCallback.enteredShResult(0, bean);
    }

    public interface IEnteredShResultCallback {
        void subEnteredShResult(int code, List<SubCardsTabTitleBean> result);

        void enteredShResult(int code, List<CardsBean> result);
    }
}
