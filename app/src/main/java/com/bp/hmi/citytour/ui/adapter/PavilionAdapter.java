package com.bp.hmi.citytour.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseRecyclerAdapter;
import com.bp.hmi.citytour.base.BaseRecyclerHolder;
import com.bp.hmi.citytour.bean.PavilionBean;

import java.util.List;
import java.util.Random;


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
        Random random = new Random();
        int max = 3;
        int min = 1;
        int index = random.nextInt(max) % (max - min + 1) + min;
        if (index == 1) {
            helper.setGone(R.id.iv_virtual_showrooms,false);
            helper.setBackgroundRes(R.id.item_dot, R.drawable.base_round_10_green);
        } else if (index == 2) {
            helper.setGone(R.id.iv_virtual_showrooms,true);
            helper.setBackgroundRes(R.id.item_dot, R.drawable.base_round_10_red);
        } else if (index == 3) {
            helper.setGone(R.id.iv_virtual_showrooms,false);
            helper.setBackgroundRes(R.id.item_dot, R.drawable.base_round_10_ff9900);
        }
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
