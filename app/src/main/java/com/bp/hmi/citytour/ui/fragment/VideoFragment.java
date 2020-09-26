package com.bp.hmi.citytour.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseFragment;
import com.bp.hmi.citytour.databinding.FragmentVodeoBinding;
import com.bp.hmi.citytour.ui.adapter.SubTabTitleAdapter;
import com.bp.hmi.citytour.ui.adapter.Tiktok2Adapter;
import com.bp.hmi.citytour.ui.viewmodel.VideoViewModel;
import com.bp.hmi.citytour.utils.PreloadManager;
import com.bp.hmi.citytour.utils.VideoUtils;
import com.bp.hmi.citytour.widget.TikTokController;
import com.bp.hmi.citytour.widget.VerticalViewPager;
import com.dueeeke.videoplayer.player.VideoView;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends BaseFragment<FragmentVodeoBinding, VideoViewModel> {
    private static final String TAG = VideoFragment.class.getSimpleName();
    private int mCurPos;
    private List<String> mVideoList = new ArrayList<>();
    private Tiktok2Adapter mTiktok2Adapter;
    private TikTokController mController;
    private VideoView mVideoView;
    private PreloadManager mPreloadManager;

    private SubTabTitleAdapter mSubTabTitleAdapter;

    public static VideoFragment getInstance() {
        return new VideoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_vodeo;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        addData();
    }

    @Override
    public void initLayout() {
        super.initLayout();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.subRecyclerView.setLayoutManager(linearLayoutManager);

        initViewPager();
        initVideoView();

        mPreloadManager = PreloadManager.getInstance(getActivity());
        mBinding.verticalViewPager.setCurrentItem(0);
        mBinding.verticalViewPager.post(new Runnable() {
            @Override
            public void run() {
                startPlay(0);
            }
        });

    }

    private void initVideoView() {
        if (null == getActivity()) {
            return;
        }
        mVideoView = new VideoView(getActivity());
        mVideoView.setLooping(true);

        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_DEFAULT);

        mController = new TikTokController(getActivity());
        mVideoView.setVideoController(mController);
    }

    private void initViewPager() {

        mBinding.verticalViewPager.setOffscreenPageLimit(4);
        mTiktok2Adapter = new Tiktok2Adapter(mVideoList);
        mBinding.verticalViewPager.setAdapter(mTiktok2Adapter);
        mBinding.verticalViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mBinding.verticalViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            private int mCurItem;

            /**
             * VerticalViewPager是否反向滑动
             */
            private boolean mIsReverseScroll;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position == mCurItem) {
                    return;
                }
                mIsReverseScroll = position < mCurItem;
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == mCurPos)
                    return;
                startPlay(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == VerticalViewPager.SCROLL_STATE_DRAGGING) {
                    mCurItem = mBinding.verticalViewPager.getCurrentItem();
                }

                if (state == VerticalViewPager.SCROLL_STATE_IDLE) {
                    mPreloadManager.resumePreload(mCurPos, mIsReverseScroll);
                } else {
                    mPreloadManager.pausePreload(mCurPos, mIsReverseScroll);
                }
            }
        });
    }

    private void startPlay(int position) {
        int count = mBinding.verticalViewPager.getChildCount();
        for (int i = 0; i < count; i++) {
            View itemView = mBinding.verticalViewPager.getChildAt(i);
            Tiktok2Adapter.ViewHolder viewHolder = (Tiktok2Adapter.ViewHolder) itemView.getTag();
            if (viewHolder.mPosition == position) {
                mVideoView.release();
                VideoUtils.removeViewFormParent(mVideoView);
                String tiktokBean = mVideoList.get(position);
                String playUrl = mPreloadManager.getPlayUrl(tiktokBean);
                mVideoView.setUrl(tiktokBean);
                mController.addControlComponent(viewHolder.mTikTokView, true);
                viewHolder.mPlayerContainer.addView(mVideoView, 0);
                mVideoView.start();
                mCurPos = position;
                break;
            }
        }
    }

    private void addData() {
        String url1 = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.test;
        String url2 = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.test;
        String url3 = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.test;
        String url4 = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.test;

        mVideoList.add(url1);
        mVideoList.add(url2);
        mVideoList.add(url3);
        mVideoList.add(url4);
    }


    @Override
    public void onResume() {
        super.onResume();
//        if (mVideoView != null) {
//            mVideoView.resume();
//        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("onHiddenChanged","onHiddenChanged:"+hidden);
        if (hidden){
            if (mVideoView != null) {
                mVideoView.pause();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.release();
        }
        mPreloadManager.removeAllPreloadTask();
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
    }
}
