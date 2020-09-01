package com.bp.hmi.citytour.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.bean.CardsBean;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.databinding.ActivityEnteredShBinding;
import com.bp.hmi.citytour.ui.adapter.EnteredShAdapter;
import com.bp.hmi.citytour.ui.adapter.SubTabTitleAdapter;
import com.bp.hmi.citytour.ui.viewmodel.EnteredShViewModel;

import java.util.List;

public class EnteredShActivity extends BaseActivity<ActivityEnteredShBinding, EnteredShViewModel> {
    private SubTabTitleAdapter mSubTabTitleAdapter;
    private EnteredShAdapter mEnteredShAdapter;


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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.subRecyclerView.setLayoutManager(linearLayoutManager);

        mBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        switchFragment(0);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        mViewModel.mSubCardsTabTitleBean.observe(this, new Observer<List<SubCardsTabTitleBean>>() {
            @Override
            public void onChanged(List<SubCardsTabTitleBean> result) {
                Log.d("mSubCardsTabTitleBean", "result:" + result.size());
                mSubTabTitleAdapter = new SubTabTitleAdapter(R.layout.sub_cards_tab_item_layout, result);
                mBinding.subRecyclerView.setAdapter(mSubTabTitleAdapter);
                mSubTabTitleAdapter.setSelectedIndex(0);

                mSubTabTitleAdapter.addOnItemClickListener(new SubTabTitleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemListener(SubCardsTabTitleBean resultBean, int position) {
                        mSubTabTitleAdapter.setSelectedIndex(position);
                    }
                });
            }
        });

        mViewModel.mCardsBeanList.observe(this, new Observer<List<CardsBean>>() {
            @Override
            public void onChanged(List<CardsBean> result) {
                mEnteredShAdapter = new EnteredShAdapter(R.layout.entered_sh_item_layout, result);
                mBinding.mRecyclerView.setAdapter(mEnteredShAdapter);
            }
        });

        mViewModel.uiChangeObservable.homeFragmentLivEvent.observe(this, o -> switchFragment(0));
        mViewModel.uiChangeObservable.travelFragmentLivEvent.observe(this, o -> switchFragment(1));
        mViewModel.uiChangeObservable.videoFragmentLivEvent.observe(this, o -> switchFragment(2));
        mViewModel.uiChangeObservable.meFragmentLivEvent.observe(this, o -> switchFragment(3));
    }

    private void switchFragment(int position) {
        switch (position) {
            case 0:
                setTabView(true, false, false, false);
                break;
            case 1:
                setTabView(false, true, false, false);
                break;
            case 2:
                setTabView(false, false, true, false);
                break;
            case 3:
                setTabView(false, false, false, true);
                break;
        }

    }

    private void setTabView(boolean b, boolean b2, boolean b4, boolean b5) {
        mBinding.enteredShBottomTabView.ivHome.setSelected(b);
        mBinding.enteredShBottomTabView.tvHome.setSelected(b);
        if (b) {
            mBinding.enteredShBottomTabView.tvHome.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.enteredShBottomTabView.tvHome.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.enteredShBottomTabView.ivTravel.setSelected(b2);
        mBinding.enteredShBottomTabView.tvTravel.setSelected(b2);
        if (b2) {
            mBinding.enteredShBottomTabView.tvTravel.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.enteredShBottomTabView.tvTravel.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.enteredShBottomTabView.ivVideo.setSelected(b4);
        mBinding.enteredShBottomTabView.tvVideo.setSelected(b4);

        if (b4) {
            mBinding.enteredShBottomTabView.tvVideo.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.enteredShBottomTabView.tvVideo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        mBinding.enteredShBottomTabView.ivMe.setSelected(b5);
        mBinding.enteredShBottomTabView.tvMe.setSelected(b5);

        if (b5) {
            mBinding.enteredShBottomTabView.tvMe.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            mBinding.enteredShBottomTabView.tvMe.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }

}