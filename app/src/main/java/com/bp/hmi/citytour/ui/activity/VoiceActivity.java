package com.bp.hmi.citytour.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityVoiceBinding;
import com.bp.hmi.citytour.ui.viewmodel.VoiceViewModel;

/**
 * 类描述:语音导航
 * 创建人:LuoWeiDi
 * 创建时间:2020/9/27
 */
public class VoiceActivity extends BaseActivity<ActivityVoiceBinding, VoiceViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_voice;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
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
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
    }

    @Override
    public void initData() {
        super.initData();
    }

}

