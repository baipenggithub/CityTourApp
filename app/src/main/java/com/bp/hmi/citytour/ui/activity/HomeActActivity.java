
package com.bp.hmi.citytour.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.bean.ActivityTabBean;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.common.CityConstant;
import com.bp.hmi.citytour.databinding.ActivityCentreTabBinding;
import com.bp.hmi.citytour.http.ApiService;
import com.bp.hmi.citytour.ui.adapter.HomeCentreTabAdapter;
import com.bp.hmi.citytour.ui.adapter.SubTabTitleAdapter;
import com.bp.hmi.citytour.ui.viewmodel.HomeCentreTabViewModel;
import com.bp.hmi.citytour.utils.GlideUtils;
import com.bp.hmi.citytour.widget.pop.PopCommon;
import com.bp.hmi.citytour.widget.pop.PopModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动..
 */
public class HomeActActivity extends BaseActivity<ActivityCentreTabBinding, HomeCentreTabViewModel> {
    private SubTabTitleAdapter mSubTabTitleAdapter;
    private HomeCentreTabAdapter mHomeCentreTabAdapter;


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_centre_tab;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        showProgress();
        mViewModel.getSubCardsTitle();
        mViewModel.requestActivityInfo("");
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.subRecyclerView.setLayoutManager(linearLayoutManager);

        mBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mBinding.llAcDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTabBean.ResultBean.ItemsBean itemsBean = mViewModel.mActivityData.getValue().getResult().getItems().get(0);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(CityConstant.PARAMETER_PASSING_KEY, itemsBean);
                Intent in = new Intent(HomeActActivity.this, NewActivityDetailsActivity.class);
                in.putExtras(mBundle);
                startActivity(in);
            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        mViewModel.mSubCardsTabTitleBean.observe(this, result -> {
            mSubTabTitleAdapter = new SubTabTitleAdapter(R.layout.sub_cards_tab_item_layout, result);
            mBinding.subRecyclerView.setAdapter(mSubTabTitleAdapter);
            mSubTabTitleAdapter.setSelectedIndex(0);
            mSubTabTitleAdapter.addOnItemClickListener(new SubTabTitleAdapter.OnItemClickListener() {
                @Override
                public void onItemListener(SubCardsTabTitleBean resultBean, int position, View view) {
                    mSubTabTitleAdapter.setSelectedIndex(position);
                    if (resultBean.getTitle().equals("类型")) {
                        showMenuPop(view);
                    }

                }
            });
        });

        mViewModel.mActivityData.observe(this, activityTabBean -> {
            hideProgress();
            mHomeCentreTabAdapter = new HomeCentreTabAdapter(R.layout.centre_tab_item_layout, activityTabBean.getResult().getItems());
            mBinding.mRecyclerView.setAdapter(mHomeCentreTabAdapter);

            mBinding.tvHomeActivityName.setText(activityTabBean.getResult().getItems().get(0).getName());
            mBinding.tvHomeActivityMessage.setText(activityTabBean.getResult().getItems().get(0).getSummary());

            GlideUtils.loadCircleImage(BaseApplication.getApplication(), ApiService.HOME_API + activityTabBean.getResult().getItems().get(0).getCover(), mBinding.ivHomeActivityCover);

            mHomeCentreTabAdapter.addOnItemClickListener((itemsBean, position) -> {
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(CityConstant.PARAMETER_PASSING_KEY, itemsBean);
                Intent in = new Intent(HomeActActivity.this, NewActivityDetailsActivity.class);
                in.putExtras(mBundle);
                startActivity(in);
            });

        });
    }

    private void showMenuPop(View menuView) {


        PopModel feedPopModel = new PopModel();
        feedPopModel.setItemDesc("市民云");

        PopModel feedPopModel1 = new PopModel();
        feedPopModel1.setItemDesc("主题");

        PopModel feedPopModel2 = new PopModel();
        feedPopModel2.setItemDesc("演出");

        PopModel feedPopModel3 = new PopModel();
        feedPopModel3.setItemDesc("比赛");

        PopModel feedPopModel4 = new PopModel();
        feedPopModel4.setItemDesc("狂欢");

        PopModel feedPopModel5 = new PopModel();
        feedPopModel5.setItemDesc("夜市");

        /** 初始化数据源 **/
        final List<PopModel> list = new ArrayList<>();
        list.add(feedPopModel);
        list.add(feedPopModel1);
        list.add(feedPopModel2);
        list.add(feedPopModel3);
        list.add(feedPopModel4);
        list.add(feedPopModel5);

        PopCommon popCommon = new PopCommon(this, list, new PopCommon.OnPopCommonListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    showProgress();
                    mViewModel.requestActivityInfo("0");
                } else if (position == 1) {
                    showProgress();
                    mViewModel.requestActivityInfo("5");
                } else if (position == 2) {
                    showProgress();
                    mViewModel.requestActivityInfo("2");
                } else if (position == 3) {
                    showProgress();
                    mViewModel.requestActivityInfo("3");
                } else if (position == 4) {
                    showProgress();
                    mViewModel.requestActivityInfo("5");
                } else if (position == 5) {
                    showProgress();
                    mViewModel.requestActivityInfo("6");
                }
            }

            @Override
            public void onDismiss() {

            }
        });
        int location[] = new int[2];
        menuView.getLocationOnScreen(location);
        int x = dp2px(getApplicationContext(), 15);
        int y = location[1] + menuView.getHeight();
        /** 是否显示黑色背景，默认不显示 **/
        popCommon.setShowAplhaWindow(true);
        popCommon.showPop(menuView, x, y);

    }

    private static int dp2px(Context context, float value) {
        return (int) (context.getResources().getDisplayMetrics().density * value + 0.5);
    }
}