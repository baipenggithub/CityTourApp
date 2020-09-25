package com.bp.hmi.citytour.ui.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.base.BaseRecyclerAdapter;
import com.bp.hmi.citytour.base.BaseRecyclerHolder;
import com.bp.hmi.citytour.bean.ScheduleEntity;
import com.bp.hmi.citytour.utils.GlideUtils;

import java.util.List;


public class ScheduleAdapter extends BaseRecyclerAdapter<ScheduleEntity, BaseRecyclerHolder> {

    /**
     * Initialization data.
     *
     * @param layoutResId
     * @param data
     */
    public ScheduleAdapter(int layoutResId, @Nullable List<ScheduleEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, ScheduleEntity item) {
        helper.getView(R.id.start_index).setVisibility(helper.getAdapterPosition() == 0 ? View.VISIBLE : View.GONE);
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_location, item.getLocation());
        helper.setText(R.id.tv_metro_a, item.getMetroA());
        helper.setText(R.id.tv_metro_b, item.getMetroB());
        helper.setText(R.id.tv_walk_a, item.getWalkA());
        helper.setText(R.id.tv_walk_b, item.getWalkB());
        helper.setText(R.id.tv_time, item.getTimeA());
        helper.setText(R.id.tv_path_time, item.getTimeB());
        helper.setText(R.id.tv_time_value, item.getTimeValueA());
        helper.setText(R.id.tv_path_time_value, item.getTimeValueB());

        GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), item.getIcon(), helper.getView(R.id.iv_icon));
    }

}
