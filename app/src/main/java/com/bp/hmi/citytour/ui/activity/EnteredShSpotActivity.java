package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.FragmentShSpotBinding;
import com.bp.hmi.citytour.ui.adapter.DetailsActivityAdapter;
import com.bp.hmi.citytour.ui.adapter.HomeBannerAdapter;
import com.bp.hmi.citytour.ui.viewmodel.EnteredShViewModel;
import com.bp.hmi.citytour.utils.ToastUtils;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 景点推荐
 */
public class EnteredShSpotActivity extends BaseActivity<FragmentShSpotBinding, EnteredShViewModel> {
    private static final String TAG = EnteredShSpotActivity.class.getSimpleName();
    private DetailsActivityAdapter mHomeCentreTabAdapter;
    private List<Integer> mBannerData = new ArrayList<>();


    public static EnteredShSpotActivity getInstance() {
        return new EnteredShSpotActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.fragment_sh_spot;
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

        mBinding.joinTripDetailsView.tvSpotShowDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.joinTripDetailsView.tvSpotShowDetails.setVisibility(View.GONE);
                mBinding.joinTripDetailsView.llExhibitionIntroduced.setVisibility(View.VISIBLE);
            }
        });

        mBinding.joinTripDetailsView.tvSubscribeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("行程加入成功");
            }
        });

        mBinding.joinTripDetailsView.tvSubscribeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(EnteredShSpotActivity.this, BookActivity.class);
                startActivity(in);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.joinTripDetailsView.joinTripRecyclerView.setLayoutManager(linearLayoutManager);


        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.joinTripDetailsView.rimTripRecyclerView.setLayoutManager(linearLayoutManager);


        mBinding.joinTripDetailsView.banner.setAdapter(new HomeBannerAdapter(mBannerData));
        mBinding.joinTripDetailsView.banner.setIndicator(new CircleIndicator(this));
        mBinding.joinTripDetailsView.banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
        mBinding.joinTripDetailsView.banner.setIndicatorMargins(new IndicatorConfig.Margins(0, 0,
                BannerConfig.INDICATOR_MARGIN, (int) BannerUtils.dp2px(12)));
        mBinding.joinTripDetailsView.banner.isAutoLoop(false);
        mBinding.joinTripDetailsView.banner.addBannerLifecycleObserver(this);


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
            //相关活动
            mHomeCentreTabAdapter = new DetailsActivityAdapter(R.layout.details_activity_item_layout, activityTabBean.getResult().getItems());
            mBinding.joinTripDetailsView.joinTripRecyclerView.setAdapter(mHomeCentreTabAdapter);
            //周边活动
            mBinding.joinTripDetailsView.rimTripRecyclerView.setAdapter(mHomeCentreTabAdapter);

            //详情
            mBinding.joinTripDetailsView.mWeb.loadDataWithBaseURL(null, activityTabBean.getResult().getItems().get(1).getContent(), "text/html", "utf-8", null);

        });
    }
}
