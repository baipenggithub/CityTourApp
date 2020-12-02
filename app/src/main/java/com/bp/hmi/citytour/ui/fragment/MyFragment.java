package com.bp.hmi.citytour.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseFragment;
import com.bp.hmi.citytour.databinding.FragmentMyBinding;
import com.bp.hmi.citytour.ui.activity.CardsActivity;
import com.bp.hmi.citytour.ui.viewmodel.MyViewModel;
import com.bp.hmi.citytour.utils.ToastUtils;

public class MyFragment extends BaseFragment<FragmentMyBinding, MyViewModel> implements View.OnClickListener {
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
        return R.layout.fragment_my;
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
        mBinding.setting.setOnClickListener(this);
        mBinding.menu1.setOnClickListener(this);
        mBinding.menu2.setOnClickListener(this);
        mBinding.menu3.setOnClickListener(this);
        mBinding.menu4.setOnClickListener(this);
        mBinding.menu5.setOnClickListener(this);
        mBinding.menu6.setOnClickListener(this);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting:
                ToastUtils.showLong("敬请期待");
                break;
            case R.id.menu_1:
                ToastUtils.showLong("敬请期待");
                break;
            case R.id.menu_2:
                ToastUtils.showLong("敬请期待");
                break;
            case R.id.menu_3:
                Intent in = new Intent(getActivity(), CardsActivity.class);
                in.putExtra("type", 1);
                startActivity(in);
                break;
            case R.id.menu_4:
                ToastUtils.showLong("敬请期待");
                break;
            case R.id.menu_5:
                ToastUtils.showLong("敬请期待");
                break;
            case R.id.menu_6:
                ToastUtils.showLong("敬请期待");
                break;
        }
    }
}
