package com.bp.hmi.citytour.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.base.BaseFragment;
import com.bp.hmi.citytour.databinding.FragmentVodeoBinding;
import com.bp.hmi.citytour.ui.viewmodel.HomeViewModel;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

public class VideoFragment extends BaseFragment<FragmentVodeoBinding, HomeViewModel> {
    private static final String TAG = VideoFragment.class.getSimpleName();

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

    }

    @Override
    public void initLayout() {
        super.initLayout();
        if (null != getActivity()) {
            String url =  RawResourceDataSource.buildRawResourceUri(R.raw.test1).toString();
            Log.e("url","url:"+url);
           // String url = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.test1;
            //注意，用ijk模式播放raw视频，这个必须打开
            GSYVideoManager.instance().enableRawPlay(BaseApplication.getApplication());
            mBinding.homeVideoView.setUp(url, false, "测试视频");
        }

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBinding.homeVideoView.onVideoPause();
    }

    @Override
    public void onResume() {
        super.onResume();
       // mBinding.homeVideoView.onVideoResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}
