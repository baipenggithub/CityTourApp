package com.bp.hmi.citytour.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.widget.CustomProgressDialog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * BaseFragment.
 */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel>
        extends Fragment implements IBaseView {
    protected V mBinding;
    protected VM mViewModel;
    private int mViewModelId;
    private boolean mIsNavigationViewInit = false;
    private View mLastView = null;
    private CustomProgressDialog mLoadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParam();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mLoadingDialog = new CustomProgressDialog(getActivity(), R.style.customLoadDialog);
        if (mLastView == null) {
            mBinding = DataBindingUtil.inflate(inflater, initContentView(inflater,
                    container, savedInstanceState), container, false);
            mBinding.setLifecycleOwner(this);
            mLastView = mBinding.getRoot();
        }
        return mLastView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (!mIsNavigationViewInit) {
            super.onViewCreated(view, savedInstanceState);
            initViewDataBinding();
            initData();
            initLayout();
            initViewObservable();
        }
        mIsNavigationViewInit = true;
    }

    private void initViewDataBinding() {
        mViewModelId = initVariableId();
        mViewModel = initViewModel();
        if (mViewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            } else {
                modelClass = BaseViewModel.class;
            }
            mViewModel = (VM) createViewModel(this, modelClass);
        }
        mBinding.setVariable(mViewModelId, mViewModel);
        getLifecycle().addObserver(mViewModel);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBinding != null) {
            mBinding.unbind();
        }


        if (null != mLoadingDialog && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
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
        //TODO: initParam Reserved reference
    }

    /**
     * Initialize the root layout.
     *
     * @return layout id
     */
    public abstract int initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                                        @Nullable Bundle savedInstanceState);

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
        //TODO: initData Initialization
    }

    public void initLayout() {
        //TODO: initLayout Initialization
    }

    @Override
    public void initViewObservable() {
        //TODO: initViewObservable
    }

    /**
     * Create ViewModel.
     */
    public <T extends ViewModel> T createViewModel(Fragment fragment, Class<T> cls) {
        return ViewModelProviders.of(fragment).get(cls);
    }

    /**
     * Hide soft keyboard.
     */
    public void hideInput() {
        if (null != getActivity()) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            if (null != imm) {
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
            }
        }
    }

    /**
     * Display soft keyboard.
     *
     * @param editText edit
     */
    public void showInput(EditText editText) {
        if (null != getActivity()) {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            if (null != imm) {
                imm.showSoftInput(editText, 0);
            }
        }
    }

    /**
     * No data view.
     *
     * @return view
     */

    public View getEmptyView(int layoutRes) {
        View emptyView = getLayoutInflater().inflate(layoutRes, null);
        return emptyView;
    }

    /**
     * display loading box.
     */
    public void showProgress() {
        try {
            if (null != mLoadingDialog && mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }

            if (mLoadingDialog != null) {
                mLoadingDialog.show();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * hide loading box.
     */
    public void hideProgress() {
        try {
            if (null != mLoadingDialog && mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}