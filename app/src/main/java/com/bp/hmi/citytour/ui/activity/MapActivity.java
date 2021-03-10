package com.bp.hmi.citytour.ui.activity;

import android.os.Bundle;

import androidx.databinding.library.baseAdapters.BR;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityMapBinding;
import com.bp.hmi.citytour.ui.viewmodel.MapViewModel;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends BaseActivity<ActivityMapBinding, MapViewModel> {
    private MapView mMapView = null;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private AMap mAMap;
    private List<String> mShangHai=new ArrayList<>();
    private List<String> mZhuHai=new ArrayList<>();
    private List<String>mChongQin=new ArrayList<>();
    private List<String>mZheJiang=new ArrayList<>();
    private List<String>mJiangSu=new ArrayList<>();
    private List<String>mShanXi=new ArrayList<>();
    private List<String>mHangHai=new ArrayList<>();

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

    private void addData(){
        // 上海
        mShangHai.add("http://www.snhm.org.cn/xunidaolan/9/index.html");
        mShangHai.add("http://www.snhm.org.cn/xunidaolan/10/index.html");
        mShangHai.add("http://www.snhm.org.cn/xunidaolan/11/index.html");
        mShangHai.add("http://www.snhm.org.cn/xunidaolan/20/index.html");
        mShangHai.add("http://www.snhm.org.cn/xunidaolan/21/index.html");

        //珠海
        mZhuHai.add("https://720yun.com/t/cbvkOhie017");
        mZhuHai.add("https://720yun.com/t/7evkOhie0p7");

        //重庆
        mChongQin.add("http://www.scicity.cn/kjgv12/index.htm");

        //浙江
        mZheJiang.add("http://jt.518pano.com:5004/project/15/");

        //苏州
        mJiangSu.add("https://720yun.com/t/7avkcq2hp2q");
        mJiangSu.add("https://720yun.com/t/c1vkcq2h5ie");
        mJiangSu.add("https://720yun.com/t/f5vkcq2hr27");
        mJiangSu.add("https://www.szmuseum.com/GoldShow/index.html");
        mJiangSu.add("https://www.szmuseum.com/xyfy/index.html?scene_id=52837942");

        // 山西
        mShanXi.add("https://www.shanximuseum.com/inter.html");

        // 航海
        mHangHai.add("https://digital.shmmc.com.cn/xnzt/hanghai_web.html");
    }


}