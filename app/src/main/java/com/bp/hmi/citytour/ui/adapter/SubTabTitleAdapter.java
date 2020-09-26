package com.bp.hmi.citytour.ui.adapter;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseRecyclerAdapter;
import com.bp.hmi.citytour.base.BaseRecyclerHolder;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;

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
public class SubTabTitleAdapter extends BaseRecyclerAdapter<SubCardsTabTitleBean, BaseRecyclerHolder> {
    private int mSelectedIndex = 0;

    /**
     * Initialization data.
     *
     * @param layoutResId
     * @param data
     */
    public SubTabTitleAdapter(int layoutResId, @Nullable List<SubCardsTabTitleBean> data) {
        super(layoutResId, data);
    }

    public void setSelectedIndex(int checkedIndex) {
        mSelectedIndex = checkedIndex;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, SubCardsTabTitleBean item) {
        helper.setText(R.id.tv_sub_cards_tab_title, item.getTitle());
        if (helper.getAdapterPosition() == mSelectedIndex) {
            helper.getView(R.id.tv_sub_cards_tab_title).setSelected(true);
            ((TextView) helper.getView(R.id.tv_sub_cards_tab_title)).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            helper.getView(R.id.iv_sub_cards_tab_title).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_sub_cards_tab_title).setSelected(false);
            ((TextView) helper.getView(R.id.tv_sub_cards_tab_title)).setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            helper.getView(R.id.iv_sub_cards_tab_title).setVisibility(View.INVISIBLE);
        }

        helper.itemView.setOnClickListener(new ClickListener(item, helper.getAdapterPosition()));
    }

    public class ClickListener implements View.OnClickListener {
        private SubCardsTabTitleBean mCardsBean;
        private int mPosition;

        public ClickListener(SubCardsTabTitleBean resultBean, int position) {
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
        void onItemListener(SubCardsTabTitleBean resultBean, int position);
    }

    private OnItemClickListener mOnClickListener;

    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnClickListener = onItemClickListener;
    }
}
