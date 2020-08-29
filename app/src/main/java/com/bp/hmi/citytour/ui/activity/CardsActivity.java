package com.bp.hmi.citytour.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.bean.CardsBean;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.databinding.ActivityCardsBinding;
import com.bp.hmi.citytour.ui.adapter.CardsAdapter;
import com.bp.hmi.citytour.ui.adapter.SubCardsTabAdapter;
import com.bp.hmi.citytour.ui.viewmodel.CardsViewModel;

import java.util.List;

public class CardsActivity extends BaseActivity<ActivityCardsBinding, CardsViewModel> {
    private CardsAdapter mCardsAdapter;
    private SubCardsTabAdapter mSubCardsTabAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_cards;
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
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        mViewModel.mSubCardsTabTitleBean.observe(this, new Observer<List<SubCardsTabTitleBean>>() {
            @Override
            public void onChanged(List<SubCardsTabTitleBean> result) {
                Log.d("mSubCardsTabTitleBean", "result:" + result.size());
                mSubCardsTabAdapter = new SubCardsTabAdapter(R.layout.sub_cards_tab_item_layout, result);
                mBinding.subRecyclerView.setAdapter(mSubCardsTabAdapter);
                mSubCardsTabAdapter.setSelectedIndex(0);

                mSubCardsTabAdapter.addOnItemClickListener(new SubCardsTabAdapter.OnItemClickListener() {
                    @Override
                    public void onItemListener(SubCardsTabTitleBean resultBean, int position) {
                        mSubCardsTabAdapter.setSelectedIndex(position);
                    }
                });
            }
        });

        mViewModel.mCardsBeanList.observe(this, new Observer<List<CardsBean>>() {
            @Override
            public void onChanged(List<CardsBean> result) {
                mCardsAdapter = new CardsAdapter(R.layout.cards_item_layout, result);
                mBinding.mRecyclerView.setAdapter(mCardsAdapter);
            }
        });
    }
}