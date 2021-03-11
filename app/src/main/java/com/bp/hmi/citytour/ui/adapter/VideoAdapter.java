package com.bp.hmi.citytour.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.base.BaseRecyclerAdapter;
import com.bp.hmi.citytour.base.BaseRecyclerHolder;
import com.bp.hmi.citytour.bean.HomeVideoBean;
import com.bp.hmi.citytour.http.ApiService;
import com.bp.hmi.citytour.utils.GlideUtils;
import com.bp.hmi.citytour.utils.ToolUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
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
    private List<Integer> heights;

    /**
     * Initialization data.
     *
     * @param layoutResId
     * @param data
     */
    public VideoAdapter(int layoutResId, @Nullable List<HomeVideoBean.ResultBean.ItemsBean> data) {
        super(layoutResId, data);
        getRandomHeight(data);
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, HomeVideoBean.ResultBean.ItemsBean item) {
        ViewGroup.LayoutParams params = helper.getView(R.id.iv_video_pic).getLayoutParams();//得到item的LayoutParams布局参数
        params.height = heights.get(helper.getAdapterPosition());//把随机的高度赋予itemView布局
        helper.getView(R.id.iv_video_pic).setLayoutParams(params);//把params设置给itemView布局

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

    private void getRandomHeight(List<HomeVideoBean.ResultBean.ItemsBean> data) {//得到随机item的高度
        heights = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (i == 0) {
                heights.add((int) (400 + 100));
            } else if (i == 1) {
                heights.add((int) (400 + 200));
            } else if (i == 2) {
                heights.add((int) (400 + 200));
            } else if (i == 3) {
                heights.add((int) (400 + 100));
            }

        }
    }
}
