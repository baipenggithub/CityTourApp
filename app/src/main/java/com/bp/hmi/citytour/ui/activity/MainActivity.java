package com.bp.hmi.citytour.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.common.MapConstant;
import com.bp.hmi.citytour.databinding.ActivityMainBinding;
import com.bp.hmi.citytour.ui.fragment.AssistantFragment;
import com.bp.hmi.citytour.ui.fragment.HomeFragment;
import com.bp.hmi.citytour.ui.fragment.MyFragment;
import com.bp.hmi.citytour.ui.fragment.TravelFragment;
import com.bp.hmi.citytour.ui.fragment.VideoFragment;
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
            Manifest.permission.ACCESS_FINE_LOCATION};
    private HomeFragment mHomeFragment;
    private TravelFragment mTravelFragment;
    private AssistantFragment mAssistantFragment;
    private VideoFragment mVideoFragment;
    private MyFragment mMyFragment;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;

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
                if (null == mVideoFragment) {
                    mVideoFragment = VideoFragment.getInstance();
                    transaction.add(R.id.content_frame_layout, mVideoFragment);
                } else {
                    transaction.show(mVideoFragment);
                }
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
        mBinding.homeBottomTabView.ivHome.setSelected(b);
        mBinding.homeBottomTabView.tvHome.setSelected(b);
        if (b) {
            mBinding.homeBottomTabView.tvHome.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.homeBottomTabView.tvHome.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.homeBottomTabView.ivTravel.setSelected(b2);
        mBinding.homeBottomTabView.tvTravel.setSelected(b2);
        if (b2) {
            mBinding.homeBottomTabView.tvTravel.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.homeBottomTabView.tvTravel.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.homeBottomTabView.ivAssistant.setSelected(b3);
        mBinding.homeBottomTabView.tvAssistant.setSelected(b3);

        if (b3) {
            mBinding.homeBottomTabView.tvAssistant.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.homeBottomTabView.tvAssistant.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.homeBottomTabView.ivVideo.setSelected(b4);
        mBinding.homeBottomTabView.tvVideo.setSelected(b4);

        if (b4) {
            mBinding.homeBottomTabView.tvVideo.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.homeBottomTabView.tvVideo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.homeBottomTabView.ivMe.setSelected(b5);
        mBinding.homeBottomTabView.tvMe.setSelected(b5);

        if (b5) {
            mBinding.homeBottomTabView.tvMe.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.homeBottomTabView.tvMe.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
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
        if (null != mVideoFragment) {
            transaction.hide(mVideoFragment);
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
        if (listNonPermissions.size() > MapConstant.PERMISSIONS_SIZE) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, MapConstant.REQUEST_CODE);
        }
    }
}
