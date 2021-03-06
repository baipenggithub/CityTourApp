package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.action.BindingAction;
import com.bp.hmi.citytour.action.BindingCommand;
import com.bp.hmi.citytour.action.BindingConsumer;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.ActivityTabBean;
import com.bp.hmi.citytour.bean.HomeVideoBean;
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
    public SingleLiveEvent<HomeVideoBean> mVideoData;
    public SingleLiveEvent<ActivityTabBean> mActivityData;
    public SingleLiveEvent<WeatherBean> mWeatherInfo;
    public UiChangeObservable uiChangeObservable = new UiChangeObservable();
    public List<String> mCodeTitleList;
    public ObservableInt mCurrentIndex;
    public ObservableInt mPreIndex;
    public ObservableInt mNexIndex;
    private int mIndex = 1;
    public ObservableField<String> mUserName;
    private int mShowUserNameType = 0;
    public ObservableField<Drawable> mHomeBigScan;
    public ObservableField<String> mHomeBigHint;

    public class UiChangeObservable {
        public SingleLiveEvent intoShEvent = new SingleLiveEvent<>();
        public SingleLiveEvent showUserEvent = new SingleLiveEvent<>();
        public SingleLiveEvent hideUserEvent = new SingleLiveEvent<>();
        public SingleLiveEvent scanQrEvent = new SingleLiveEvent<>();
        public SingleLiveEvent homeCardEvent = new SingleLiveEvent<>();
        public SingleLiveEvent homeVideoEvent = new SingleLiveEvent<>();
        public SingleLiveEvent homeTabEvent = new SingleLiveEvent<>();
        public SingleLiveEvent homeActivityMoreEvent = new SingleLiveEvent<>();
        public SingleLiveEvent intoAnim = new SingleLiveEvent<>();

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
    public BindingCommand ivHomeCheckedLeft = new BindingCommand(() -> {
        uiChangeObservable.intoAnim.call();
        checkCodeIndex(0);
    });

    public BindingCommand ivHomeCheckedRight = new BindingCommand(() -> {
        uiChangeObservable.intoAnim.call();
        checkCodeIndex(1);
    });

    public BindingCommand ivHomeShowUserName = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //显示
            if (mShowUserNameType == 0) {
                mUserName.set("***");
                mShowUserNameType = 1;
                // 隐藏
            } else {
                mUserName.set("慧生活");
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

    //更多活动
    public BindingCommand ivgotoActivityMoreCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uiChangeObservable.homeActivityMoreEvent.call();
        }
    });


    //展览,活动...
    public BindingCommand<String> ivHomeTabCommand = new BindingCommand<String>(new BindingConsumer<String>() {
        @Override
        public void call(String s) {
            uiChangeObservable.homeTabEvent.setValue(s);
        }
    });

    /**
     * Constructor.
     *
     * @param application
     */
    public HomeViewModel(@NonNull Application application) {
        super(application);
        initData();
        createRepository();
    }

    protected void initData() {
        mVideoData = new SingleLiveEvent<>();
        mWeatherInfo = new SingleLiveEvent<>();
        mActivityData = new SingleLiveEvent<>();
        mHomeBigScan = new ObservableField<>(getApplication().getResources().getDrawable(R.mipmap.tour_home_04_09));
        mHomeBigHint = new ObservableField<>("绿色");
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
                        //dismissDialog();
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

    /**
     * 获取视频数据
     */
    public void getVoidData() {
        RxRetrofitClient.create(APiClient.class).getVideoData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeVideoBean>() {
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
                    public void onNext(HomeVideoBean weatherBean) {
                        Log.d(TAG, Thread.currentThread().getName() + "---onNext......" + weatherBean.toString());
                        //绑定数据
                        mVideoData.postValue(weatherBean);
                    }
                });
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
                    mHomeBigScan.set(BaseApplication.getApplication().getResources().getDrawable(R.mipmap.tour_home_04_20));
                    mHomeBigHint.set("请扫描");
                } else if (mIndex == 1) {
                    mPreIndex.set(0);
                    mCurrentIndex.set(1);
                    mNexIndex.set(2);
                    mHomeBigScan.set(BaseApplication.getApplication().getResources().getDrawable(R.mipmap.tour_home_04_09));
                    mHomeBigHint.set("绿色");
                } else if (mIndex == 2) {
                    mPreIndex.set(2);
                    mCurrentIndex.set(0);
                    mNexIndex.set(1);
                    mHomeBigHint.set("请扫描");
                    mHomeBigScan.set(BaseApplication.getApplication().getResources().getDrawable(R.mipmap.tour_home_04_21));
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
                    mHomeBigHint.set("请扫描");
                    mHomeBigScan.set(BaseApplication.getApplication().getResources().getDrawable(R.mipmap.tour_home_04_20));
                } else if (mIndex == 1) {
                    mPreIndex.set(0);
                    mCurrentIndex.set(1);
                    mNexIndex.set(2);
                    mHomeBigHint.set("绿色");
                    mHomeBigScan.set(BaseApplication.getApplication().getResources().getDrawable(R.mipmap.tour_home_04_09));

                } else if (mIndex == 2) {
                    mPreIndex.set(2);
                    mCurrentIndex.set(0);
                    mNexIndex.set(1);
                    mHomeBigHint.set("请扫描");
                    mHomeBigScan.set(BaseApplication.getApplication().getResources().getDrawable(R.mipmap.tour_home_04_21));

                }
                break;
        }
    }

    /**
     * 获取活动
     */
    public void requestActivityInfo() {
        Map<String, String> params = new HashMap<>();
        params.put("isNew", "0");
        params.put("type", "3");
        RxRetrofitClient.create(APiClient.class).getActivityData(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ActivityTabBean>() {
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
                    public void onNext(ActivityTabBean weatherBean) {
                        Log.d(TAG, Thread.currentThread().getName() + "---onNext......" + weatherBean.getResult().getItems().size());
                        //绑定数据
                        mActivityData.postValue(weatherBean);
                    }
                });
    }
}
