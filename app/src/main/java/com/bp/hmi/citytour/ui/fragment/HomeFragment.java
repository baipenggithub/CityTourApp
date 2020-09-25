package com.bp.hmi.citytour.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.base.BaseFragment;
import com.bp.hmi.citytour.databinding.FragmentHomeBinding;
import com.bp.hmi.citytour.http.ApiService;
import com.bp.hmi.citytour.ui.activity.CardsActivity;
import com.bp.hmi.citytour.ui.activity.EnteredShActivity;
import com.bp.hmi.citytour.ui.activity.HomeCentreTabActivity;
import com.bp.hmi.citytour.ui.activity.VideoActivity;
import com.bp.hmi.citytour.ui.adapter.VideoAdapter;
import com.bp.hmi.citytour.ui.viewmodel.HomeViewModel;
import com.bp.hmi.citytour.utils.GlideUtils;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private VideoAdapter mVideoAdapter;
    private static final int REQUEST_CODE = 0;

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
        showProgress();
        mViewModel.requestActivityInfo();
    }

    @Override
    public void initLayout() {
        super.initLayout();
        mBinding.homeActivityTabView.subVideoView.mRecyclerView.setNestedScrollingEnabled(false);
        if (null != getActivity()) {
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
            mBinding.homeActivityTabView.subVideoView.mRecyclerView.setLayoutManager(manager);
        }

        mViewModel.uiChangeObservable.homeTabEvent.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                if (o.equals("消费券")) {
                    Intent in = new Intent(getActivity(), CardsActivity.class);
                    startActivity(in);
                } else {
                    Intent in = new Intent(getActivity(), HomeCentreTabActivity.class);
                    Bundle b = new Bundle();
                    b.putString("HOME_TYPE", (String) o);
                    in.putExtras(b);
                    startActivity(in);
                }

            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        mViewModel.mVideoData.observe(this, itemsBeans -> {
            mVideoAdapter = new VideoAdapter(R.layout.video_item_layout, itemsBeans);
            mBinding.homeActivityTabView.subVideoView.mRecyclerView.setAdapter(mVideoAdapter);
        });

        //走进上海
        mViewModel.uiChangeObservable.intoShEvent.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                //
                Intent in = new Intent(getActivity(), EnteredShActivity.class);
                startActivity(in);
            }
        });

        // 查看用户信息
        mViewModel.uiChangeObservable.showUserEvent.observe(this, o -> {
            mBinding.homeFirstHeadView.getRoot().setVisibility(View.GONE);
            mBinding.homeSecondHeadView.getRoot().setVisibility(View.VISIBLE);
            mBinding.ivShowUserInfo.setVisibility(View.VISIBLE);
        });

        // 隐藏用户信息
        mViewModel.uiChangeObservable.hideUserEvent.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                mBinding.ivShowUserInfo.setVisibility(View.GONE);
                mBinding.homeSecondHeadView.getRoot().setVisibility(View.GONE);
                mBinding.homeFirstHeadView.getRoot().setVisibility(View.VISIBLE);

                mBinding.homeNestedScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        //扫描
        mViewModel.uiChangeObservable.scanQrEvent.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        //消费券
        mViewModel.uiChangeObservable.homeCardEvent.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Intent in = new Intent(getActivity(), CardsActivity.class);
                startActivity(in);
            }
        });

        //更多视频
        mViewModel.uiChangeObservable.homeVideoEvent.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Intent in = new Intent(getActivity(), VideoActivity.class);
                startActivity(in);
            }
        });

        //更多活动
        mViewModel.uiChangeObservable.homeActivityMoreEvent.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Intent in = new Intent(getActivity(), HomeCentreTabActivity.class);
                Bundle b = new Bundle();
                b.putString("HOME_TYPE", "活动");
                in.putExtras(b);
                startActivity(in);
            }
        });

        mViewModel.mActivityData.observe(this, activityTabBean -> {
            hideProgress();
            mBinding.homeActivityTabView.subActivityView.tvHomeActivitySum.setText(" (" + String.valueOf(activityTabBean.getResult().getTotalCount()) + " )");
            mBinding.homeActivityTabView.subActivityView.tvHomeActivityName.setText(activityTabBean.getResult().getItems().get(3).getName());
            mBinding.homeActivityTabView.subActivityView.tvHomeActivityMessage.setText(activityTabBean.getResult().getItems().get(3).getSummary());
            GlideUtils.loadCircleImage_10(BaseApplication.getApplication(), ApiService.HOME_API + activityTabBean.getResult().getItems().get(3).getCover(), mBinding.homeActivityTabView.subActivityView.ivHomeActivityCover);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                }
            }
        }
    }
}
