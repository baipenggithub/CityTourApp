
package com.bp.hmi.citytour.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityOtherBinding;
import com.bp.hmi.citytour.ui.viewmodel.OhterViewModel;

public class OtherActivity extends BaseActivity<ActivityOtherBinding, OhterViewModel> {
    public static final String TBS_FILE_PATH = "filePath";
    public static final String TBS_TEMP_PATH = "tempPath";

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_other;
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
    }


    @Override
    public void initViewObservable() {
        super.initViewObservable();
    }
}