package com.bp.hmi.citytour.ui.adapter;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.base.BaseRecyclerAdapter;
import com.bp.hmi.citytour.base.BaseRecyclerHolder;
import com.bp.hmi.citytour.bean.HomeVideoBean;
import com.bp.hmi.citytour.utils.GlideUtils;

import java.util.List;

/**
 * <pre>
 *     author : BaiPengMac
 *     e-mail : xxx@xx
 *     time   : 2020/08/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class VideoAdapter extends BaseRecyclerAdapter<HomeVideoBean.ResultBean.ItemsBean, BaseRecyclerHolder> {


    /**
     * Initialization data.
     *
     * @param layoutResId
     * @param data
     */
    public VideoAdapter(int layoutResId, @Nullable List<HomeVideoBean.ResultBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, HomeVideoBean.ResultBean.ItemsBean item) {
        helper.setText(R.id.tv_video_title, item.getName());
        helper.setText(R.id.tv_video_like, item.getLikeSum() + "w");
        helper.setText(R.id.tv_video_favorite, item.getFavoriteSum() + "w");
        helper.getView(R.id.iv_video_like).setSelected(item.isLike());
        helper.getView(R.id.iv_video_favorite).setSelected(item.isFavorite());
        GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), item.getId(), helper.getView(R.id.iv_video_pic));
    }
}
