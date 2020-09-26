package com.bp.hmi.citytour.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.databinding.ActivityPavilionBinding;
import com.bp.hmi.citytour.ui.adapter.SubTabTitleAdapter;
import com.bp.hmi.citytour.ui.viewmodel.PavilionViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * 类描述:展馆
 * 创建人:LuoWeiDi
 * 创建时间:2020/9/26
 */
public class PavilionActivity extends BaseActivity<ActivityPavilionBinding, PavilionViewModel>
{
    private int mContentType = 0;

    @Override
    public int initContentView(Bundle savedInstanceState)
    {
        return R.layout.activity_pavilion;
    }

    @Override
    public int initVariableId()
    {
        return BR.viewModel;
    }


    @Override
    public void initLayout()
    {
        super.initLayout();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.subRecyclerView.setLayoutManager(linearLayoutManager);
        List<SubCardsTabTitleBean> subCardsTitle       = getSubCardsTitle();
        SubTabTitleAdapter         mSubTabTitleAdapter = new SubTabTitleAdapter(R.layout.sub_cards_tab_item_layout, subCardsTitle);
        mBinding.subRecyclerView.setAdapter(mSubTabTitleAdapter);
        mSubTabTitleAdapter.setSelectedIndex(0);
        mSubTabTitleAdapter.addOnItemClickListener(new SubTabTitleAdapter.OnItemClickListener()
        {
            @Override
            public void onItemListener(SubCardsTabTitleBean resultBean, int position)
            {
                mSubTabTitleAdapter.setSelectedIndex(position);
            }
        });

        mBinding.linImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                contentTypeSwitch(0);
            }
        });

        mBinding.linHall.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                contentTypeSwitch(1);
            }
        });
    }

    @Override
    public void initViewObservable()
    {
        super.initViewObservable();
    }

    @Override
    public void initData()
    {
        super.initData();
    }


    public void contentTypeSwitch(int type)
    {
        mContentType = type;

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
    }

    //模拟数据
    public List<SubCardsTabTitleBean> getSubCardsTitle()
    {
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
}
