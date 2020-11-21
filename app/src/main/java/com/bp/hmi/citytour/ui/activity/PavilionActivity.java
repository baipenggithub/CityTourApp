package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.bean.PavilionBean;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.databinding.ActivityPavilionBinding;
import com.bp.hmi.citytour.ui.adapter.PavilionAdapter;
import com.bp.hmi.citytour.ui.adapter.SubTabTitleAdapter;
import com.bp.hmi.citytour.ui.viewmodel.PavilionViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:展馆
 * 创建人:LuoWeiDi
 * 创建时间:2020/9/26
 */
public class PavilionActivity extends BaseActivity<ActivityPavilionBinding, PavilionViewModel> {
    private int mContentType = 0;
    private SubTabTitleAdapter mSubTabTitleAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_pavilion;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initLayout() {
        super.initLayout();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.subRecyclerView.setLayoutManager(linearLayoutManager);
        List<SubCardsTabTitleBean> subCardsTitle = getSubCardsTitle();
        mSubTabTitleAdapter = new SubTabTitleAdapter(R.layout.sub_cards_tab_item_layout, subCardsTitle);
        mBinding.subRecyclerView.setAdapter(mSubTabTitleAdapter);
        mSubTabTitleAdapter.setSelectedIndex(0);
        mSubTabTitleAdapter.addOnItemClickListener(new SubTabTitleAdapter.OnItemClickListener() {
            @Override
            public void onItemListener(SubCardsTabTitleBean resultBean, int position, View view) {
                mSubTabTitleAdapter.setSelectedIndex(position);
                if (resultBean.getTitle().equals("类型")) {
                   // showMenuPop(view);
                }

            }
        });

        mBinding.ivCardsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBinding.linImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentTypeSwitch(0);
            }
        });

        mBinding.linHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentTypeSwitch(1);
            }
        });
        //默认选中展览
        contentTypeSwitch(0);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
    }

    @Override
    public void initData() {
        super.initData();
    }


    public void contentTypeSwitch(int type) {
        mContentType = type;
        ((TextView) findViewById(R.id.title)).setText(mContentType == 0 ? "展 览" : "展 馆");
        ((ImageView) findViewById(R.id.iv_home)).setImageResource(mContentType == 0 ?
                R.mipmap.tour_pavilion_image :
                R.mipmap.tour_pavilion_up_image);


        ((TextView) findViewById(R.id.tv_home)).setTextColor(
                ContextCompat.getColor(this, mContentType == 0 ?
                        R.color.top_tab_bar_light : R.color.light_grey));

        ((ImageView) findViewById(R.id.iv_travel)).setImageResource(
                mContentType == 0 ? R.mipmap.tour_pavilion_up_home :
                        R.mipmap.tour_pavilion_home);

        ((TextView) findViewById(R.id.tv_travel)).setTextColor(
                ContextCompat.getColor(this, mContentType == 0 ?
                        R.color.light_grey : R.color.top_tab_bar_light));


        if (mContentType == 0) {
            mBinding.nsv.fullScroll(ScrollView.FOCUS_UP);
            mBinding.ivCityTourPavilion.setImageResource(R.mipmap.city_tour_pavilion_up_bac);

            LinearLayoutManager managerCircle = new LinearLayoutManager(this);
            managerCircle.setOrientation(LinearLayoutManager.VERTICAL);
            mBinding.listPavilion.setLayoutManager(managerCircle);
            PavilionAdapter mScheduleAdapter = new PavilionAdapter(R.layout.item_pavilion_exhibition, getPavilionData());
            mBinding.listPavilion.setAdapter(mScheduleAdapter);
            mScheduleAdapter.addOnItemClickListener((resultBean, position) -> {
                Intent i = new Intent(PavilionActivity.this, PavilionHallDetailsActivity.class);
                i.putExtra("type", mContentType);
                startActivity(i);
            });

        } else {
            mBinding.nsv.fullScroll(ScrollView.FOCUS_UP);
            mBinding.ivCityTourPavilion.setImageResource(R.mipmap.pic_01_07);
            GridLayoutManager manager = new GridLayoutManager(this, 2);
            mBinding.listPavilion.setLayoutManager(manager);
            PavilionAdapter mScheduleAdapter = new PavilionAdapter(R.layout.item_pavilion_exhibition_hall, getPavilionHallData());
            mBinding.listPavilion.setAdapter(mScheduleAdapter);

            mScheduleAdapter.addOnItemClickListener((resultBean, position) -> {
                Intent i = new Intent(PavilionActivity.this, PavilionHallDetailsActivity.class);
                i.putExtra("type", mContentType);
                startActivity(i);
            });
        }

    }

    //模拟数据
    public List<SubCardsTabTitleBean> getSubCardsTitle() {
        List<SubCardsTabTitleBean> subCardsList = new ArrayList<>();

        SubCardsTabTitleBean subCardsTitle1 = new SubCardsTabTitleBean();
        subCardsTitle1.setTitle("全部");
        subCardsList.add(subCardsTitle1);

        SubCardsTabTitleBean subCardsTitle2 = new SubCardsTabTitleBean();
        subCardsTitle2.setTitle("最近");
        subCardsList.add(subCardsTitle2);

        SubCardsTabTitleBean subCardsTitle3 = new SubCardsTabTitleBean();
        subCardsTitle3.setTitle("最新");
        subCardsList.add(subCardsTitle3);

        SubCardsTabTitleBean subCardsTitle4 = new SubCardsTabTitleBean();
        subCardsTitle4.setTitle("热门");
        subCardsList.add(subCardsTitle4);

        SubCardsTabTitleBean subCardsTitle5 = new SubCardsTabTitleBean();
        subCardsTitle5.setTitle("即将结束");
        subCardsList.add(subCardsTitle5);
        return subCardsList;
    }

    //模拟数据
    public List<PavilionBean> getPavilionData() {
        ArrayList<PavilionBean> pavilionBeans = new ArrayList<>();
        pavilionBeans.add(new PavilionBean("中元画展：纪念古元诞辰百年", "中央美术学院美术馆", "距您 57米", R.mipmap.pic_01));
        pavilionBeans.add(new PavilionBean("2020再写刘海棠", "刘海棠公馆", "距您 67米", R.mipmap.pic_02));
        pavilionBeans.add(new PavilionBean("心不为行，从孤舟草堂到黄浦江", "东方明珠电视台", "距您 87", R.mipmap.pic_03));
        pavilionBeans.add(new PavilionBean("九月再登明珠塔", "塔顶会客厅", "距您 45", R.mipmap.pic_01_01));
        pavilionBeans.add(new PavilionBean("黄浦江岸迎风起", "江岸展览馆", "距您 20", R.mipmap.pic_01_02));
        pavilionBeans.add(new PavilionBean("城隍庙伦", "庙堂大厅", "距您 80", R.mipmap.pic_01_03));
        return pavilionBeans;
    }

    //模拟数据
    public List<PavilionBean> getPavilionHallData() {
        ArrayList<PavilionBean> pavilionBeans = new ArrayList<>();
        pavilionBeans.add(new PavilionBean("中央美术馆", "￥ 免费", "距您 20米", R.mipmap.pic_01_01));
        pavilionBeans.add(new PavilionBean("刘海棠公馆", "￥30.00", "距您 30米", R.mipmap.pic_01_02));
        pavilionBeans.add(new PavilionBean("东方明珠电视台", "￥ 60.00", "距您 87", R.mipmap.pic_01_03));
        pavilionBeans.add(new PavilionBean("塔顶会客厅", "￥ 70.00", "距您 45", R.mipmap.pic_01_04));
        pavilionBeans.add(new PavilionBean("江岸展览馆", "￥ 40.00", "距您 20", R.mipmap.pic_01_05));
        pavilionBeans.add(new PavilionBean("庙堂大厅", "￥ 50.00", "距您 80", R.mipmap.pic_01_06));
        return pavilionBeans;
    }
}
