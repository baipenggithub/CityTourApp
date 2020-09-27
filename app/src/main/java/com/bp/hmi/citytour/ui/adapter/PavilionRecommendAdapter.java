package com.bp.hmi.citytour.ui.adapter;

import android.widget.ImageView;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseRecyclerAdapter;
import com.bp.hmi.citytour.base.BaseRecyclerHolder;
import com.bp.hmi.citytour.bean.RecommendBean;

import java.util.List;

import androidx.annotation.Nullable;


public class PavilionRecommendAdapter extends BaseRecyclerAdapter<RecommendBean, BaseRecyclerHolder>
{

    /**
     * Initialization data.
     *
     * @param layoutResId
     * @param data
     */
    public PavilionRecommendAdapter(int layoutResId, @Nullable List<RecommendBean> data)
    {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, RecommendBean item)
    {
        helper.setText(R.id.item_title, item.getTitle());
        ((ImageView) helper.getView(R.id.item_icon)).setImageResource(item.getIcon());
    }

}
