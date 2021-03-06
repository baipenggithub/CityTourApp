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

    private int type;

    /**
     * Initialization data.
     *
     * @param layoutResId
     * @param data
     */
    public CardsAdapter(int layoutResId, @Nullable List<CardsBean.ResultBean.ItemsBean> data, int type) {
        super(layoutResId, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, CardsBean.ResultBean.ItemsBean item) {
        helper.setText(R.id.tv_cards_title, item.getName());
        helper.setText(R.id.tv_cards_time, "有效时间:" + item.getExpire());
        helper.setVisible(R.id.ll_remain, type == 0 ? true : false);
        helper.setVisible(R.id.iv_vip, helper.getAdapterPosition() % 2 == 0);
        GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_0509, helper.getView(R.id.iv_cards_type), 30);
        if (helper.getAdapterPosition() == 0) {
            setStyleButton(helper, 0);
            helper.setImageResource(R.id.iv_cards_tag, R.mipmap.ic_cards_tag_1);
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_08, helper.getView(R.id.iv_cards_type), 30);
        } else if (helper.getAdapterPosition() == 1) {
            setStyleButton(helper, 1);
            helper.setImageResource(R.id.iv_cards_tag, R.mipmap.ic_cards_tag_2);
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_07, helper.getView(R.id.iv_cards_type), 30);
        } else if (helper.getAdapterPosition() == 2) {
            setStyleButton(helper, 2);
            helper.setImageResource(R.id.iv_cards_tag, R.mipmap.ic_cards_tag_3);
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_06, helper.getView(R.id.iv_cards_type), 30);
        } else if (helper.getAdapterPosition() == 3) {
            setStyleButton(helper, 3);
            helper.setImageResource(R.id.iv_cards_tag, R.mipmap.ic_cards_tag_4);
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_0509, helper.getView(R.id.iv_cards_type), 30);
        } else if (helper.getAdapterPosition() == 4) {
            setStyleButton(helper, 4);
            helper.setImageResource(R.id.iv_cards_tag, R.mipmap.ic_cards_tag_1);
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_08, helper.getView(R.id.iv_cards_type), 30);
        } else {
            setStyleButton(helper, 0);
            helper.setImageResource(R.id.iv_cards_tag, R.mipmap.ic_cards_tag_2);
            GlideUtils.loadRoundCircleRes(BaseApplication.getApplication(), R.mipmap.tour_home_02_07, helper.getView(R.id.iv_cards_type), 30);
        }
        helper.getView(R.id.tv_cards_use).setOnClickListener(new ClickListener(item, helper.getAdapterPosition()));
        helper.getView(R.id.tv_cards_detail).setOnClickListener(new ClickListener(item, helper.getAdapterPosition()));
    }

    public void setStyleButton(BaseRecyclerHolder helper, int type) {
        if (type == 0) {
            helper.setText(R.id.tv_cards_use, "线上使用");
            helper.setBackgroundRes(R.id.tv_cards_use, R.drawable.base_round_30_f29700);
        } else if (type == 1) {
            helper.setText(R.id.tv_cards_use, "线下使用");
            helper.setBackgroundRes(R.id.tv_cards_use, R.drawable.base_round_30_8fc320);
        } else if (type == 2) {
            helper.setText(R.id.tv_cards_use, "积分兑换");
            helper.setBackgroundRes(R.id.tv_cards_use, R.drawable.base_round_30_4bd3dd);
        } else if (type == 3) {
            helper.setText(R.id.tv_cards_use, "20:00抢券");
            helper.setBackgroundRes(R.id.tv_cards_use, R.drawable.base_round_30_cacaca);
        } else if (type == 4) {
            helper.setText(R.id.tv_cards_use, "免费领取");
            helper.setBackgroundRes(R.id.tv_cards_use, R.drawable.base_round_30_4bd3dd);
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

            switch (v.getId()) {
                case R.id.tv_cards_use:
                    if (null != mOnClickListener) {
                        mOnClickListener.onItemUseListener(mCardsBean, mPosition);
                    }
                    break;

                case R.id.tv_cards_detail:
                    if (null != mOnClickListener) {
                        mOnClickListener.onItemDetailsListener(mCardsBean, mPosition);
                    }
                    break;
            }

        }
    }

    public interface OnItemClickListener {
        void onItemUseListener(CardsBean.ResultBean.ItemsBean resultBean, int position);

        void onItemDetailsListener(CardsBean.ResultBean.ItemsBean resultBean, int position);
    }

    private OnItemClickListener mOnClickListener;

    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnClickListener = onItemClickListener;
    }
}
