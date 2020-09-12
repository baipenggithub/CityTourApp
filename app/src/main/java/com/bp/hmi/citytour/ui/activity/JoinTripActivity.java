package com.bp.hmi.citytour.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityJoinTripBinding;
import com.bp.hmi.citytour.ui.viewmodel.JoinTripViewModel;
import com.bp.hmi.citytour.utils.ToastUtils;

public class JoinTripActivity extends BaseActivity<ActivityJoinTripBinding, JoinTripViewModel> {

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

        mBinding.joinTripDetailsView.tvJoinTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showLong("已加入行程");
            }
        });

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
    }
}