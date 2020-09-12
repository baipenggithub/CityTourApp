package com.bp.hmi.citytour.base;

import android.app.Application;

import com.bp.hmi.citytour.BuildConfig;
import com.bp.hmi.citytour.http.ApiService;
import com.bp.hmi.citytour.http.RxRetrofitClient;
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.player.SystemPlayerManager;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;

/**
 * BaseApplication.
 */
public class BaseApplication extends Application {
    private static BaseApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);
        initOkHttp();
        ZXingLibrary.initDisplayOpinion(this);


        //EXOPlayer内核，支持格式更多
        PlayerFactory.setPlayManager(Exo2PlayerManager.class);
        //系统内核模式
        PlayerFactory.setPlayManager(SystemPlayerManager.class);
        //ijk内核，默认模式
        PlayerFactory.setPlayManager(IjkPlayerManager.class);
    }

    public static void setApplication(BaseApplication application) {
        sInstance = application;
    }

    public static BaseApplication getApplication() {
        return sInstance;
    }

    private void initOkHttp() {
        //默认配置(15秒超时,HttpLoggingInterceptor,ScalarsConverterFactory,GsonConverterFactory,RxJavaCallAdapterFactory)
        //传入接口BaseUrl,是否输出log
        RxRetrofitClient.init(ApiService.HOME_WEATHER_API, BuildConfig.DEBUG);

        //自定义配置
        /*
        RxRetrofitConfig config = new RxRetrofitConfig.Builder().connectTimeout(20 * 1000)
                //.addInterceptor()
                //.addConverterFactory()
                //.addCallAdapterFactory()
                .build();
        RxRetrofitClient.init(config);
        */
    }
}
