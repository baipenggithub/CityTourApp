package com.bp.hmi.citytour.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.base.BaseRecyclerAdapter;
import com.bp.hmi.citytour.base.BaseRecyclerHolder;
import com.bp.hmi.citytour.bean.HomeVideoBean;
import com.bp.hmi.citytour.http.ApiService;
import com.bp.hmi.citytour.utils.GlideUtils;
import com.bp.hmi.citytour.utils.ToolUtils;

import java.util.List;
import java.util.Random;

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
        Random random = new Random();
        ViewGroup.LayoutParams layoutParams = helper.getView(R.id.iv_video_pic).getLayoutParams();
        layoutParams.height = random.nextInt(400) + 400;
        helper.getView(R.id.iv_video_pic).setLayoutParams(layoutParams);
        helper.setText(R.id.tv_video_title, item.getName());
        helper.setText(R.id.tv_video_like, item.getCollectQty() + "w");
        helper.setText(R.id.tv_video_favorite, item.getCommentQty() + "w");
        if (ToolUtils.isOdd(helper.getAdapterPosition())) {
            helper.getView(R.id.iv_video_like).setSelected(true);
            helper.getView(R.id.iv_video_favorite).setSelected(false);
        } else {
            helper.getView(R.id.iv_video_like).setSelected(false);
            helper.getView(R.id.iv_video_favorite).setSelected(true);
        }
        GlideUtils.loadCircleImage(BaseApplication.getApplication(), ApiService.HOME_API + item.getCover(), helper.getView(R.id.iv_video_pic));
    }
}
