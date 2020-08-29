package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.action.BindingCommand;
import com.bp.hmi.citytour.action.SingleLiveEvent;
import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.bean.VideoBean;
import com.bp.hmi.citytour.bean.WeatherBean;
import com.bp.hmi.citytour.ui.repository.HomeRepository;

import java.util.List;

public class HomeViewModel extends BaseViewModel<HomeRepository> implements HomeRepository.IVideoResultCallback {
    private static final String TAG = HomeViewModel.class.getSimpleName();
    public SingleLiveEvent<List<VideoBean>> mVideoData;
    public SingleLiveEvent<WeatherBean> mWeatherInfo;
    public UiChangeObservable uiChangeObservable = new UiChangeObservable();

    public class UiChangeObservable {
        public SingleLiveEvent intoShEvent = new SingleLiveEvent<>();
    }

    public BindingCommand intoShCommand = new BindingCommand(() ->
            uiChangeObservable.intoShEvent.call());

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

    }

    @Override
    protected void createRepository() {
        mRepository = new HomeRepository(getApplication());
        mRepository.addVideoResultCallBack(this);

        mRepository.requestWeatherInfo();
        mRepository.getVoidData();

    }

    @Override
    public void videoResult(int code, List<VideoBean> result) {
        mVideoData.postValue(result);
    }

    @Override
    public void weatherResult(int code, WeatherBean result) {
        mWeatherInfo.postValue(result);
    }
}
