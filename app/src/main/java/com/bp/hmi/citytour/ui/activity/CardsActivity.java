package com.bp.hmi.citytour.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.bean.CardsBean;
import com.bp.hmi.citytour.bean.SubCardsTabTitleBean;
import com.bp.hmi.citytour.databinding.ActivityCardsBinding;
import com.bp.hmi.citytour.ui.adapter.CardsAdapter;
import com.bp.hmi.citytour.ui.adapter.SubTabTitleAdapter;
import com.bp.hmi.citytour.ui.viewmodel.CardsViewModel;
import com.bp.hmi.citytour.widget.CustomDialog;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

/**
 * 消费券
 */
public class CardsActivity extends BaseActivity<ActivityCardsBinding, CardsViewModel> {
    private CardsAdapter mCardsAdapter;
    private SubTabTitleAdapter mSubTabTitleAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_cards;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();

        showProgress();
        mViewModel.requestActivityInfo();
    }

    @Override
    public void initLayout() {
        super.initLayout();
        mBinding.ivCardsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.subRecyclerView.setLayoutManager(linearLayoutManager);

        mBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        mViewModel.mSubCardsTabTitleBean.observe(this, new Observer<List<SubCardsTabTitleBean>>() {
            @Override
            public void onChanged(List<SubCardsTabTitleBean> result) {
                Log.d("mSubCardsTabTitleBean", "result:" + result.size());
                mSubTabTitleAdapter = new SubTabTitleAdapter(R.layout.sub_cards_tab_item_layout, result);
                mBinding.subRecyclerView.setAdapter(mSubTabTitleAdapter);
                mSubTabTitleAdapter.setSelectedIndex(0);

                mSubTabTitleAdapter.addOnItemClickListener(new SubTabTitleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemListener(SubCardsTabTitleBean resultBean, int position) {
                        mSubTabTitleAdapter.setSelectedIndex(position);
                    }
                });
            }
        });

        mViewModel.mCardsBeanList.observe(this, new Observer<CardsBean>() {
            @Override
            public void onChanged(CardsBean cardsBean) {
                hideProgress();
                mCardsAdapter = new CardsAdapter(R.layout.cards_item_layout, cardsBean.getResult().getItems());
                mBinding.mRecyclerView.setAdapter(mCardsAdapter);

                mCardsAdapter.addOnItemClickListener(new CardsAdapter.OnItemClickListener() {

                    @Override
                    public void onItemUseListener(CardsBean.ResultBean.ItemsBean resultBean, int position) {
                        showScanDialog();

                    }

                    @Override
                    public void onItemDetailsListener(CardsBean.ResultBean.ItemsBean resultBean, int position) {
                        showDetailsDialog();
                    }
                });
            }
        });
    }

    private void showDetailsDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.cards_details_view, null, false);
        CustomDialog dialog = new CustomDialog(this, view, R.style.dialog);
        view.findViewById(R.id.close).setOnClickListener(view1 -> dialog.dismiss());
        dialog.show();
    }

    private void showScanDialog() {
        Bitmap mBitmap = CodeUtils.createImage("textContent", 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.icon));
        View view = LayoutInflater.from(this).inflate(R.layout.cards_scan_view, null, false);
        CustomDialog dialog = new CustomDialog(this, view, R.style.dialog);
        ImageView scanIv = view.findViewById(R.id.iv_cards_scan);
        scanIv.setImageBitmap(mBitmap);
        view.findViewById(R.id.close).setOnClickListener(view1 -> dialog.dismiss());
        dialog.show();

    }
}