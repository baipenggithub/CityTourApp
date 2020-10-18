package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.bean.EnteredShBean;
import com.bp.hmi.citytour.bean.ScheduleEntity;
import com.bp.hmi.citytour.common.CityConstant;
import com.bp.hmi.citytour.databinding.ActivityEnteredShNewDetailsBinding;
import com.bp.hmi.citytour.ui.viewmodel.EnterShDetailsViewModel;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;

/**
 * 类描述:走进上海详情（新）
 * 创建人:LuoWeiDi
 * 创建时间:2020/9/26
 */
public class EnteredShNewDetailsActivity extends BaseActivity<ActivityEnteredShNewDetailsBinding, EnterShDetailsViewModel> {
    private EnteredShBean.ResultBean.ItemsBean itemsBean;
    private boolean mGroupIsOpen = false;
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_entered_sh_new_details;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initLayout() {
        super.initLayout();
        SpannableString str = new SpannableString("线路规划（可自定义）");
        str.setSpan(new AbsoluteSizeSpan(16, true), 4, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mBinding.tvPathTitle.setText(str);
        mBinding.ivCardsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBinding.rlPathContentPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoSwitch();
            }
        });
        mBinding.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EnteredShNewDetailsActivity.this, BookActivity.class));
            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        mViewModel.mActDetailsBean.observe(this, activityTabBean -> {
            hideProgress();
            //GlideUtils.loadCircleImage(BaseApplication.getApplication(), ApiService.HOME_API + activityTabBean.getResult().getCover(), mBinding.ivEnteredShAdv, 0);
            //mBinding.joinTripDetailsView.tvJoinTripAddress.setText(activityTabBean.getResult().getName());
            // mBinding.joinTripDetailsView.tvJoinTripRanking.setText(activityTabBean.getResult().getPingfen() + "分");
            //mBinding.joinTripDetailsView.tvTime.setText(activityTabBean.getResult().getTime());
            //mBinding.joinTripDetailsView.tvAddress.setText(activityTabBean.getResult().getAddress());
            //详情
            //mBinding.joinTripDetailsView.mWeb.loadDataWithBaseURL(null, activityTabBean.getResult().getContent(), "text/html", "utf-8", null);
        });
    }

    @Override
    public void initData() {
        super.initData();
        Bundle mBundle = getIntent().getExtras();
        itemsBean = (EnteredShBean.ResultBean.ItemsBean) mBundle.getSerializable(CityConstant.PARAMETER_PASSING_KEY);
        showProgress();
        mViewModel.requestActivityInfo(itemsBean.getId());

        ArrayList<ScheduleEntity> scheduleEntities = getBookData();
        mBinding.pathListA.removeAllViews();
        mBinding.pathListB.removeAllViews();
        mBinding.pathSplit.removeAllViews();
        int size = scheduleEntities.size() + 2;
        for (int i = 0; i < size; i++) {
            View itemSplit = LayoutInflater.from(EnteredShNewDetailsActivity.this).inflate(R.layout.item_details_split, mBinding.pathSplit, false);
            mBinding.pathSplit.addView(itemSplit);
            if (i < scheduleEntities.size()) {
                ScheduleEntity scheduleEntity = scheduleEntities.get(i);
                View itemPath;
                if (i % 2 == 0) {
                    itemSplit.findViewById(R.id.leftLing).setVisibility(View.INVISIBLE);
                    itemSplit.findViewById(R.id.rightLing).setVisibility(View.VISIBLE);
                    itemPath = LayoutInflater.from(EnteredShNewDetailsActivity.this).inflate(R.layout.item_details_path, mBinding.pathListA, false);
                    mBinding.pathListA.addView(itemPath);
                } else {
                    itemSplit.findViewById(R.id.leftLing).setVisibility(View.VISIBLE);
                    itemSplit.findViewById(R.id.rightLing).setVisibility(View.INVISIBLE);
                    itemPath = LayoutInflater.from(EnteredShNewDetailsActivity.this).inflate(R.layout.item_details_path, mBinding.pathListB, false);
                    mBinding.pathListB.addView(itemPath);
                }
                ((ImageView) itemPath.findViewById(R.id.pathImage)).setImageResource(scheduleEntity.getIcon());
                ((TextView) itemPath.findViewById(R.id.pathTitle)).setText(scheduleEntity.getTitle());
                ((TextView) itemPath.findViewById(R.id.pathDes)).setText(scheduleEntity.getLocation());
            } else {
                if (i == size - 2) {
                    itemSplit.findViewById(R.id.leftLing).setVisibility(View.INVISIBLE);
                    itemSplit.findViewById(R.id.rightLing).setVisibility(View.VISIBLE);
                }
                if (i == size - 1) {
                    itemSplit.findViewById(R.id.topCircle).setVisibility(View.GONE);
                    itemSplit.findViewById(R.id.addButton).setVisibility(View.VISIBLE);
                }
            }
            if (i == 0) {
                itemSplit.findViewById(R.id.topCircle).setVisibility(View.GONE);
            }
        }
    }



    public void infoSwitch() {
        float layoutHeight = BannerUtils.dp2px(200);
        Animation animation;
        if (mGroupIsOpen) {
            animation = new Animation() {
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    int v = (int) (layoutHeight * interpolatedTime);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mBinding.rlPathPage.getLayoutParams();
                    params.topMargin = -v;
                    mBinding.rlPathPage.setLayoutParams(params);
                }
            };
        } else {
            animation = new Animation() {
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    int v = (int) (layoutHeight - layoutHeight * interpolatedTime);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mBinding.rlPathPage.getLayoutParams();
                    params.topMargin = -v;
                    mBinding.rlPathPage.setLayoutParams(params);
                }
            };
        }
        mGroupIsOpen = !mGroupIsOpen;
        animation.setDuration(350);
        mBinding.rlPathPage.startAnimation(animation);
    }

    public ArrayList<ScheduleEntity> getBookData() {
        //模拟数据
        ArrayList<ScheduleEntity> list = new ArrayList<>();
        ScheduleEntity scheduleEntity1 = new ScheduleEntity();
        scheduleEntity1.setIcon(R.mipmap.item_1);
        scheduleEntity1.setTitle("中共一大会址");
        scheduleEntity1.setLocation("海兴业路76号");
        scheduleEntity1.setTimeA("08:00");
        scheduleEntity1.setTimeValueA("2小时");
        scheduleEntity1.setTimeB("10:00");
        scheduleEntity1.setTimeValueB("29分钟");
        scheduleEntity1.setWalkA("步行596米");
        scheduleEntity1.setWalkB("步行925米");
        scheduleEntity1.setMetroA("新天地站(10号线6号口)");
        scheduleEntity1.setMetroB("愚园路站(10号线3号口)");
        list.add(scheduleEntity1);
        ScheduleEntity scheduleEntity2 = new ScheduleEntity();
        scheduleEntity2.setIcon(R.mipmap.item_2);
        scheduleEntity2.setTitle("城隍庙");
        scheduleEntity2.setLocation("方浜中路249号");
        scheduleEntity2.setTimeA("10:29");
        scheduleEntity2.setTimeValueA("2小时");
        scheduleEntity2.setTimeB("12:30");
        scheduleEntity2.setTimeValueB("30分钟");
        scheduleEntity2.setWalkA("步行76米");
        scheduleEntity2.setWalkB("步行101米");
        scheduleEntity2.setMetroA("城隍庙·豫园站(都市观光旅游3号线)");
        scheduleEntity2.setMetroB("东方明珠广播电视塔");
        list.add(scheduleEntity2);
        ScheduleEntity scheduleEntity3 = new ScheduleEntity();
        scheduleEntity3.setIcon(R.mipmap.item_3);
        scheduleEntity3.setTitle("东方明珠");
        scheduleEntity3.setLocation("世纪大道1号");
        scheduleEntity3.setTimeA("13:00");
        scheduleEntity3.setTimeValueA("3小时");
        scheduleEntity3.setTimeB("14:00");
        scheduleEntity3.setTimeValueB("30分钟");
        scheduleEntity3.setWalkA("步行76米");
        scheduleEntity3.setWalkB("步行101米");
        scheduleEntity3.setMetroA("城隍庙·豫园站(都市观光旅游3号线)");
        scheduleEntity3.setMetroB("东方明珠广播电视塔");
        list.add(scheduleEntity3);

        ScheduleEntity scheduleEntity4 = new ScheduleEntity();
        scheduleEntity4.setIcon(R.mipmap.pic_1_04);
        scheduleEntity4.setTitle("四行仓库纪念馆");
        scheduleEntity4.setLocation("光复路1号");
        scheduleEntity4.setTimeA("13:00");
        scheduleEntity4.setTimeValueA("3小时");
        scheduleEntity4.setTimeB("14:00");
        scheduleEntity4.setTimeValueB("30分钟");
        scheduleEntity4.setWalkA("步行76米");
        scheduleEntity4.setWalkB("步行101米");
        scheduleEntity4.setMetroA("城隍庙·豫园站(都市观光旅游3号线)");
        scheduleEntity4.setMetroB("东方明珠广播电视塔");
        list.add(scheduleEntity4);

        ScheduleEntity scheduleEntity5 = new ScheduleEntity();
        scheduleEntity5.setIcon(R.mipmap.pic_1_05);
        scheduleEntity5.setTitle("万国建筑群");
        scheduleEntity5.setLocation("黄浦江西岸");
        scheduleEntity5.setTimeA("13:00");
        scheduleEntity5.setTimeValueA("3小时");
        scheduleEntity5.setTimeB("14:00");
        scheduleEntity5.setTimeValueB("30分钟");
        scheduleEntity5.setWalkA("步行76米");
        scheduleEntity5.setWalkB("步行101米");
        scheduleEntity5.setMetroA("城隍庙·豫园站(都市观光旅游3号线)");
        scheduleEntity5.setMetroB("东方明珠广播电视塔");
        list.add(scheduleEntity5);

        ScheduleEntity scheduleEntity6 = new ScheduleEntity();
        scheduleEntity6.setIcon(R.mipmap.pic_1_06);
        scheduleEntity6.setTitle("龙华烈士陵园");
        scheduleEntity6.setLocation("天钥桥路1501号");
        scheduleEntity6.setTimeA("13:00");
        scheduleEntity6.setTimeValueA("3小时");
        scheduleEntity6.setTimeB("14:00");
        scheduleEntity6.setTimeValueB("30分钟");
        scheduleEntity6.setWalkA("步行76米");
        scheduleEntity6.setWalkB("步行101米");
        scheduleEntity6.setMetroA("城隍庙·豫园站(都市观光旅游3号线)");
        scheduleEntity6.setMetroB("东方明珠广播电视塔");
        list.add(scheduleEntity6);
        return list;
    }
}
