package com.bp.hmi.citytour.ui.activity;

import android.os.Bundle;

import androidx.databinding.library.baseAdapters.BR;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityMapBinding;
import com.bp.hmi.citytour.ui.viewmodel.BookViewModel;

public class MapActivity extends BaseActivity<ActivityMapBinding, BookViewModel> {
    private MapView mMapView = null;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private AMap mAMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != mMapView) {
            mMapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_map;
    }

    @Override
    public void initLayout() {
        super.initLayout();
        mMapView = mBinding.map;
        if (mAMap == null) {
            mAMap = mMapView.getMap();
        }
        mUiSettings = mAMap.getUiSettings();//实例化UiSettings类对象
        mUiSettings.setZoomControlsEnabled(false);

    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        if (null != mMapView) {
            mMapView.onSaveInstanceState(outState);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        if (null != mMapView) {
            mMapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        if (null != mMapView) {
            mMapView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        if (null != mMapView) {
            mMapView.onDestroy();
        }
    }

}