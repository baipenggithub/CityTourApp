package com.bp.hmi.citytour.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseRecyclerAdapter;
import com.bp.hmi.citytour.base.BaseRecyclerHolder;
import com.bp.hmi.citytour.bean.MarkerListBean;
import com.bp.hmi.citytour.bean.PavilionBean;

import java.util.List;
import java.util.Random;


public class MarkerAdapter extends BaseRecyclerAdapter<MarkerListBean, BaseRecyclerHolder> {

    /**
     * Initialization data.
     *
     * @param layoutResId
     * @param data
     */
    public MarkerAdapter(int layoutResId, @Nullable List<MarkerListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, MarkerListBean item) {

        helper.setText(R.id.tv_drawer_url, (helper.getAdapterPosition() + 1) + "、" + item.getSubTitle());
        helper.itemView.setOnClickListener(new ClickListener(item));
    }

    public class ClickListener implements View.OnClickListener {
        private final MarkerListBean mCardsBean;

        public ClickListener(MarkerListBean resultBean) {
            this.mCardsBean = resultBean;
        }

        @Override
        public void onClick(View v) {
            if (null != mOnClickListener) {
                mOnClickListener.onItemListener(mCardsBean);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemListener(MarkerListBean resultBean);
    }

    private OnItemClickListener mOnClickListener;

    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnClickListener = onItemClickListener;
    }

}
