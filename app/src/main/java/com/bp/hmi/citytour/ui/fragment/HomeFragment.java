package com.bp.hmi.citytour.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseFragment;
import com.bp.hmi.citytour.bean.ActivityTabBean;
import com.bp.hmi.citytour.common.CityConstant;
import com.bp.hmi.citytour.databinding.FragmentHomeBinding;
import com.bp.hmi.citytour.ui.activity.CardsActivity;
import com.bp.hmi.citytour.ui.activity.EnteredShActivity;
import com.bp.hmi.citytour.ui.activity.HomeActActivity;
import com.bp.hmi.citytour.ui.activity.MapActivity;
import com.bp.hmi.citytour.ui.activity.NewActivityDetailsActivity;
import com.bp.hmi.citytour.ui.activity.PavilionActivity;
import com.bp.hmi.citytour.ui.activity.ShoppingActivity;
import com.bp.hmi.citytour.ui.activity.SportsActivity;
import com.bp.hmi.citytour.ui.activity.TravelActivity;
import com.bp.hmi.citytour.ui.activity.VideoActivity;
import com.bp.hmi.citytour.ui.activity.WebViewActivity;
import com.bp.hmi.citytour.ui.adapter.HomeActItemAdapter;
import com.bp.hmi.citytour.ui.adapter.VideoAdapter;
import com.bp.hmi.citytour.ui.viewmodel.HomeViewModel;
import com.bp.hmi.citytour.utils.ToastUtils;
import com.bp.hmi.citytour.widget.EnterAnimLayout;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import anim.Anim;
import anim.AnimShiZiXingKuoZhan;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private VideoAdapter mVideoAdapter;
    private static final int REQUEST_CODE = 0;
    private HomeActItemAdapter mHomeActItemAdapter;

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

        if (null != getActivity()) {
            //活动
            mBinding.homeActivityTabView.subActivityView.rcyAct.setNestedScrollingEnabled(false);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mBinding.homeActivityTabView.subActivityView.rcyAct.setLayoutManager(linearLayoutManager);

            //视频
            mBinding.homeActivityTabView.subVideoView.mRecyclerView.setNestedScrollingEnabled(false);
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mBinding.homeActivityTabView.subVideoView.mRecyclerView.setLayoutManager(manager);
        }

        mViewModel.uiChangeObservable.homeTabEvent.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                if (o.equals("云展览")) {
                    Intent in = new Intent(getActivity(), MapActivity.class);
                    startActivity(in);
                } else if (o.equals("云景点")) {
                    Intent in = new Intent(getActivity(), TravelActivity.class);
                    startActivity(in);
                } else if (o.equals("云探店")) {
//                    Intent in = new Intent(getActivity(), SportsActivity.class);
//                    startActivity(in);
                    Intent in = new Intent(getActivity(), VideoActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(CityConstant.PARAMETER_PASSING_KEY, mViewModel.mVideoData.getValue());
                    in.putExtras(mBundle);
                    startActivity(in);
                } else if (o.equals("云购物")) {
                    Intent in = new Intent(getActivity(), ShoppingActivity.class);
                    startActivity(in);
                } else if (o.equals("守护生灵")) {
                    gotoIWXAPI();
                } else if (o.equals("红色足迹")) {
                    ToastUtils.showLong("敬请期待!");
                } else if (o.equals("节气民俗")) {
                    gotoWebView((String) o);
                } else if (o.equals("姓氏宗谱")) {
                    gotoWebView((String) o);
                }
            }
        });
    }

    private void gotoIWXAPI() {
        String appId = "wxa1fc1eb63bd1c06d";
        IWXAPI api = WXAPIFactory.createWXAPI(getActivity(), appId);
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        // 小程序原始id
        req.userName = "gh_5822c1dbf5fc";
        req.path = "";
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;
        api.sendReq(req);
    }


    private void gotoWebView(String o) {
        Bundle bundle = new Bundle();
        bundle.putString(CityConstant.PARAMETER_PASSING_KEY, o);
        Intent in = new Intent(getActivity(), WebViewActivity.class);
        in.putExtras(bundle);
        startActivity(in);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        //视频数据
        mViewModel.mVideoData.observe(this, itemsBeans -> {
            mVideoAdapter = new VideoAdapter(R.layout.video_item_layout, itemsBeans.getResult().getItems());
            mBinding.homeActivityTabView.subVideoView.mRecyclerView.setAdapter(mVideoAdapter);
        });

        //走进上海
        mViewModel.uiChangeObservable.intoShEvent.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {

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
                mBinding.homeNestedScrollView.scrollTo(0, 0);
                mBinding.ivShowUserInfo.setVisibility(View.GONE);
                mBinding.homeSecondHeadView.getRoot().setVisibility(View.GONE);
                mBinding.homeFirstHeadView.getRoot().setVisibility(View.VISIBLE);


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
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(CityConstant.PARAMETER_PASSING_KEY, mViewModel.mVideoData.getValue());
                in.putExtras(mBundle);
                startActivity(in);
            }
        });

        //更多活动
        mViewModel.uiChangeObservable.homeActivityMoreEvent.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Intent in = new Intent(getActivity(), HomeActActivity.class);
                startActivity(in);
            }
        });

        mViewModel.mActivityData.observe(this, activityTabBean -> {
            hideProgress();
            mBinding.homeActivityTabView.subActivityView.tvHomeActivitySum.setText(" (" + String.valueOf(activityTabBean.getResult().getTotalCount()) + " )");
            mHomeActItemAdapter = new HomeActItemAdapter(R.layout.home_act_item, activityTabBean.getResult().getItems());
            mBinding.homeActivityTabView.subActivityView.rcyAct.setAdapter(mHomeActItemAdapter);
            mHomeActItemAdapter.addOnItemClickListener(new HomeActItemAdapter.OnItemClickListener() {
                @Override
                public void onItemListener(ActivityTabBean.ResultBean.ItemsBean itemsBean, int position) {
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(CityConstant.PARAMETER_PASSING_KEY, itemsBean);
                    Intent in = new Intent(getActivity(), NewActivityDetailsActivity.class);
                    in.putExtras(mBundle);
                    startActivity(in);
                }
            });
        });

        mViewModel.uiChangeObservable.intoAnim.observe(this, o -> {
            EnterAnimLayout view1 = mBinding.homeSecondHeadView.mEnterAnimLayout;
            Anim anim1 = new AnimShiZiXingKuoZhan(view1);
            anim1.startAnimation(1000);
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
