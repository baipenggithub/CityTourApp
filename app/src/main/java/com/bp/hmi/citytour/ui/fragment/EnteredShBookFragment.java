package com.bp.hmi.citytour.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseFragment;
import com.bp.hmi.citytour.databinding.FragmentShBookBinding;
import com.bp.hmi.citytour.ui.viewmodel.EnteredShViewModel;

/**
 * 预约
 */
public class EnteredShBookFragment extends BaseFragment<FragmentShBookBinding, EnteredShViewModel> {
    private static final String TAG = EnteredShBookFragment.class.getSimpleName();

    public static EnteredShBookFragment getInstance() {
        return new EnteredShBookFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_sh_book;
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
