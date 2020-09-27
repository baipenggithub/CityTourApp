package com.bp.hmi.citytour.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityTravelBinding;
import com.bp.hmi.citytour.ui.adapter.VideoAdapter;
import com.bp.hmi.citytour.ui.viewmodel.TravelViewModel;

public class TravelActivity extends BaseActivity<ActivityTravelBinding, TravelViewModel> {
    private VideoAdapter mVideoAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_travel;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        showProgress();
        mViewModel.getVoidData();
    }

    @Override
    public void initLayout() {
        super.initLayout();
        mBinding.mRecyclerView.setNestedScrollingEnabled(false);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mBinding.mRecyclerView.setLayoutManager(manager);

        mBinding.ivCardsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        mViewModel.mVideoData.observe(this, itemsBeans -> {
            hideProgress();
            mVideoAdapter = new VideoAdapter(R.layout.video_item_layout, itemsBeans);
            mBinding.mRecyclerView.setAdapter(mVideoAdapter);
        });
    }
}