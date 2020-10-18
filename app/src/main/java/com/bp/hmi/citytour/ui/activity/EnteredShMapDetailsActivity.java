package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.bean.EnteredShBean;
import com.bp.hmi.citytour.bean.RecommendBean;
import com.bp.hmi.citytour.common.CityConstant;
import com.bp.hmi.citytour.databinding.FragmentShSpotBinding;
import com.bp.hmi.citytour.ui.adapter.HomeBannerAdapter;
import com.bp.hmi.citytour.ui.adapter.PavilionRecommendAdapter;
import com.bp.hmi.citytour.ui.viewmodel.EnterShDetailsViewModel;
import com.bp.hmi.citytour.utils.ToastUtils;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 地图详情
 */
public class EnteredShMapDetailsActivity extends BaseActivity<FragmentShSpotBinding, EnterShDetailsViewModel> {
    private static final String TAG = EnteredShMapDetailsActivity.class.getSimpleName();
    private List<Integer> mBannerData = new ArrayList<>();
    private EnteredShBean.ResultBean.ItemsBean itemsBean;


    public static EnteredShMapDetailsActivity getInstance() {
        return new EnteredShMapDetailsActivity();
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

        Bundle mBundle = getIntent().getExtras();
        itemsBean = (EnteredShBean.ResultBean.ItemsBean) mBundle.getSerializable(CityConstant.PARAMETER_PASSING_KEY);
        showProgress();
        mViewModel.requestActivityInfo(itemsBean.getId());

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


        mBinding.joinTripDetailsView.tvRelatedMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(EnteredShMapDetailsActivity.this, HomeActActivity.class);
                startActivity(in);
            }
        });

        mBinding.joinTripDetailsView.tvSurroundingMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(EnteredShMapDetailsActivity.this, HomeActActivity.class);
                startActivity(in);
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
                Intent in = new Intent(EnteredShMapDetailsActivity.this, BookActivity.class);
                startActivity(in);
            }
        });

        // 相关活动
        LinearLayoutManager managerPavilion = new LinearLayoutManager(this);
        managerPavilion.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.joinTripDetailsView.listPavilion.setLayoutManager(managerPavilion);
        PavilionRecommendAdapter pavilionAdapter = new PavilionRecommendAdapter(R.layout.item_haill_recommend, getPavilionData());
        mBinding.joinTripDetailsView.listPavilion.setAdapter(pavilionAdapter);

        LinearLayoutManager managerRim = new LinearLayoutManager(this);
        managerRim.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.joinTripDetailsView.listRim.setLayoutManager(managerRim);
        PavilionRecommendAdapter rimAdapter = new PavilionRecommendAdapter(R.layout.item_haill_recommend, getRimData());
        mBinding.joinTripDetailsView.listRim.setAdapter(rimAdapter);


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

        mViewModel.mActDetailsBean.observe(this, activityTabBean -> {
            hideProgress();
            //GlideUtils.loadCircleImage(BaseApplication.getApplication(), ApiService.HOME_API + activityTabBean.getResult().getCover(), mBinding.ivEnteredShAdv, 0);
            mBinding.joinTripDetailsView.tvJoinTripAddress.setText(activityTabBean.getResult().getName());
            //mBinding.joinTripDetailsView.tvJoinTripRanking.setText(activityTabBean.getResult().getPingfen() + "分");
            //mBinding.joinTripDetailsView.tvTime.setText(activityTabBean.getResult().getTime());
            //mBinding.joinTripDetailsView.tvAddress.setText(activityTabBean.getResult().getAddress());
            //详情
            mBinding.joinTripDetailsView.mWeb.loadDataWithBaseURL(null, activityTabBean.getResult().getContent(), "text/html", "utf-8", null);
        });
    }

    private List<RecommendBean> getPavilionData() {
        ArrayList<RecommendBean> list = new ArrayList<>();
        list.add(new RecommendBean(R.mipmap.hall_details_pic_04, "古元画展：纪念古元诞辰百年"));
        list.add(new RecommendBean(R.mipmap.hall_details_pic_05, "东风画展：纪念电视台"));
        list.add(new RecommendBean(R.mipmap.hall_details_pic_06, "城隍庙展：纪念城隍庙"));
        list.add(new RecommendBean(R.mipmap.hall_details_pic_07, "黄浦江画展：纪念江河"));
        return list;
    }

    private List<RecommendBean> getRimData() {
        ArrayList<RecommendBean> list = new ArrayList<>();
        list.add(new RecommendBean(R.mipmap.hall_details_pic_01, "万达绿地广场"));
        list.add(new RecommendBean(R.mipmap.hall_details_pic_02, "星巴克"));
        list.add(new RecommendBean(R.mipmap.hall_details_pic_03, "乐园田园"));
        return list;
    }
}
