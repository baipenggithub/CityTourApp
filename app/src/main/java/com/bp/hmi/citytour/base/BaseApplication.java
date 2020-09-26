package com.bp.hmi.citytour.base;

import android.app.Application;

import com.bp.hmi.citytour.BuildConfig;
import com.bp.hmi.citytour.http.ApiService;
import com.bp.hmi.citytour.http.RxRetrofitClient;
import com.dueeeke.videoplayer.exo.ExoMediaPlayerFactory;
import com.dueeeke.videoplayer.ijk.IjkPlayerFactory;
import com.dueeeke.videoplayer.player.AndroidMediaPlayerFactory;
import com.dueeeke.videoplayer.player.VideoViewConfig;
import com.dueeeke.videoplayer.player.VideoViewManager;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

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
        initZxing();
        initVideo();
    }

    public static void setApplication(BaseApplication application) {
        sInstance = application;
    }

    public static BaseApplication getApplication() {
        return sInstance;
    }

    private void initZxing() {
        ZXingLibrary.initDisplayOpinion(this);
    }

    private void initVideo() {
        VideoViewManager.setConfig(VideoViewConfig.newBuilder()
                //使用使用IjkPlayer解码
                .setPlayerFactory(IjkPlayerFactory.create())
                //使用ExoPlayer解码
                .setPlayerFactory(ExoMediaPlayerFactory.create())
                //使用MediaPlayer解码
                .setPlayerFactory(AndroidMediaPlayerFactory.create())
                .build());
    }

    private void initOkHttp() {
        RxRetrofitClient.init(ApiService.HOME_API, BuildConfig.DEBUG);
    }
}
