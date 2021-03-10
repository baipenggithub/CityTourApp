package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.library.baseAdapters.BR;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.bean.MarkerBean;
import com.bp.hmi.citytour.common.CityConstant;
import com.bp.hmi.citytour.databinding.ActivityMapBinding;
import com.bp.hmi.citytour.ui.viewmodel.MapViewModel;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;

import java.util.ArrayList;
import java.util.List;

import static com.amap.api.maps.AMap.MAP_TYPE_NORMAL;

public class MapActivity extends BaseActivity<ActivityMapBinding, MapViewModel> {
    private MapView mMapView = null;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private AMap mAMap;
    private String[] mShangHai = {
            "http://www.snhm.org.cn/xunidaolan/9/index.html",
            "http://www.snhm.org.cn/xunidaolan/10/index.html",
            "http://www.snhm.org.cn/xunidaolan/11/index.html",
            "http://www.snhm.org.cn/xunidaolan/20/index.html",
            "http://www.snhm.org.cn/xunidaolan/21/index.html",
            "https://digital.shmmc.com.cn/xnzt/hanghai_web.html"};
    ;
    private String[] mZhuHai = {
            "https://720yun.com/t/cbvkOhie017",
            "https://720yun.com/t/7evkOhie0p7",
    };

    private String[] mJiangSu = {
            "https://720yun.com/t/7avkcq2hp2q",
            "https://720yun.com/t/c1vkcq2h5ie",
            "https://720yun.com/t/f5vkcq2hr27",
            "https://www.szmuseum.com/GoldShow/index.html",
            "https://www.szmuseum.com/xyfy/index.html?scene_id=52837942"
    };

    private List<MarkerBean> markerBeanList = new ArrayList<>();

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
    public void initData() {
        super.initData();
        addData();
    }

    @Override
    public void initLayout() {
        super.initLayout();
        mBinding.ivBack.setOnClickListener(v -> finish());
        initMap();
        addMarker();
        setMapClick();

    }

    private void initMap() {
        mMapView = mBinding.map;
        if (mAMap == null) {
            mAMap = mMapView.getMap();
        }
        mUiSettings = mAMap.getUiSettings();//实例化UiSettings类对象
        //可以显示世界地图
        MapsInitializer.loadWorldGridMap(true);
        //******************
        //实例化UiSettings类对象
        UiSettings mUiSettings = mAMap.getUiSettings();
        //是否显示缩放按钮
        mUiSettings.setZoomControlsEnabled(false);
        mAMap.setMapType(MAP_TYPE_NORMAL);
    }

    private void setMapClick() {
        mAMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String id = marker.getSnippet();
                //上海
                if (Integer.parseInt(id) == 0) {
                    new XPopup.Builder(MapActivity.this)
                            .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                            .asCenterList("上海展馆", mShangHai,
                                    null,
                                    new OnSelectListener() {
                                        @Override
                                        public void onSelect(int position, String text) {
                                            gotoWebView(text);
                                        }
                                    })
                            .show();
                    // 珠海
                } else if (Integer.parseInt(id) == 1) {
                    new XPopup.Builder(MapActivity.this)
                            .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                            .asCenterList("珠海展馆", mZhuHai,
                                    null,
                                    new OnSelectListener() {
                                        @Override
                                        public void onSelect(int position, String text) {
                                            gotoWebView(text);
                                        }
                                    })
                            .show();

                    // 苏州
                } else if (Integer.parseInt(id) == 4) {
                    new XPopup.Builder(MapActivity.this)
                            .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                            .asCenterList("苏州展馆", mJiangSu,
                                    null,
                                    new OnSelectListener() {
                                        @Override
                                        public void onSelect(int position, String text) {
                                            gotoWebView(text);
                                        }
                                    })
                            .show();

                    // 重启
                } else if (Integer.parseInt(id) == 2) {
                    gotoWebView("http://www.scicity.cn/kjgv12/index.htm");
                    // 浙江
                } else if (Integer.parseInt(id) == 3) {
                    gotoWebView("http://jt.518pano.com:5004/project/15/");
                } else if (Integer.parseInt(id) == 5) {
                    // 山西
                    gotoWebView("http://www.shanximuseum.com/inter.html");
                }
                return true;
            }
        });
    }

    private void gotoWebView(String o) {
        Bundle bundle = new Bundle();
        bundle.putString(CityConstant.PARAMETER_PASSING_KEY, o);
        Intent in = new Intent(this, WebViewActivity.class);
        in.putExtras(bundle);
        startActivity(in);
    }

    private void addMarker() {
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        int size = markerBeanList.size();
        for (int i = 0; i < size; i++) {
            LatLng mLatLng = new LatLng(markerBeanList.get(i).getmLat(), markerBeanList.get(i).getmLon());
            boundsBuilder.include(mLatLng);
            MarkerOptions markerOption = new MarkerOptions()
                    .position(mLatLng)
                    .title(markerBeanList.get(i).getmTitle())
                    .period(i)
                    .snippet(markerBeanList.get(i).getmId())
                    .icon(BitmapDescriptorFactory.fromView(getMyView(markerBeanList.get(i).getmTitle())))
                    .draggable(true);

            mAMap.addMarker(markerOption);
        }
        mAMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 200));
    }

    protected View getMyView(String pm_val) {
        View view = getLayoutInflater().inflate(R.layout.mymarker, null);
        TextView tv_val = view.findViewById(R.id.tv_marker);
        tv_val.setText(pm_val);
        return view;
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

    private void addData() {
        MarkerBean shanghai = new MarkerBean();
        shanghai.setmId("0");
        shanghai.setmTitle("上海展馆");
        shanghai.setmLat(31.282032);
        shanghai.setmLon(121.594508);
        markerBeanList.add(shanghai);

        MarkerBean zhuhai = new MarkerBean();
        zhuhai.setmId("1");
        zhuhai.setmTitle("珠海展馆");
        zhuhai.setmLat(22.224979);
        zhuhai.setmLon(113.553986);
        markerBeanList.add(zhuhai);

        MarkerBean chongqing = new MarkerBean();
        chongqing.setmId("2");
        chongqing.setmTitle("重庆展馆");
        chongqing.setmLat(29.563761);
        chongqing.setmLon(106.550464);
        markerBeanList.add(chongqing);


        MarkerBean zhejiang = new MarkerBean();
        zhejiang.setmId("3");
        zhejiang.setmTitle("浙江展馆");
        zhejiang.setmLat(30.287459);
        zhejiang.setmLon(120.153576);
        markerBeanList.add(zhejiang);


        MarkerBean jiangsu = new MarkerBean();
        jiangsu.setmId("4");
        jiangsu.setmTitle("苏州展馆");
        jiangsu.setmLat(31.275908);
        jiangsu.setmLon(120.611345);
        markerBeanList.add(jiangsu);

        MarkerBean shanxi = new MarkerBean();
        shanxi.setmId("5");
        shanxi.setmTitle("山西展馆");
        shanxi.setmLat(37.857014);
        shanxi.setmLon(112.549248);
        markerBeanList.add(shanxi);

    }
}