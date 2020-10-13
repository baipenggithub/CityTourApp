
package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.databinding.ActivityCentreTabBinding;
import com.bp.hmi.citytour.http.ApiService;
import com.bp.hmi.citytour.ui.adapter.HomeCentreTabAdapter;
import com.bp.hmi.citytour.ui.adapter.SubTabTitleAdapter;
import com.bp.hmi.citytour.ui.viewmodel.HomeCentreTabViewModel;
import com.bp.hmi.citytour.utils.GlideUtils;

/**
 * 活动..
 */
public class HomeActActivity extends BaseActivity<ActivityCentreTabBinding, HomeCentreTabViewModel> {
    private SubTabTitleAdapter mSubTabTitleAdapter;
    private HomeCentreTabAdapter mHomeCentreTabAdapter;


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_centre_tab;
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.subRecyclerView.setLayoutManager(linearLayoutManager);

        mBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mBinding.llAcDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActActivity.this, NewActivityDetailsActivity.class);
                startActivity(in);
            }
        });
    }


    @Override
    public void initViewObservable() {
        super.initViewObservable();

        mViewModel.mSubCardsTabTitleBean.observe(this, result -> {
            mSubTabTitleAdapter = new SubTabTitleAdapter(R.layout.sub_cards_tab_item_layout, result);
            mBinding.subRecyclerView.setAdapter(mSubTabTitleAdapter);
            mSubTabTitleAdapter.setSelectedIndex(0);

            mSubTabTitleAdapter.addOnItemClickListener((resultBean, position) -> mSubTabTitleAdapter.setSelectedIndex(position));
        });

        mViewModel.mActivityData.observe(this, activityTabBean -> {
            hideProgress();
            mHomeCentreTabAdapter = new HomeCentreTabAdapter(R.layout.centre_tab_item_layout, activityTabBean.getResult().getItems());
            mBinding.mRecyclerView.setAdapter(mHomeCentreTabAdapter);

            mBinding.tvHomeActivityName.setText(activityTabBean.getResult().getItems().get(3).getName());
            mBinding.tvHomeActivityMessage.setText(activityTabBean.getResult().getItems().get(3).getSummary());

            GlideUtils.loadCircleImage_10(BaseApplication.getApplication(), ApiService.HOME_API + activityTabBean.getResult().getItems().get(3).getCover(), mBinding.ivHomeActivityCover);

            mHomeCentreTabAdapter.addOnItemClickListener((itemsBean, position) -> {
                Intent in = new Intent(HomeActActivity.this, NewActivityDetailsActivity.class);
                startActivity(in);
            });

        });

    }
}