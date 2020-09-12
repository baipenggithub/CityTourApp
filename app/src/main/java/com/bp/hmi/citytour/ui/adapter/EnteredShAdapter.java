package com.bp.hmi.citytour.ui.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseApplication;
import com.bp.hmi.citytour.base.BaseRecyclerAdapter;
import com.bp.hmi.citytour.base.BaseRecyclerHolder;
import com.bp.hmi.citytour.bean.CardsBean;
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
public class EnteredShAdapter extends BaseRecyclerAdapter<CardsBean, BaseRecyclerHolder> {

    /**
     * Initialization data.
     *
     * @param layoutResId
     * @param data
     */
    public EnteredShAdapter(int layoutResId, @Nullable List<CardsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, CardsBean item) {
        helper.setText(R.id.tv_entered_sh_title, item.getTitle());
        helper.setText(R.id.tv_entered_sh_spot, "游览景点:" + item.getType());
        helper.setText(R.id.tv_entered_sh_time, "推荐时间:" + item.getTime());
        GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.test_pic, helper.getView(R.id.iv_entered_sh_icon));

        helper.itemView.setOnClickListener(new ClickListener(item, helper.getAdapterPosition()));
    }

    public class ClickListener implements View.OnClickListener {
        private CardsBean mCardsBean;
        private int mPosition;

        public ClickListener(CardsBean resultBean, int position) {
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
        void onItemListener(CardsBean resultBean, int position);
    }

    private OnItemClickListener mOnClickListener;

    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnClickListener = onItemClickListener;
    }
}
