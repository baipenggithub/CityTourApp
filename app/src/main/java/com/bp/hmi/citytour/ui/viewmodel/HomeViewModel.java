package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.bp.hmi.citytour.action.BindingAction;
import com.bp.hmi.citytour.action.BindingCommand;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.VideoBean;
import com.bp.hmi.citytour.bean.WeatherBean;
import com.bp.hmi.citytour.http.APiClient;
import com.bp.hmi.citytour.http.RxRetrofitClient;
import com.bp.hmi.citytour.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {
    private static final String TAG = HomeViewModel.class.getSimpleName();
    public SingleLiveEvent<List<VideoBean>> mVideoData;
    public SingleLiveEvent<WeatherBean> mWeatherInfo;
    public UiChangeObservable uiChangeObservable = new UiChangeObservable();
    public List<String> mCodeTitleList;
    public ObservableInt mCurrentIndex;
    public ObservableInt mPreIndex;
    public ObservableInt mNexIndex;
    private int mIndex = 1;
    public ObservableField<String> mUserName;
    private int mShowUserNameType = 0;

    public class UiChangeObservable {
        public SingleLiveEvent intoShEvent = new SingleLiveEvent<>();
        public SingleLiveEvent showUserEvent = new SingleLiveEvent<>();
        public SingleLiveEvent hideUserEvent = new SingleLiveEvent<>();
        public SingleLiveEvent scanQrEvent = new SingleLiveEvent<>();
        public SingleLiveEvent homeCardEvent = new SingleLiveEvent<>();
        public SingleLiveEvent homeVideoEvent = new SingleLiveEvent<>();

    }

    public BindingCommand intoShCommand = new BindingCommand(() ->
            uiChangeObservable.intoShEvent.call());

    public BindingCommand showUserCommand = new BindingCommand(() ->
            uiChangeObservable.showUserEvent.call());

    public BindingCommand hideUserCommand = new BindingCommand(() ->
            uiChangeObservable.hideUserEvent.call());

    public BindingCommand tvHomeAddress = new BindingCommand(() ->
            ToastUtils.showLong("当前只支持上海")
    );

    public BindingCommand ivHomeScanQr = new BindingCommand(() ->
            uiChangeObservable.scanQrEvent.call()
    );

    public BindingCommand tvHomeSearch = new BindingCommand(() ->
            ToastUtils.showLong("暂无搜索内容")
    );
    public BindingCommand ivHomeCheckedLeft = new BindingCommand(() ->
            checkCodeIndex(0)
    );

    public BindingCommand ivHomeCheckedRight = new BindingCommand(() ->
            checkCodeIndex(1)
    );

    public BindingCommand ivHomeShowUserName = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //显示
            if (mShowUserNameType == 0) {
                mUserName.set("城市游");
                mShowUserNameType = 1;
                // 隐藏
            } else {
                mUserName.set("姓名");
                mShowUserNameType = 0;
            }
        }
    });

    //卡包
    public BindingCommand ivHomeCardCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showLong("敬请期待");
        }
    });

    //消费券
    public BindingCommand ivHomeGiftCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uiChangeObservable.homeCardEvent.call();
        }
    });

    //行程
    public BindingCommand ivHomeRoundCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showLong("敬请期待");
        }
    });

    //更多视频
    public BindingCommand ivgotoVideoCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uiChangeObservable.homeVideoEvent.call();
        }
    });

    /**
     * Constructor.
     *
     * @param application
     */
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void initData() {
        mVideoData = new SingleLiveEvent<>();
        mWeatherInfo = new SingleLiveEvent<>();
        //城市码
        mPreIndex = new ObservableInt(0);
        mCurrentIndex = new ObservableInt(1);
        mNexIndex = new ObservableInt(2);
        //地铁码类型
        mCodeTitleList = new ArrayList<>();
        mCodeTitleList.add("公交码");
        mCodeTitleList.add("文旅码");
        mCodeTitleList.add("地铁码");

        //姓名
        mUserName = new ObservableField<>("姓名");
    }

    @Override
    protected void createRepository() {
        requestWeatherInfo();
        getVoidData();

    }

    /**
     * 获取天气
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
                        mWeatherInfo.postValue(weatherBean);
                    }
                });
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

        mVideoData.postValue(bean);
    }

    private void checkCodeIndex(int index) {
        switch (index) {
            case 0:
                mIndex--;
                if (mIndex < 0) {
                    mIndex = mCodeTitleList.size() - 1;
                }
                Log.e("mCurrentIndex---", "mCurrentIndex:" + mIndex);
                if (mIndex == 0) {
                    mPreIndex.set(1);
                    mCurrentIndex.set(2);
                    mNexIndex.set(0);
                } else if (mIndex == 1) {
                    mPreIndex.set(0);
                    mCurrentIndex.set(1);
                    mNexIndex.set(2);
                } else if (mIndex == 2) {
                    mPreIndex.set(2);
                    mCurrentIndex.set(0);
                    mNexIndex.set(1);
                }
                break;
            case 1:
                if (mIndex >= mCodeTitleList.size() - 1) {
                    mIndex = 0;
                } else {
                    mIndex++;
                }

                if (mIndex == 0) {
                    mPreIndex.set(1);
                    mCurrentIndex.set(2);
                    mNexIndex.set(0);
                } else if (mIndex == 1) {
                    mPreIndex.set(0);
                    mCurrentIndex.set(1);
                    mNexIndex.set(2);
                } else if (mIndex == 2) {
                    mPreIndex.set(2);
                    mCurrentIndex.set(0);
                    mNexIndex.set(1);
                }
                break;
        }
    }
}
