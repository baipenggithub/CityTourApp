package com.bp.hmi.citytour.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.common.CityConstant;
import com.bp.hmi.citytour.databinding.ActivityMainBinding;
import com.bp.hmi.citytour.ui.fragment.AssistantFragment;
import com.bp.hmi.citytour.ui.fragment.HomeFragment;
import com.bp.hmi.citytour.ui.fragment.MyFragment;
import com.bp.hmi.citytour.ui.fragment.TravelFragment;
import com.bp.hmi.citytour.ui.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * MusicActivity.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel> {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.RECORD_AUDIO};
    private HomeFragment mHomeFragment;
    private TravelFragment mTravelFragment;
    private AssistantFragment mAssistantFragment;
    private MyFragment mMyFragment;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;

    }

    @Override
    protected void onResume() {
        super.onResume();
        switchFragment(0);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initLayout() {
        super.initLayout();
        initPermission();
        switchFragment(0);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        mViewModel.uiChangeObservable.homeFragmentLivEvent.observe(this, o -> switchFragment(0));
        mViewModel.uiChangeObservable.travelFragmentLivEvent.observe(this, o -> switchFragment(1));
        mViewModel.uiChangeObservable.assistantFragmentLivEvent.observe(this, o -> switchFragment(2));
        mViewModel.uiChangeObservable.videoFragmentLivEvent.observe(this, o -> switchFragment(3));
        mViewModel.uiChangeObservable.meFragmentLivEvent.observe(this, o -> switchFragment(4));
    }

    private void switchFragment(int position) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        hindFragment(transaction);
        switch (position) {
            case 0:
                setTabView(true, false, false, false, false);


                if (null == mHomeFragment) {
                    mHomeFragment = HomeFragment.getInstance();
                    transaction.add(R.id.content_frame_layout, mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
            case 1:

                setTabView(false, true, false, false, false);

                if (null == mTravelFragment) {
                    mTravelFragment = TravelFragment.getInstance();
                    transaction.add(R.id.content_frame_layout, mTravelFragment);
                } else {
                    transaction.show(mTravelFragment);
                }

                break;
            case 2:
                setTabView(false, false, true, false, false);

                if (null == mAssistantFragment) {
                    mAssistantFragment = AssistantFragment.getInstance();
                    transaction.add(R.id.content_frame_layout, mAssistantFragment);
                } else {
                    transaction.show(mAssistantFragment);
                }
                break;
            case 3:
                setTabView(false, false, false, true, false);
                Intent in = new Intent(this, VideoActivity.class);
                startActivity(in);
                break;

            case 4:
                setTabView(false, false, false, false, true);
                if (null == mMyFragment) {
                    mMyFragment = MyFragment.getInstance();
                    transaction.add(R.id.content_frame_layout, mMyFragment);
                } else {
                    transaction.show(mMyFragment);
                }
                break;

            default:
                break;

        }
        transaction.commit();
    }

    private void setTabView(boolean b, boolean b2, boolean b3, boolean b4, boolean b5) {
        mBinding.ivHome.setSelected(b);
        mBinding.tvHome.setSelected(b);
        if (b) {
            mBinding.tvHome.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.tvHome.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.ivTravel.setSelected(b2);
        mBinding.tvTravel.setSelected(b2);
        if (b2) {
            mBinding.tvTravel.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.tvTravel.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.ivAssistant.setSelected(b3);
        mBinding.tvAssistant.setSelected(b3);

        if (b3) {
            mBinding.tvAssistant.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.tvAssistant.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.ivVideo.setSelected(b4);
        mBinding.tvVideo.setSelected(b4);

        if (b4) {
            mBinding.tvVideo.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.tvVideo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.ivMe.setSelected(b5);
        mBinding.tvMe.setSelected(b5);

        if (b5) {
            mBinding.tvMe.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.tvMe.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }

    private void hindFragment(FragmentTransaction transaction) {
        if (null != mHomeFragment) {
            transaction.hide(mHomeFragment);
        }
        if (null != mTravelFragment) {
            transaction.hide(mTravelFragment);
        }
        if (null != mAssistantFragment) {
            transaction.hide(mAssistantFragment);
        }
        if (null != mMyFragment) {
            transaction.hide(mMyFragment);
        }
    }

    private void initPermission() {
        List<String> listNonPermissions = new ArrayList<>();
        for (String type : PERMISSIONS) {
            if (checkSelfPermission(type) != PackageManager.PERMISSION_GRANTED) {
                listNonPermissions.add(type);
            }
        }
        if (listNonPermissions.size() > CityConstant.PERMISSIONS_SIZE) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, CityConstant.REQUEST_CODE);
        }
    }
}
