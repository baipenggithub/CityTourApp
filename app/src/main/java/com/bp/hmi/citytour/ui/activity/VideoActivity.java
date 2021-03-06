package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.databinding.ActivityVideoBinding;
import com.bp.hmi.citytour.ui.adapter.SubTabTitleAdapter;
import com.bp.hmi.citytour.ui.adapter.Tiktok2Adapter;
import com.bp.hmi.citytour.ui.viewmodel.VideoViewModel;
import com.bp.hmi.citytour.utils.Glide4Engine;
import com.bp.hmi.citytour.utils.PreloadManager;
import com.bp.hmi.citytour.utils.ToastUtils;
import com.bp.hmi.citytour.utils.VideoUtils;
import com.bp.hmi.citytour.widget.TikTokController;
import com.bp.hmi.citytour.widget.VerticalViewPager;
import com.dueeeke.videoplayer.player.VideoView;

import java.util.ArrayList;
import java.util.List;

import me.bzcoder.mediapicker.SmartMediaPicker;
import me.bzcoder.mediapicker.config.MediaPickerEnum;

@SuppressWarnings("ALL")
public class VideoActivity extends BaseActivity<ActivityVideoBinding, VideoViewModel> {

    private int mCurPos;
    private List<String> mVideoList = new ArrayList<>();
    private Tiktok2Adapter mTiktok2Adapter;
    private TikTokController mController;
    private VideoView mVideoView;
    private PreloadManager mPreloadManager;

    private SubTabTitleAdapter mSubTabTitleAdapter;
    private final String FILE_PROVIDER = "com.sharry.app.salbum.FileProvider";
    private final String RELATIVE_PATH = "SAlbum";
    private SmartMediaPicker.Builder builder;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_video;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initLayout() {
        super.initLayout();
        mBinding.ivCardsBack.setOnClickListener(view -> finish());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.subRecyclerView.setLayoutManager(linearLayoutManager);

        initViewPager();
        initVideoView();

        mPreloadManager = PreloadManager.getInstance(this);
        mBinding.verticalViewPager.setCurrentItem(0);
        mBinding.verticalViewPager.post(new Runnable() {
            @Override
            public void run() {
                startPlay(0);
            }
        });

        mBinding.videoBottomView.llAddVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.withMediaPickerType(MediaPickerEnum.CAMERA)
                        .build()
                        .show();
            }

        });

        builder = SmartMediaPicker.builder(this)
                //最大图片选择数目
                .withMaxImageSelectable(5)
                //最大视频选择数目
                .withMaxVideoSelectable(1)
                //图片选择器是否显示数字
                .withCountable(true)
                //最大视频长度
                .withMaxVideoLength(15 * 1000)
                //最大视频文件大小 单位MB
                .withMaxVideoSize(1)
                //最大图片高度 默认1920
                .withMaxHeight(1920)
                //最大图片宽度 默认1920
                .withMaxWidth(1920)
                //最大图片大小 单位MB
                .withMaxImageSize(5)
                //前置摄像头拍摄是否镜像翻转图像 默认为true 与微信一致的话为false
                .withIsMirror(false)
                //设置图片加载引擎
                .withImageEngine(new Glide4Engine());

    }


    private void initVideoView() {
        mVideoView = new VideoView(this);
        mVideoView.setLooping(true);

        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_DEFAULT);

        mController = new TikTokController(this);
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
                String url1 = "android.resource://" + getPackageName() + "/" + R.raw.test_01;
                String url2 = "android.resource://" + getPackageName() + "/" + R.raw.test_02;
                String url3 = "android.resource://" + getPackageName() + "/" + R.raw.test_03;

                mVideoList.add(url1);
                mVideoList.add(url2);
                mVideoList.add(url3);
    }


    @Override
    public void initData() {
        super.initData();
        addData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.release();
        }
        mPreloadManager.removeAllPreloadTask();
    }

    @Override
    public void onBackPressed() {
        if (mVideoView == null || !mVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        mViewModel.mSubCardsTabTitleBean.observe(this, new Observer<List<SubCardsTabTitleBean>>() {
            @Override
            public void onChanged(List<SubCardsTabTitleBean> result) {
                mSubTabTitleAdapter = new SubTabTitleAdapter(R.layout.sub_cards_tab_item_layout, result);
                mBinding.subRecyclerView.setAdapter(mSubTabTitleAdapter);
                mSubTabTitleAdapter.setSelectedIndex(0);

                mSubTabTitleAdapter.addOnItemClickListener(new SubTabTitleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemListener(SubCardsTabTitleBean resultBean, int position, View view) {
                        mSubTabTitleAdapter.setSelectedIndex(position);
                        if (resultBean.getTitle().equals("类型")) {
                            //showMenuPop(view);
                        }

                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<String> resultData = SmartMediaPicker.getResultData(this, requestCode, resultCode, data);
        if (resultData != null && resultData.size() > 0) {
            //            tvPath.setText(Arrays.toString(resultData.toArray()) + "\n文件类型："
            //                    + SmartMediaPicker.getFileType(resultData.get(0)) + "\n视频时长: " +
            //                    (SmartMediaPicker.getFileType(resultData.get(0)).contains("video") ?
            //                            SmartMediaPicker.getVideoDuration(resultData.get(0)) : ""));
            ToastUtils.showLong("上传成功:");

        } else {
            ToastUtils.showLong("上传失败");
        }
    }
}