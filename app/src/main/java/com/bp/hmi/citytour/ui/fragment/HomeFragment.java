package com.bp.hmi.citytour.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseFragment;
import com.bp.hmi.citytour.bean.VideoBean;
import com.bp.hmi.citytour.databinding.FragmentHomeBinding;
import com.bp.hmi.citytour.ui.activity.CardsActivity;
import com.bp.hmi.citytour.ui.adapter.VideoAdapter;
import com.bp.hmi.citytour.ui.viewmodel.HomeViewModel;

import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private VideoAdapter mVideoAdapter;

    public static HomeFragment getInstance() {
        return new HomeFragment();
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
        mBinding.homeActivityTabView.subVideoView.mRecyclerView.setNestedScrollingEnabled(false);
        if (null != getActivity()) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            mBinding.homeActivityTabView.subVideoView.mRecyclerView.setLayoutManager(gridLayoutManager);

        }
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        mViewModel.mVideoData.observe(this, new Observer<List<VideoBean>>() {
            @Override
            public void onChanged(List<VideoBean> videoBeans) {
                mVideoAdapter = new VideoAdapter(R.layout.video_item_layout, videoBeans);
                mBinding.homeActivityTabView.subVideoView.mRecyclerView.setAdapter(mVideoAdapter);
            }
        });

        mViewModel.uiChangeObservable.intoShEvent.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Intent in = new Intent(getActivity(), CardsActivity.class);
                startActivity(in);
            }
        });
    }

}
