package com.bp.hmi.citytour.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseRecyclerAdapter;
import com.bp.hmi.citytour.base.BaseRecyclerHolder;
import com.bp.hmi.citytour.bean.PavilionBean;

import java.util.List;


public class PavilionAdapter extends BaseRecyclerAdapter<PavilionBean, BaseRecyclerHolder> {

    /**
     * Initialization data.
     *
     * @param layoutResId
     * @param data
     */
    public PavilionAdapter(int layoutResId, @Nullable List<PavilionBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, PavilionBean item) {
        helper.setText(R.id.item_title, item.getTitle());
        helper.setText(R.id.item_desc, item.getDesc());
        helper.setText(R.id.item_distance, item.getDistance());
        ((ImageView) helper.getView(R.id.item_icon)).setImageResource(item.getIcon());
        helper.itemView.setOnClickListener(new ClickListener(item, helper.getAdapterPosition()));
    }

    public class ClickListener implements View.OnClickListener {
        private PavilionBean mCardsBean;
        private int mPosition;

        public ClickListener(PavilionBean resultBean, int position) {
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
        void onItemListener(PavilionBean resultBean, int position);
    }

    private OnItemClickListener mOnClickListener;

    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnClickListener = onItemClickListener;
    }

}
