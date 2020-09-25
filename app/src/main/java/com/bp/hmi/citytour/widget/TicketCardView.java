package com.bp.hmi.citytour.widget;


import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bp.hmi.citytour.R;

public class TicketCardView extends FrameLayout {

    private Context mContext;
    private LayoutInflater mFromInflater;
    private RelativeLayout mCardView;
    private TextView mCardTitle;
    private TextView mCardRule1;
    private TextView mCardRule2;
    private TextView mCardRule3;
    private TextView mCardPrice;
    private ImageView mCardIcon;

    public TicketCardView(@NonNull Context context) {
        super(context);
        init();
    }

    public TicketCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TicketCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mContext = getContext();
        mFromInflater = LayoutInflater.from(mContext);
        View view = mFromInflater.inflate(R.layout.view_ticket_card, this, false);
        mCardView = view.findViewById(R.id.rl_card);
        mCardPrice = view.findViewById(R.id.tv_card_price);
        mCardTitle = view.findViewById(R.id.tv_card_title);
        mCardRule1 = view.findViewById(R.id.tv_card_rule_1);
        mCardRule2 = view.findViewById(R.id.tv_card_rule_2);
        mCardRule3 = view.findViewById(R.id.tv_card_rule_3);
        mCardIcon = view.findViewById(R.id.ic_card);
        this.addView(view);
    }

    public void setCardInfo(String title, String rule1, String rule2, String rule3, int price) {
        mCardTitle.setText(title);
        mCardRule1.setText(rule1);
        mCardRule2.setText(rule2);
        mCardRule3.setText(rule3);
        SpannableString str = new SpannableString(price + "元");
        str.setSpan(new AbsoluteSizeSpan(14, true), String.valueOf(price).length(), str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mCardPrice.setText(str);
    }

    public void setSelectStatus(Boolean select) {
        mCardView.setBackgroundResource(select ? R.drawable.base_frame_57ced4_1_round_10_white : R.drawable.base_frame_1_gray_round_10_white);
        mCardTitle.setTextColor(Color.parseColor(select ? "#57CED4" : "#ff000000"));
        mCardPrice.setTextColor(Color.parseColor(select ? "#57CED4" : "#ff000000"));
        mCardIcon.setVisibility(select ? VISIBLE : GONE);
    }
}
