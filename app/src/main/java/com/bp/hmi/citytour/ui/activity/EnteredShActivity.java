package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityEnteredShBinding;
import com.bp.hmi.citytour.ui.fragment.EnteredShBookFragment;
import com.bp.hmi.citytour.ui.fragment.EnteredShMapFragment;
import com.bp.hmi.citytour.ui.fragment.EnteredShRoundFragment;
import com.bp.hmi.citytour.ui.fragment.EnteredShSpotFragment;
import com.bp.hmi.citytour.ui.viewmodel.EnteredShViewModel;

/**
 * 走进上海01
 */
public class EnteredShActivity extends BaseActivity<ActivityEnteredShBinding, EnteredShViewModel> {

    private EnteredShRoundFragment mEnteredShRoundFragment;
    private EnteredShSpotFragment mEnteredShSpotFragment;
    private EnteredShMapFragment mEnteredShMapFragment;
    private EnteredShBookFragment mEnteredShBookFragment;


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_entered_sh;
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
        mViewModel.uiChangeObservable.homeFragmentLivEvent.observe(this, o -> switchFragment(0));
        mViewModel.uiChangeObservable.travelFragmentLivEvent.observe(this, o -> switchFragment(1));
        mViewModel.uiChangeObservable.videoFragmentLivEvent.observe(this, o -> switchFragment(2));
        mViewModel.uiChangeObservable.meFragmentLivEvent.observe(this, o -> switchFragment(3));
    }

    private void switchFragment(int position) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        hindFragment(transaction);
        switch (position) {

            case 0:
//                setTabView(true, false, false, false);
//
//                if (null == mEnteredShRoundFragment) {
//                    mEnteredShRoundFragment = EnteredShRoundFragment.getInstance();
//                    transaction.add(R.id.sh_frame_layout, mEnteredShRoundFragment);
//                } else {
//                    transaction.show(mEnteredShRoundFragment);
//                }
                startActivity(new Intent(this,EnteredShNewDetailsActivity.class));
                break;
            case 1:
                setTabView(false, true, false, false);

                if (null == mEnteredShSpotFragment) {
                    mEnteredShSpotFragment = EnteredShSpotFragment.getInstance();
                    transaction.add(R.id.sh_frame_layout, mEnteredShSpotFragment);
                } else {
                    transaction.show(mEnteredShSpotFragment);
                }
                break;
            case 2:
                setTabView(false, false, true, false);

                if (null == mEnteredShMapFragment) {
                    mEnteredShMapFragment = EnteredShMapFragment.getInstance();
                    transaction.add(R.id.sh_frame_layout, mEnteredShMapFragment);
                } else {
                    transaction.show(mEnteredShMapFragment);
                }
                break;
            case 3:
                setTabView(false, false, false, true);

                if (null == mEnteredShBookFragment) {
                    mEnteredShBookFragment = EnteredShBookFragment.getInstance();
                    transaction.add(R.id.sh_frame_layout, mEnteredShBookFragment);
                } else {
                    transaction.show(mEnteredShBookFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void setTabView(boolean b, boolean b2, boolean b4, boolean b5) {
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
        if (null != mEnteredShRoundFragment) {
            transaction.hide(mEnteredShRoundFragment);
        }
        if (null != mEnteredShSpotFragment) {
            transaction.hide(mEnteredShSpotFragment);
        }
        if (null != mEnteredShMapFragment) {
            transaction.hide(mEnteredShMapFragment);
        }
        if (null != mEnteredShBookFragment) {
            transaction.hide(mEnteredShBookFragment);
        }
    }
}