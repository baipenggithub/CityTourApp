package com.bp.hmi.citytour.base;

import android.app.Application;

import com.bp.hmi.citytour.BuildConfig;
import com.bp.hmi.citytour.http.ApiService;
import com.bp.hmi.citytour.http.RxRetrofitClient;

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
