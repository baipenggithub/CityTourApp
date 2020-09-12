package com.bp.hmi.citytour.ui.activity;

import android.os.Bundle;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityVideoBinding;
import com.bp.hmi.citytour.ui.viewmodel.VideoViewModel;

public class VideoActivity extends BaseActivity<ActivityVideoBinding, VideoViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_video;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}