package com.bp.hmi.citytour.ui.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.base.BaseRecyclerAdapter;
import com.bp.hmi.citytour.base.BaseRecyclerHolder;
import com.bp.hmi.citytour.bean.ActivityTabBean;
import com.bp.hmi.citytour.http.ApiService;
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
public class HomeActItemAdapter extends BaseRecyclerAdapter<ActivityTabBean.ResultBean.ItemsBean, BaseRecyclerHolder> {

    /**
     * Initialization data.
     *
     * @param layoutResId
     * @param data
     */
    public HomeActItemAdapter(int layoutResId, @Nullable List<ActivityTabBean.ResultBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, ActivityTabBean.ResultBean.ItemsBean item) {
        helper.setText(R.id.tv_home_activity_name, item.getName());
        helper.setText(R.id.tv_home_activity_message, item.getSummary());
        GlideUtils.loadCircleImage_15(BaseApplication.getApplication(), ApiService.HOME_API + item.getCover(), helper.getView(R.id.iv_home_activity_cover));
        helper.itemView.setOnClickListener(new ClickListener(item, helper.getAdapterPosition()));
    }

    public class ClickListener implements View.OnClickListener {
        private ActivityTabBean.ResultBean.ItemsBean mCardsBean;
        private int mPosition;

        public ClickListener(ActivityTabBean.ResultBean.ItemsBean resultBean, int position) {
            this.mCardsBean = resultBean;
            this.mPosition = position;
        }

        @Override
        public void onClick(View v) {
            if (null != mOnClickListener) {
                mOnClickListener.onItemListener(mCardsBean, mPosition);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemListener(ActivityTabBean.ResultBean.ItemsBean itemsBean, int position);
    }

    private OnItemClickListener mOnClickListener;

    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnClickListener = onItemClickListener;
    }
}
