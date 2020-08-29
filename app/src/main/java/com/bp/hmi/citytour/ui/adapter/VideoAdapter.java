package com.bp.hmi.citytour.ui.adapter;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.base.BaseRecyclerAdapter;
import com.bp.hmi.citytour.base.BaseRecyclerHolder;
import com.bp.hmi.citytour.bean.VideoBean;
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
public class VideoAdapter extends BaseRecyclerAdapter<VideoBean, BaseRecyclerHolder> {

    /**
     * Initialization data.
     *
     * @param layoutResId
     * @param data
     */
    public VideoAdapter(int layoutResId, @Nullable List<VideoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, VideoBean item) {
        helper.setTag(R.id.tv_video_title, item.getTitle());
        helper.setTag(R.id.tv_video_like, item.getLikeSum());
        helper.setTag(R.id.tv_video_favorite, item.getFavoriteSum());
        helper.getView(R.id.iv_video_like).setSelected(item.isLike());
        helper.getView(R.id.iv_video_favorite).setSelected(item.isFavorite());
        GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.test_pic, helper.getView(R.id.iv_video_pic));
    }
}
