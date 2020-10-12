package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityEnteredShBinding;
import com.bp.hmi.citytour.ui.adapter.EnteredShAdapter;
import com.bp.hmi.citytour.ui.adapter.HomeBannerAdapter;
import com.bp.hmi.citytour.ui.adapter.SubTabTitleAdapter;
import com.bp.hmi.citytour.ui.fragment.EnteredShMapFragment;
import com.bp.hmi.citytour.ui.viewmodel.EnteredShViewModel;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 走进上海01
 */
public class EnteredShActivity extends BaseActivity<ActivityEnteredShBinding, EnteredShViewModel> {
    private EnteredShMapFragment mEnteredShMapFragment;

    private List<Integer> mBannerData = new ArrayList<>();
    private SubTabTitleAdapter mSubTabTitleAdapter;
    private EnteredShAdapter mEnteredShAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        //        setTabView(false, false, false, false);
        mBinding.mapFrameLayout.setVisibility(View.GONE);
        mBinding.shFrameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_entered_sh;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        super.initData();

        mBannerData.add(R.mipmap.pic_1_07);
        mBannerData.add(R.mipmap.pic_1_08);
        mBannerData.add(R.mipmap.pic_1_09);
        mBannerData.add(R.mipmap.pic_1_10);
        mBannerData.add(R.mipmap.pic_1_11);

        showProgress();
        mViewModel.getSubCardsTitle();
        mViewModel.getCardsList();
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


        //广告
        mBinding.banner.setAdapter(new HomeBannerAdapter(mBannerData));
        mBinding.banner.setIndicator(new CircleIndicator(this));
        mBinding.banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
        mBinding.banner.setIndicatorMargins(new IndicatorConfig.Margins(0, 0,
                BannerConfig.INDICATOR_MARGIN, (int) BannerUtils.dp2px(12)));
        mBinding.banner.isAutoLoop(false);
        mBinding.banner.addBannerLifecycleObserver(this);

        // 列表
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.subRecyclerView.setLayoutManager(linearLayoutManager);

        mBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        mViewModel.uiChangeObservable.homeFragmentLivEvent.observe(this, o -> switchFragment(0));
        mViewModel.uiChangeObservable.travelFragmentLivEvent.observe(this, o -> switchFragment(1));
        mViewModel.uiChangeObservable.videoFragmentLivEvent.observe(this, o -> switchFragment(2));
        mViewModel.uiChangeObservable.meFragmentLivEvent.observe(this, o -> switchFragment(3));

        mViewModel.mSubCardsTabTitleBean.observe(this, result -> {
            mSubTabTitleAdapter = new SubTabTitleAdapter(R.layout.sub_cards_tab_item_layout, result);
            mBinding.subRecyclerView.setAdapter(mSubTabTitleAdapter);
            mSubTabTitleAdapter.setSelectedIndex(0);

            mSubTabTitleAdapter.addOnItemClickListener((resultBean, position) -> mSubTabTitleAdapter.setSelectedIndex(position));
        });

        mViewModel.mEnteredShBeanList.observe(this, enteredShBean -> {
            hideProgress();
            mEnteredShAdapter = new EnteredShAdapter(R.layout.entered_sh_item_layout, enteredShBean.getResult().getItems());
            mBinding.mRecyclerView.setAdapter(mEnteredShAdapter);

            mEnteredShAdapter.addOnItemClickListener((itemsBean, position) -> {
                Intent in = new Intent(this, EnteredShDetailsActivity.class);
                startActivity(in);
            });
        });
        setTabView(true, false, false, false);
    }

    private void switchFragment(int position) {
        switch (position) {
            case 0:
                setTabView(true, false, false, false);
                Intent i = new Intent(EnteredShActivity.this, EnteredShNewDetailsActivity.class);
                startActivity(i);
                break;
            case 1:
                setTabView(false, true, false, false);
                i = new Intent(EnteredShActivity.this, EnteredShSpotActivity.class);
                startActivity(i);

                break;
            case 2:
                mBinding.mapFrameLayout.setVisibility(View.VISIBLE);
                mBinding.shFrameLayout.setVisibility(View.GONE);
                setTabView(false, false, true, false);
                FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
                if (null == mEnteredShMapFragment) {
                    mEnteredShMapFragment = EnteredShMapFragment.getInstance();
                    transaction.add(R.id.map_frame_layout, mEnteredShMapFragment);
                } else {
                    transaction.show(mEnteredShMapFragment);
                }
                transaction.commit();
                break;
            case 3:
                setTabView(false, false, false, true);
                i = new Intent(EnteredShActivity.this, BookActivity.class);
                startActivity(i);
                break;
        }

    }

    private void setTabView(boolean b, boolean b2, boolean b4, boolean b5) {
        mBinding.ivHome.setSelected(b);
        mBinding.tvHome.setSelected(b);
        if (b) {
            mBinding.tvHome.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.tvHome.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.ivTravel.setSelected(b2);
        mBinding.tvTravel.setSelected(b2);
        if (b2) {
            mBinding.tvTravel.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.tvTravel.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.ivVideo.setSelected(b4);
        mBinding.tvVideo.setSelected(b4);

        if (b4) {
            mBinding.tvVideo.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.tvVideo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.ivMe.setSelected(b5);
        mBinding.tvMe.setSelected(b5);

        if (b5) {
            mBinding.tvMe.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.tvMe.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }
}