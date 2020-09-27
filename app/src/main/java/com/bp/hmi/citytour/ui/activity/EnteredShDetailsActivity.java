package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityJoinTripBinding;
import com.bp.hmi.citytour.ui.adapter.DetailsActivityAdapter;
import com.bp.hmi.citytour.ui.adapter.HomeBannerAdapter;
import com.bp.hmi.citytour.ui.viewmodel.JoinTripViewModel;
import com.bp.hmi.citytour.utils.ToastUtils;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 走进上海详情
 */
public class EnteredShDetailsActivity extends BaseActivity<ActivityJoinTripBinding, JoinTripViewModel> {
    private List<Integer> mBannerData = new ArrayList<>();
    private DetailsActivityAdapter mHomeCentreTabAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_join_trip;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        mBannerData.add(R.mipmap.pic_1_01);
        mBannerData.add(R.mipmap.pic_1_02);
        mBannerData.add(R.mipmap.pic_1_03);
        mBannerData.add(R.mipmap.pic_1_04);
        mBannerData.add(R.mipmap.pic_1_05);
        mBannerData.add(R.mipmap.pic_1_06);

        showProgress();
        mViewModel.requestActivityInfo();
    }

    @Override
    public void initLayout() {
        super.initLayout();

        mBinding.ivCardsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBinding.joinTripDetailsView.tvJoinTrip.setOnClickListener(view -> {
            Intent i = new Intent(EnteredShDetailsActivity.this, BookActivity.class);
            startActivity(i);
        });

        mBinding.joinTripDetailsView.ivJoinTripVoiceNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EnteredShDetailsActivity.this, VoiceActivity.class);
                startActivity(i);
            }
        });

        mBinding.joinTripDetailsView.ivJoinTripVirtualShowrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("敬请期待");
            }
        });

        mBinding.joinTripDetailsView.banner.setAdapter(new HomeBannerAdapter(mBannerData));
        mBinding.joinTripDetailsView.banner.setIndicator(new CircleIndicator(this));
        mBinding.joinTripDetailsView.banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
        mBinding.joinTripDetailsView.banner.setIndicatorMargins(new IndicatorConfig.Margins(0, 0,
                BannerConfig.INDICATOR_MARGIN, (int) BannerUtils.dp2px(12)));
        mBinding.joinTripDetailsView.banner.isAutoLoop(false);
        mBinding.joinTripDetailsView.banner.addBannerLifecycleObserver(this);


        // 相关活动
        mBinding.joinTripDetailsView.joinTripRecyclerView.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mBinding.joinTripDetailsView.joinTripRecyclerView.setLayoutManager(gridLayoutManager);


        //开启js脚本支持
        mBinding.joinTripDetailsView.mWeb.getSettings().setJavaScriptEnabled(true);
        // TODO 阻塞加载网络图片
        mBinding.joinTripDetailsView.mWeb.getSettings().setBlockNetworkImage(false);
        //设置加载进来的页面自适应手机屏幕
        mBinding.joinTripDetailsView.mWeb.getSettings().setLoadWithOverviewMode(true);
        //支持内容从新布局
        mBinding.joinTripDetailsView.mWeb.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置 缓存模式
        mBinding.joinTripDetailsView.mWeb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        //设置DOM Storage缓存
        mBinding.joinTripDetailsView.mWeb.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        mBinding.joinTripDetailsView.mWeb.getSettings().setDatabaseEnabled(true);
        //开启缓存
        mBinding.joinTripDetailsView.mWeb.getSettings().setAppCacheEnabled(true);

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        mViewModel.mActivityData.observe(this, activityTabBean -> {
            hideProgress();
            mHomeCentreTabAdapter = new DetailsActivityAdapter(R.layout.details_activity_item_layout, activityTabBean.getResult().getItems());
            mBinding.joinTripDetailsView.joinTripRecyclerView.setAdapter(mHomeCentreTabAdapter);

            mBinding.joinTripDetailsView.mWeb.loadDataWithBaseURL(null, activityTabBean.getResult().getItems().get(1).getContent(), "text/html", "utf-8", null);
        });
    }
}