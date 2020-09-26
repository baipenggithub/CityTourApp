
package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityCentreTabBinding;
import com.bp.hmi.citytour.ui.adapter.HomeCentreTabAdapter;
import com.bp.hmi.citytour.ui.adapter.SubTabTitleAdapter;
import com.bp.hmi.citytour.ui.viewmodel.HomeCentreTabViewModel;

/**
 * 展览,活动..
 */
public class HomeCentreTabActivity extends BaseActivity<ActivityCentreTabBinding, HomeCentreTabViewModel> {
    private String mType;
    private Bundle mBundle;
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
        mBundle = getIntent().getExtras();
        if (null != mBundle) {
            mType = mBundle.getString("HOME_TYPE", "");
            mViewModel.mTitle.set(mType);
        }

        //        if (mType.equals("活动")) {
        //            showProgress();
        //            mViewModel.getSubCardsTitle();
        //            mViewModel.requestActivityInfo();
        //        }
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
    }


    @Override
    public void initViewObservable() {
        super.initViewObservable();

        mViewModel.mSubCardsTabTitleBean.observe(this, result -> {
            Log.d("mSubCardsTabTitleBean", "result:" + result.size());
            mSubTabTitleAdapter = new SubTabTitleAdapter(R.layout.sub_cards_tab_item_layout, result);
            mBinding.subRecyclerView.setAdapter(mSubTabTitleAdapter);
            mSubTabTitleAdapter.setSelectedIndex(0);

            mSubTabTitleAdapter.addOnItemClickListener((resultBean, position) -> mSubTabTitleAdapter.setSelectedIndex(position));
        });

        mViewModel.mActivityData.observe(this, activityTabBean -> {
            hideProgress();
            mHomeCentreTabAdapter = new HomeCentreTabAdapter(R.layout.centre_tab_item_layout, activityTabBean.getResult().getItems());
            mBinding.mRecyclerView.setAdapter(mHomeCentreTabAdapter);
            mHomeCentreTabAdapter.addOnItemClickListener((itemsBean, position) -> {
                Intent in = new Intent(HomeCentreTabActivity.this, EnteredShDetailsActivity.class);
                startActivity(in);
            });

        });

    }
}