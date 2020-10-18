package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.bean.ActivityTabBean;
import com.bp.hmi.citytour.bean.RecommendBean;
import com.bp.hmi.citytour.common.CityConstant;
import com.bp.hmi.citytour.databinding.ActivityNewDetailsBinding;
import com.bp.hmi.citytour.http.ApiService;
import com.bp.hmi.citytour.ui.adapter.PavilionRecommendAdapter;
import com.bp.hmi.citytour.ui.viewmodel.ActDetailsViewModel;
import com.bp.hmi.citytour.utils.GlideUtils;
import com.bp.hmi.citytour.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:新活动详情
 * 创建人:LuoWeiDi
 * 创建时间:2020/10/13
 */
public class NewActivityDetailsActivity extends BaseActivity<ActivityNewDetailsBinding, ActDetailsViewModel> {
    private ActivityTabBean.ResultBean.ItemsBean itemsBean;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_new_details;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        showProgress();
        Bundle mBundle = getIntent().getExtras();
        itemsBean = (ActivityTabBean.ResultBean.ItemsBean) mBundle.getSerializable(CityConstant.PARAMETER_PASSING_KEY);
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

        mBinding.tvRelatedMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NewActivityDetailsActivity.this, HomeActActivity.class);
                startActivity(in);
            }
        });

        mBinding.tvSurroundingMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NewActivityDetailsActivity.this, HomeActActivity.class);
                startActivity(in);
            }
        });

        mBinding.tvSpotShowDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.tvSpotShowDetails.setVisibility(View.GONE);
                mBinding.llExhibitionIntroduced.setVisibility(View.VISIBLE);
            }
        });

        mBinding.tvSubscribeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(NewActivityDetailsActivity.this, BookDetailsActivity.class);
                startActivity(in);
            }
        });

        mBinding.tvSubscribeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtils.showLong("敬请期待");
            }
        });


        //开启js脚本支持
        mBinding.mWeb.getSettings().setJavaScriptEnabled(true);
        // TODO 阻塞加载网络图片
        mBinding.mWeb.getSettings().setBlockNetworkImage(false);
        //设置加载进来的页面自适应手机屏幕
        mBinding.mWeb.getSettings().setLoadWithOverviewMode(true);
        //支持内容从新布局
        mBinding.mWeb.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置 缓存模式
        mBinding.mWeb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        //设置DOM Storage缓存
        mBinding.mWeb.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        mBinding.mWeb.getSettings().setDatabaseEnabled(true);
        //开启缓存
        mBinding.mWeb.getSettings().setAppCacheEnabled(true);

        LinearLayoutManager managerPavilion = new LinearLayoutManager(this);
        managerPavilion.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.listPavilion.setLayoutManager(managerPavilion);
        PavilionRecommendAdapter pavilionAdapter = new PavilionRecommendAdapter(R.layout.item_haill_recommend, getPavilionData());
        mBinding.listPavilion.setAdapter(pavilionAdapter);

        LinearLayoutManager managerRim = new LinearLayoutManager(this);
        managerRim.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.listRim.setLayoutManager(managerRim);
        PavilionRecommendAdapter rimAdapter = new PavilionRecommendAdapter(R.layout.item_haill_recommend, getRimData());
        mBinding.listRim.setAdapter(rimAdapter);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        mViewModel.mActDetailsBean.observe(this, activityTabBean -> {
            hideProgress();

            GlideUtils.loadCircleImage_17(BaseApplication.getApplication(), ApiService.HOME_API + activityTabBean.getResult().getCover(), mBinding.ivAvd);
            mBinding.tvJoinTripAddress.setText(activityTabBean.getResult().getName());
            mBinding.tvJoinTripRanking.setText(activityTabBean.getResult().getPingfen() + "分");
            mBinding.tvTime.setText(activityTabBean.getResult().getTime());
            mBinding.tvAddress.setText(activityTabBean.getResult().getAddress());
            //详情
            mBinding.mWeb.loadDataWithBaseURL(null, activityTabBean.getResult().getContent(), "text/html", "utf-8", null);

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
