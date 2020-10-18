package com.bp.hmi.citytour.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseFragment;
import com.bp.hmi.citytour.bean.EnteredShBean;
import com.bp.hmi.citytour.common.CityConstant;
import com.bp.hmi.citytour.databinding.FragmentShMapBinding;
import com.bp.hmi.citytour.ui.activity.EnteredShMapDetailsActivity;
import com.bp.hmi.citytour.ui.viewmodel.EnteredShViewModel;

/**
 * 地图
 */
public class EnteredShMapFragment extends BaseFragment<FragmentShMapBinding, EnteredShViewModel> {
    private static final String TAG = EnteredShMapFragment.class.getSimpleName();

    public static EnteredShMapFragment getInstance() {
        return new EnteredShMapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_sh_map;
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
        mBinding.coordinatorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnteredShBean.ResultBean.ItemsBean itemsBean = new EnteredShBean.ResultBean.ItemsBean();
                itemsBean.setId(3);

                Bundle mBundle = new Bundle();
                mBundle.putSerializable(CityConstant.PARAMETER_PASSING_KEY, itemsBean);
                Intent in = new Intent(getActivity(), EnteredShMapDetailsActivity.class);
                in.putExtras(mBundle);
                startActivity(in);
            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
    }
}
