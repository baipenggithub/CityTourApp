package com.bp.hmi.citytour.ui.adapter;

import android.widget.ImageView;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseRecyclerAdapter;
import com.bp.hmi.citytour.base.BaseRecyclerHolder;
import com.bp.hmi.citytour.bean.PavilionBean;

import java.util.List;

import androidx.annotation.Nullable;


public class PavilionAdapter extends BaseRecyclerAdapter<PavilionBean, BaseRecyclerHolder>
{

    /**
     * Initialization data.
     *
     * @param layoutResId
     * @param data
     */
    public PavilionAdapter(int layoutResId, @Nullable List<PavilionBean> data)
    {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, PavilionBean item)
    {
        helper.setText(R.id.item_title, item.getTitle());
        helper.setText(R.id.item_desc, item.getDesc());
        helper.setText(R.id.item_distance, item.getDistance());
        ((ImageView) helper.getView(R.id.item_icon)).setImageResource(item.getIcon());
    }

}
