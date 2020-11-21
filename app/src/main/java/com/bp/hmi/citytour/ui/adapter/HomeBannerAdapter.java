package com.bp.hmi.citytour.ui.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.utils.GlideUtils;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

/**
 * 自定义布局，图片
 */
public class HomeBannerAdapter extends BannerAdapter<Integer, BannerHolder> {
    private boolean isRadius;

    public HomeBannerAdapter(List<Integer> mDatas, boolean isRadius) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
        this.isRadius = isRadius;
    }


    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) BannerUtils.getView(parent, R.layout.banner_image);
        return new BannerHolder(imageView);
    }

    @Override
    public void onBindView(BannerHolder holder, Integer data, int position, int size) {
        if (isRadius) {
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), data, (ImageView) holder.itemView, 30);
        } else {
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), data, (ImageView) holder.itemView, 0);
        }
    }

}
