package com.bp.hmi.citytour.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * BaseActivity.
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel>
        extends AppCompatActivity implements IBaseView {
    protected V mBinding;
    protected VM mViewModel;
    private int mViewModelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParam();
        initViewDataBinding(savedInstanceState);
        initLayout();
        initData();
        initViewObservable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinding != null) {
            mBinding.unbind();
        }
    }

    private void initViewDataBinding(Bundle savedInstanceState) {
        mBinding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));
        mViewModelId = initVariableId();
        mViewModel = initViewModel();

        if (mViewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            } else {
                //If no generic parameters are specified, BaseViewModel is used by default.
                modelClass = BaseViewModel.class;
            }
            mViewModel = (VM) createViewModel(this, modelClass);
        }
        //Associate ViewModel
        mBinding.setVariable(mViewModelId, mViewModel);
        //Support LiveData binding xml, data changes, UI will be updated automatically.
        mBinding.setLifecycleOwner(this);
        //Let ViewModel have the life cycle sensing of View.
        getLifecycle().addObserver(mViewModel);
    }

    /**
     * Refresh layout.
     */
    public void refreshLayout() {
        if (mViewModel != null) {
            mBinding.setVariable(mViewModelId, mViewModel);
        }
    }

    @Override
    public void initParam() {
        // TODO: initParam Reserved reference
    }

    /**
     * Initialize the root layout.
     *
     * @return layout id
     */
    public abstract int initContentView(Bundle savedInstanceState);

    /**
     * Initialize the id of the ViewModel.
     *
     * @return BR id
     */
    public abstract int initVariableId();

    /**
     * Initialize ViewModel.
     *
     * @return ViewModel inheriting BaseViewModel
     */
    public VM initViewModel() {
        return null;
    }

    @Override
    public void initData() {
        // TODO: initData Reserved reference
    }

    @Override
    public void initViewObservable() {
        // TODO: initViewObservable
    }

    public void initLayout() {
        // TODO: initLayout
    }

    /**
     * Create ViewModel.
     */
    public <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
        return ViewModelProviders.of(activity).get(cls);
    }
}
