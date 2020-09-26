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
public class CardsAdapter extends BaseRecyclerAdapter<CardsBean.ResultBean.ItemsBean, BaseRecyclerHolder> {

    /**
     * Initialization data.
     *
     * @param layoutResId
     * @param data
     */
    public CardsAdapter(int layoutResId, @Nullable List<CardsBean.ResultBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, CardsBean.ResultBean.ItemsBean item) {
        helper.setText(R.id.tv_cards_title, item.getName());
        helper.setText(R.id.tv_cards_time, "有效时间:" + item.getExpire());

        GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_0509, helper.getView(R.id.iv_cards_type));

        if (helper.getAdapterPosition() == 0) {
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_08, helper.getView(R.id.iv_cards_type));

        } else if (helper.getAdapterPosition() == 1) {
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_07, helper.getView(R.id.iv_cards_type));

        } else if (helper.getAdapterPosition() == 2) {
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_06, helper.getView(R.id.iv_cards_type));

        } else if (helper.getAdapterPosition() == 3) {
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_0509, helper.getView(R.id.iv_cards_type));

        } else if (helper.getAdapterPosition() == 4) {
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_08, helper.getView(R.id.iv_cards_type));

        } else {
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_07, helper.getView(R.id.iv_cards_type));

        }
    }

    public class ClickListener implements View.OnClickListener {
        private CardsBean.ResultBean.ItemsBean mCardsBean;
        private int mPosition;

        public ClickListener(CardsBean.ResultBean.ItemsBean resultBean, int position) {
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
        void onItemListener(CardsBean.ResultBean.ItemsBean resultBean, int position);
    }

    private OnItemClickListener mOnClickListener;

    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnClickListener = onItemClickListener;
    }
}