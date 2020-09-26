package com.bp.hmi.citytour.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.TextView;

import com.bp.hmi.citytour.BR;
import com.bp.hmi.citytour.R;
import com.bp.hmi.citytour.base.BaseActivity;
import com.bp.hmi.citytour.databinding.ActivityBookDetaislBindingImpl;
import com.bp.hmi.citytour.ui.viewmodel.BookViewModel;
import com.bp.hmi.citytour.utils.ToastUtils;
import com.necer.calendar.BaseCalendar;
import com.necer.listener.OnCalendarChangedListener;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 预约详情
 */
public class BookDetailsActivity extends BaseActivity<ActivityBookDetaislBindingImpl, BookViewModel> implements View.OnClickListener {
    private Date mCurrent;
    private int mBookCount = 1;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_book_detaisl;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initLayout() {
        super.initLayout();
        mBinding.tvDate.setText(new SimpleDateFormat("yyyy年M月d日").format(new Date()));
        SpannableString str = new SpannableString("数量（每单限购 3 份）");
        str.setSpan(new AbsoluteSizeSpan(14, true), 2, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mBinding.tvNumber.setText(str);

        mBinding.adultCard.setCardInfo("成人票(单人)", "限2020.9.30前使用", "届时请携带预约成功短信", "", 135);
        mBinding.adultCard.setSelectStatus(true);

        mBinding.oldCard.setCardInfo("老年票(单人)", "限60岁以上老人", "限2020.9.30前使用", "届时请携带预约成功短信", 15);
        mBinding.childrenCard.setCardInfo("儿童票(单人)", "限2020.7.30-2020.9.30双休日", "使用，平日不可使用", "限身高1.2米(含)以下儿童使用", 66);
        mBinding.relevanceCard.setCardInfo("关联票(单人)", "限2020.9.30前使用", "届时请携带预约成功短信", "", 88);

        mBinding.monthCalendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, LocalDate localDate) {
                if (mCurrent == null || !mCurrent.equals(localDate.toDate())) {
                    mCurrent = localDate.toDate();
                    mBinding.monthCalendar.jumpDate(new SimpleDateFormat("yyyy-MM-dd").format(mCurrent));//同步日历
                    mBinding.tvDate.setText(new SimpleDateFormat("yyyy年M月d日").format(mCurrent));
                    int childCount = mBinding.viewMonthBar.monthList.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        TextView childAt = (TextView) mBinding.viewMonthBar.monthList.getChildAt(i);
                        childAt.setTextColor(Color.parseColor("#999999"));
                    }
                    TextView childAt = (TextView) mBinding.viewMonthBar.monthList.getChildAt(month - 1);
                    childAt.setTextColor(Color.parseColor("#57CED4"));
                    if (month < 7) {
                        mBinding.viewMonthBar.monthSv.scrollTo(0, 0);
                    } else {
                        mBinding.viewMonthBar.monthSv.scrollTo(10000000, 0);
                    }
                }
            }
        });

        mCurrent = new Date();
        mBinding.tvDate.setText(new SimpleDateFormat("yyyy年M月d日").format(mCurrent));


        mBinding.ivBack.setOnClickListener(this);
        mBinding.adultCard.setOnClickListener(this);
        mBinding.oldCard.setOnClickListener(this);
        mBinding.childrenCard.setOnClickListener(this);
        mBinding.relevanceCard.setOnClickListener(this);
        mBinding.tvDate.setOnClickListener(this);
        mBinding.ivUp.setOnClickListener(this);
        mBinding.tvBookOk.setOnClickListener(this);
        mBinding.ivBookSubtract.setOnClickListener(this);
        mBinding.ivBookAdd.setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_book_add:
                mBookCount++;
                mViewModel.mBookSum.set(mBookCount + "");
                break;
            case R.id.iv_book_subtract:
                if (mBookCount <= 1) {
                    mBookCount = 1;
                } else {
                    mBookCount--;
                }
                mViewModel.mBookSum.set(mBookCount + "");
                break;
            case R.id.tv_book_ok:
                ToastUtils.showLong("预定成功");
                break;
            case R.id.ivBack:
                finish();
                break;
            case R.id.adult_card:
                mBinding.adultCard.setSelectStatus(true);
                mBinding.oldCard.setSelectStatus(false);
                mBinding.childrenCard.setSelectStatus(false);
                mBinding.relevanceCard.setSelectStatus(false);
                break;
            case R.id.old_card:
                mBinding.adultCard.setSelectStatus(false);
                mBinding.oldCard.setSelectStatus(true);
                mBinding.childrenCard.setSelectStatus(false);
                mBinding.relevanceCard.setSelectStatus(false);
                break;
            case R.id.children_card:
                mBinding.adultCard.setSelectStatus(false);
                mBinding.oldCard.setSelectStatus(false);
                mBinding.childrenCard.setSelectStatus(true);
                mBinding.relevanceCard.setSelectStatus(false);
                break;
            case R.id.relevance_card:
                mBinding.adultCard.setSelectStatus(false);
                mBinding.oldCard.setSelectStatus(false);
                mBinding.childrenCard.setSelectStatus(false);
                mBinding.relevanceCard.setSelectStatus(true);
                break;
            case R.id.tv_date:
                if (mBinding.llCalendar.getVisibility() == View.GONE) {
                    mBinding.llCalendar.setVisibility(View.VISIBLE);
                    mBinding.ivOpen.setVisibility(View.GONE);
                }
                break;
            case R.id.iv_up:
                mBinding.llCalendar.setVisibility(View.GONE);
                mBinding.ivOpen.setVisibility(View.VISIBLE);
                break;
        }
    }
}