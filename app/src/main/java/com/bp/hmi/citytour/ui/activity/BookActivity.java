package com.bp.hmi.citytour.ui.activity;

import android.os.Bundle;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityBookBinding;
import com.bp.hmi.citytour.ui.viewmodel.BookViewModel;

public class BookActivity extends BaseActivity<ActivityBookBinding, BookViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_book;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initLayout() {
        super.initLayout();
    }

    @Override
    public void initData() {
        super.initData();

    }
}