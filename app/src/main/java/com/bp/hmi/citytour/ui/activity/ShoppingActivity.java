package com.bp.hmi.citytour.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.databinding.ActivityShoppingBindingImpl;
import com.bp.hmi.citytour.ui.adapter.HomeCentreTabAdapter;
import com.bp.hmi.citytour.ui.adapter.SubTabTitleAdapter;
import com.bp.hmi.citytour.ui.viewmodel.HomeCentreTabViewModel;

public class ShoppingActivity extends BaseActivity<ActivityShoppingBindingImpl, HomeCentreTabViewModel> {
    private HomeCentreTabAdapter mHomeCentreTabAdapter;
    private SubTabTitleAdapter mSubTabTitleAdapter;
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_shopping;
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
        mViewModel.requestShopping();
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
                    if (resultBean.getTitle().equals("类型")) {
                       // showMenuPop(view);
                    }

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
}