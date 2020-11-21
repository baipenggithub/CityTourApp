package com.bp.hmi.citytour.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.databinding.ActivitySportsBinding;
import com.bp.hmi.citytour.ui.adapter.HomeCentreTabAdapter;
import com.bp.hmi.citytour.ui.adapter.SubTabTitleAdapter;
import com.bp.hmi.citytour.ui.viewmodel.HomeCentreTabViewModel;

public class SportsActivity extends BaseActivity<ActivitySportsBinding, HomeCentreTabViewModel> {
    private HomeCentreTabAdapter mHomeCentreTabAdapter;
    private SubTabTitleAdapter mSubTabTitleAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_sports;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        showProgress();
        mViewModel.getSubCardsTitle();
        mViewModel.requestActivityInfo("0");
    }

    @Override
    public void initLayout() {
        super.initLayout();

        mBinding.ivCardsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.subRecyclerView.setLayoutManager(linearLayoutManager);


        mBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mBinding.linTravel.setOnClickListener(v -> switchFragment(0));

        mBinding.linVideo.setOnClickListener(v -> switchFragment(1));

        mBinding.linMe.setOnClickListener(v -> switchFragment(2));

        switchFragment(0);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        mViewModel.mSubCardsTabTitleBean.observe(this, result -> {
            mSubTabTitleAdapter = new SubTabTitleAdapter(R.layout.sub_cards_tab_item_layout, result);
            mBinding.subRecyclerView.setAdapter(mSubTabTitleAdapter);
            mSubTabTitleAdapter.setSelectedIndex(0);

            mSubTabTitleAdapter.addOnItemClickListener(new SubTabTitleAdapter.OnItemClickListener() {
                @Override
                public void onItemListener(SubCardsTabTitleBean resultBean, int position, View view) {
                    mSubTabTitleAdapter.setSelectedIndex(position);

                }
            });        });

        mViewModel.mActivityData.observe(this, activityTabBean -> {
            hideProgress();
            mHomeCentreTabAdapter = new HomeCentreTabAdapter(R.layout.centre_tab_item_layout, activityTabBean.getResult().getItems());
            mBinding.mRecyclerView.setAdapter(mHomeCentreTabAdapter);
            mHomeCentreTabAdapter.addOnItemClickListener((itemsBean, position) -> {
                // Intent in = new Intent(SportsActivity.this, ActivityDetailsActivity.class);
                // startActivity(in);
            });

        });
    }

    private void switchFragment(int position) {
        switch (position) {
            case 0:
                setTabView(true, false, false);
                break;
            case 1:
                setTabView(false, true, false);
                break;
            case 2:
                setTabView(false, false, true);
                break;

            default:
                break;

        }
    }

    private void setTabView(boolean b, boolean b2, boolean b3) {
        mBinding.ivCompetition.setSelected(b);
        mBinding.tvCompetition.setSelected(b);
        if (b) {
            mBinding.tvCompetition.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.tvCompetition.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.ivVenue.setSelected(b2);
        mBinding.tvVenue.setSelected(b2);
        if (b2) {
            mBinding.tvVenue.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.tvVenue.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.ivFitness.setSelected(b3);
        mBinding.tvFitness.setSelected(b3);

        if (b3) {
            mBinding.tvFitness.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.tvFitness.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }

}