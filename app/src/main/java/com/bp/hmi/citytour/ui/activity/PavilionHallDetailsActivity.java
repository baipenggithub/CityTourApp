package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.bean.RecommendBean;
import com.bp.hmi.citytour.databinding.ActivityPavilionHallDetailsBinding;
import com.bp.hmi.citytour.ui.adapter.PavilionRecommendAdapter;
import com.bp.hmi.citytour.ui.viewmodel.PavilionHallViewModel;
import com.bp.hmi.citytour.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:展馆详情
 * 创建人:LuoWeiDi
 * 创建时间:2020/9/27
 */
public class PavilionHallDetailsActivity extends BaseActivity<ActivityPavilionHallDetailsBinding, PavilionHallViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_pavilion_hall_details;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initLayout() {
        super.initLayout();

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

        mBinding.tvSubscribeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PavilionHallDetailsActivity.this, BookDetailsActivity.class);
                startActivity(i);
            }
        });

        mBinding.tvSubscribeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("敬请期待");
            }
        });

        mBinding.ivCardsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBinding.ivHallNavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PavilionHallDetailsActivity.this, VoiceActivity.class);
                startActivity(i);
            }
        });

        mBinding.ivHallExhibition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("敬请期待");
            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
    }

    @Override
    public void initData() {
        super.initData();
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
