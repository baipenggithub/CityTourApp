package com.bp.hmi.citytour.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseFragment;
import com.bp.hmi.citytour.databinding.FragmentShRoundBinding;
import com.bp.hmi.citytour.ui.activity.EnteredShDetailsActivity;
import com.bp.hmi.citytour.ui.adapter.EnteredShAdapter;
import com.bp.hmi.citytour.ui.adapter.HomeBannerAdapter;
import com.bp.hmi.citytour.ui.adapter.SubTabTitleAdapter;
import com.bp.hmi.citytour.ui.viewmodel.EnteredShViewModel;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 推荐路线
 */
public class EnteredShRoundFragment extends BaseFragment<FragmentShRoundBinding, EnteredShViewModel> {
    private static final String TAG = EnteredShRoundFragment.class.getSimpleName();
    private List<Integer> mBannerData = new ArrayList<>();

    private SubTabTitleAdapter mSubTabTitleAdapter;
    private EnteredShAdapter mEnteredShAdapter;

    public static EnteredShRoundFragment getInstance() {
        return new EnteredShRoundFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_sh_round;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();

        mBannerData.add(R.mipmap.pic_1_07);
        mBannerData.add(R.mipmap.pic_1_08);
        mBannerData.add(R.mipmap.pic_1_09);
        mBannerData.add(R.mipmap.pic_1_10);
        mBannerData.add(R.mipmap.pic_1_11);

        showProgress();
        mViewModel.getSubCardsTitle();
        mViewModel.getCardsList();
    }

    @Override
    public void initLayout() {
        super.initLayout();

        //广告
        mBinding.banner.setAdapter(new HomeBannerAdapter(mBannerData));
        mBinding.banner.setIndicator(new CircleIndicator(getActivity()));
        mBinding.banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
        mBinding.banner.setIndicatorMargins(new IndicatorConfig.Margins(0, 0,
                BannerConfig.INDICATOR_MARGIN, (int) BannerUtils.dp2px(12)));
        mBinding.banner.isAutoLoop(false);
        mBinding.banner.addBannerLifecycleObserver(this);

        // 列表
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.subRecyclerView.setLayoutManager(linearLayoutManager);

        mBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        mViewModel.mSubCardsTabTitleBean.observe(this, result -> {
            mSubTabTitleAdapter = new SubTabTitleAdapter(R.layout.sub_cards_tab_item_layout, result);
            mBinding.subRecyclerView.setAdapter(mSubTabTitleAdapter);
            mSubTabTitleAdapter.setSelectedIndex(0);

            mSubTabTitleAdapter.addOnItemClickListener((resultBean, position) -> mSubTabTitleAdapter.setSelectedIndex(position));
        });

        mViewModel.mEnteredShBeanList.observe(this, enteredShBean -> {
            hideProgress();
            mEnteredShAdapter = new EnteredShAdapter(R.layout.entered_sh_item_layout, enteredShBean.getResult().getItems());
            mBinding.mRecyclerView.setAdapter(mEnteredShAdapter);

            mEnteredShAdapter.addOnItemClickListener((itemsBean, position) -> {
                Intent in = new Intent(getActivity(), EnteredShDetailsActivity.class);
                startActivity(in);
            });
        });
    }
}
