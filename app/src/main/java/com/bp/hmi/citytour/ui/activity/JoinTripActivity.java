package com.bp.hmi.citytour.ui.activity;

import android.os.Bundle;

import androidx.databinding.library.baseAdapters.BR;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityJoinTripBinding;
import com.bp.hmi.citytour.ui.viewmodel.JoinTripViewModel;

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
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
    }
}