package com.bp.hmi.citytour.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import androidx.databinding.library.baseAdapters.BR;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityWebViewBinding;
import com.bp.hmi.citytour.ui.viewmodel.BookViewModel;
import com.just.agentweb.AgentWeb;

public class WebViewActivity extends BaseActivity<ActivityWebViewBinding, BookViewModel> {
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_web_view;
    }

    @Override
    public void initLayout() {
        super.initLayout();
        buildAgentWeb();
    }

    protected void buildAgentWeb() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mBinding.container, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go("http://h5.genealogy.imadc.cn/");


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}