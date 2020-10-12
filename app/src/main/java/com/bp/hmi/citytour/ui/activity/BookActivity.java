package com.bp.hmi.citytour.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityBookBinding;
import com.bp.hmi.citytour.ui.adapter.ScheduleAdapter;
import com.bp.hmi.citytour.ui.viewmodel.BookViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 预约
 */
public class BookActivity extends BaseActivity<ActivityBookBinding, BookViewModel> {
    private ScheduleAdapter mScheduleAdapter;
    private Date mCurrent;
    private int mTemp = 0;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_book;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initLayout() {
        super.initLayout();

        mCurrent = new Date();
        mBinding.tvTime.setText(new SimpleDateFormat("yyyy年 M月 d日").format(mCurrent));

        LinearLayoutManager managerCircle = new LinearLayoutManager(this);
        managerCircle.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.listSchedule.setLayoutManager(managerCircle);

        mBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //事件
        mBinding.subscribe.setOnClickListener(view -> {
            Intent i = new Intent(BookActivity.this, BookDetailsActivity.class);
            startActivity(i);
        });
        mBinding.selectDate.setOnClickListener(view -> {
            if (mTemp == 0) {
                mBinding.monthCalendar.setVisibility(View.VISIBLE);
                mTemp = 1;
            } else {
                mBinding.monthCalendar.setVisibility(View.GONE);
                mTemp = 0;
            }
        });

        mBinding.monthCalendar.setOnCalendarChangedListener((baseCalendar, year, month, localDate) -> {
            if (mCurrent == null || !mCurrent.equals(localDate.toDate())) {
                mCurrent = localDate.toDate();
                mBinding.monthCalendar.jumpDate(new SimpleDateFormat("yyyy-MM-dd").format(mCurrent));//同步日历
                mBinding.tvTime.setText(new SimpleDateFormat("yyyy年 M月 d日").format(mCurrent));
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        mViewModel.getBookData();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        mViewModel.mBookData.observe(this, scheduleEntities -> {
            mScheduleAdapter = new ScheduleAdapter(R.layout.item_schedule_view, scheduleEntities);
            mBinding.listSchedule.setAdapter(mScheduleAdapter);
        });
    }
}