package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import androidx.databinding.library.baseAdapters.BR;

import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.common.CityConstant;
import com.bp.hmi.citytour.databinding.ActivityWebViewBinding;
import com.bp.hmi.citytour.ui.viewmodel.WebViewModel;
import com.just.agentweb.AgentWeb;

public class WebViewActivity extends BaseActivity<ActivityWebViewBinding, WebViewModel> {
    private AgentWeb mAgentWeb;
    private String mTag;

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
        mBinding.ivBack.setOnClickListener(v -> finish());
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mTag = bundle.getString(CityConstant.PARAMETER_PASSING_KEY);
        buildAgentWeb(getUrl(mTag));
    }

    private String getUrl(String tag) {

        String url = "";
        switch (tag) {
            case "红色足迹":
                mViewModel.mWebTitle.set(tag);
                url = "";
                break;
            case "节气民俗":
                mViewModel.mWebTitle.set(tag);
                url = "https://s.wcd.im/v/4injcZ3d/?slv=6&sid=b701&v=oosnVwrLCzRJVujZlORcJFMOH9ZU";
                break;
            case "姓氏宗谱":
                mViewModel.mWebTitle.set(tag);
                url = "http://h5.genealogy.imadc.cn/";
                break;
            default:
                mViewModel.mWebTitle.set("云展览");
                url = tag;
                break;
        }
        return url;
    }

    protected void buildAgentWeb(String url) {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mBinding.container, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);
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