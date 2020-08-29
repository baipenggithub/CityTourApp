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
public class CardsRepository extends BaseRepository {
    private static final String TAG = CardsRepository.class.getSimpleName();
    private ICardsResultCallback mICardsResultCallback;

    public CardsRepository(Application application) {
        mApplication = application;
    }

    public void addCardsResultCallBack(ICardsResultCallback callback) {
        if (null != callback) {
            mICardsResultCallback = callback;
        }
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

        mICardsResultCallback.subCardsResult(0, subCardsList);

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

        mICardsResultCallback.cardsResult(0, bean);
    }

    public interface ICardsResultCallback {
        void subCardsResult(int code, List<SubCardsTabTitleBean> result);

        void cardsResult(int code, List<CardsBean> result);
    }
}
