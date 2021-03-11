package com.bp.hmi.citytour.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.bean.MarkerListBean;
import com.bp.hmi.citytour.common.CityConstant;
import com.bp.hmi.citytour.ui.activity.WebViewActivity;
import com.bp.hmi.citytour.ui.adapter.MarkerAdapter;
import com.lxj.xpopup.core.CenterPopupView;

import java.util.List;

public class ListDrawerPopupView extends CenterPopupView {
    private final List<MarkerListBean> mMarkerListBeans;
    private final Context mContext;

    public ListDrawerPopupView(@NonNull Context context, List<MarkerListBean> markerListBeans) {
        super(context);
        mContext = context;
        mMarkerListBeans = markerListBeans;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_list_drawer;
    }

    @Override
    protected void onCreate() {
        TextView textView = findViewById(R.id.tv_drawer_title);
        textView.setText(mMarkerListBeans.get(0).getTitle());
        RecyclerView recyclerView = findViewById(R.id.drawerRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MarkerAdapter mMarkerAdapter = new MarkerAdapter(R.layout.custom_list_drawer_item, mMarkerListBeans);
        recyclerView.setAdapter(mMarkerAdapter);
        mMarkerAdapter.addOnItemClickListener(resultBean -> gotoWebView(resultBean.getUrl()));

    }

    private void gotoWebView(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(CityConstant.PARAMETER_PASSING_KEY, url);
        Intent in = new Intent(mContext, WebViewActivity.class);
        in.putExtras(bundle);
        mContext.startActivity(in);
        dismiss();
    }
}
