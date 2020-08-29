package com.bp.hmi.citytour.ui.repository;

import android.app.Application;
import android.util.Log;

import com.bp.hmi.citytour.base.BaseRepository;
import com.bp.hmi.citytour.bean.VideoBean;
import com.bp.hmi.citytour.bean.WeatherBean;
import com.bp.hmi.citytour.http.APiClient;
import com.bp.hmi.citytour.http.RxRetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <pre>
 *     author : BaiPengMac
 *     e-mail : xxx@xx
 *     time   : 2020/06/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class HomeRepository extends BaseRepository {
    private static final String TAG = HomeRepository.class.getSimpleName();
    private IVideoResultCallback mIVideoResultCallback;

    public HomeRepository(Application application) {
        mApplication = application;
    }

    public void addVideoResultCallBack(IVideoResultCallback callback) {
        if (null != callback) {
            mIVideoResultCallback = callback;
        }
    }

    public void getVoidData() {
        List<VideoBean> bean = new ArrayList<>();
        VideoBean v1 = new VideoBean();
        v1.setTitle("假日周边好去处");
        v1.setLike(true);
        v1.setFavorite(false);
        v1.setLikeSum(20);
        v1.setFavoriteSum(10);
        bean.add(v1);


        VideoBean v2 = new VideoBean();
        v2.setTitle("假日周边好去处");
        v2.setLike(true);
        v2.setFavorite(false);
        v2.setLikeSum(20);
        v2.setFavoriteSum(10);
        bean.add(v2);


        VideoBean v3 = new VideoBean();
        v3.setTitle("假日周边好去处");
        v3.setLike(true);
        v3.setFavorite(false);
        v3.setLikeSum(20);
        v3.setFavoriteSum(10);
        bean.add(v3);

        VideoBean v4 = new VideoBean();
        v4.setTitle("假日周边好去处");
        v4.setLike(false);
        v4.setFavorite(false);
        v4.setLikeSum(21);
        v4.setFavoriteSum(30);
        bean.add(v4);

        VideoBean v5 = new VideoBean();
        v5.setTitle("假日周边好去处");
        v5.setLike(true);
        v5.setFavorite(true);
        v5.setLikeSum(32);
        v5.setFavoriteSum(16);
        bean.add(v5);


        VideoBean v6 = new VideoBean();
        v6.setTitle("假日周边好去处");
        v6.setLike(false);
        v6.setFavorite(true);
        v6.setLikeSum(60);
        v6.setFavoriteSum(70);
        bean.add(v6);


        mIVideoResultCallback.videoResult(0, bean);
    }

    /**
     * subscribe写法
     */
    public void requestWeatherInfo() {

        //设置参数
        Map<String, String> params = new HashMap<>();
        params.put("version", "v6");
        params.put("appid", "35265473");
        params.put("appsecret", "2xiljnCe");


        RxRetrofitClient.create(APiClient.class).getWeatherInfo(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherBean>() {
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
                    public void onNext(WeatherBean weatherBean) {
                         Log.d(TAG, Thread.currentThread().getName() + "---onNext......" + weatherBean.toString());
                        //绑定数据
                        mIVideoResultCallback.weatherResult(0,weatherBean);
                    }
                });
    }

    public interface IVideoResultCallback {
        void videoResult(int code, List<VideoBean> result);

        void weatherResult(int code, WeatherBean result);
    }
}
