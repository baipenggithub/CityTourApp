package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
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

    private int type;

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
        type = getIntent().getIntExtra("type", 0);
        showProgress();
        mViewModel.getSubCardsTitle(type);
        mViewModel.requestActivityInfo();
    }

    @Override
    public void initLayout() {
        super.initLayout();
        mBinding.title.setText(type == 0 ? "消费券中心" : "个人中心-消费券");
        mBinding.myCards.setVisibility(type == 0 ? View.VISIBLE : View.INVISIBLE);
        mBinding.topMenu.setVisibility(type == 0 ? View.GONE : View.VISIBLE);
        if (type == 0) {
            mBinding.myCards.setOnClickListener(view -> {
                Intent in = new Intent(CardsActivity.this, CardsActivity.class);
                in.putExtra("type", 1);
                startActivity(in);
            });
        } else {
            int childCount = mBinding.topMenu.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (i % 2 == 0) {
                    TextView childAt = (TextView) mBinding.topMenu.getChildAt(i);
                    childAt.setOnClickListener(view -> {
                        for (int j = 0; j < childCount; j++) {
                            if (j % 2 == 0) {
                                TextView childAt1 = (TextView) mBinding.topMenu.getChildAt(j);
                                childAt1.setBackgroundResource(R.drawable.base_round_30_efefef);
                                childAt1.setTextColor(Color.parseColor("#727171"));
                            }
                        }
                        childAt.setBackgroundResource(R.drawable.cards_menu_button);
                        childAt.setTextColor(ContextCompat.getColor(CardsActivity.this, R.color.white));
                    });
                }
            }
        }
        mBinding.ivCardsBack.setOnClickListener(view -> finish());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.subRecyclerView.setLayoutManager(linearLayoutManager);

        mBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        mViewModel.mSubCardsTabTitleBean.observe(this, result -> {
            Log.d("mSubCardsTabTitleBean", "result:" + result.size());
            mSubTabTitleAdapter = new SubTabTitleAdapter(R.layout.sub_cards_tab_item_layout, result);
            mBinding.subRecyclerView.setAdapter(mSubTabTitleAdapter);
            mSubTabTitleAdapter.setSelectedIndex(0);

            mSubTabTitleAdapter.addOnItemClickListener(new SubTabTitleAdapter.OnItemClickListener() {
                @Override
                public void onItemListener(SubCardsTabTitleBean resultBean, int position, View view) {
                    mSubTabTitleAdapter.setSelectedIndex(position);

                }
            });
        });

        mViewModel.mCardsBeanList.observe(this, new Observer<CardsBean>() {
            @Override
            public void onChanged(CardsBean cardsBean) {
                hideProgress();
                mCardsAdapter = new CardsAdapter(R.layout.cards_item_layout, cardsBean.getResult().getItems(), type);
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