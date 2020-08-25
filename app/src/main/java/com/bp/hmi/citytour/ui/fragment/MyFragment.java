package com.bp.hmi.citytour.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseFragment;
import com.bp.hmi.citytour.databinding.FragmentHomeBindingImpl;
import com.bp.hmi.citytour.ui.viewmodel.HomeViewModel;

public class MyFragment extends BaseFragment<FragmentHomeBindingImpl, HomeViewModel> {
    private static final String TAG = MyFragment.class.getSimpleName();

    public static MyFragment getInstance() {
        return new MyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
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
