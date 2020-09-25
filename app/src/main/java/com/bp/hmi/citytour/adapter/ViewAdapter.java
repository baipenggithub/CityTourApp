package com.bp.hmi.citytour.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;

import androidx.databinding.BindingAdapter;

import com.bp.hmi.citytour.action.BindingCommand;

/**
 * ViewAdapter.
 */
public class ViewAdapter {

    // Anti repeat click interval (seconds)
    public static final int CLICK_INTERVAL = 1;

    /**
     * requireAll It means whether all parameters need to be bound. False means No.
     * Onclick event binding of view
     * onClickCommand Bound command,
     * isThrottleFirst Whether to turn on to prevent too fast clicking
     */
    @BindingAdapter(value = {"onClickCommand", "isThrottleFirst"}, requireAll = false)
    public static void onClickCommand(View view, final BindingCommand<String> clickCommand,
                                      final boolean isThrottleFirst) {
        view.setOnClickListener(view1 -> {
            if (clickCommand != null) {
                if (null != view1.getTag() && !TextUtils.isEmpty(view1.getTag().toString())) {
                    clickCommand.execute(view1.getTag().toString());
                } else {
                    clickCommand.execute();
                }

            }
        });
    }

    /**
     * view onLongClick.
     */
    @BindingAdapter(value = {"onLongClickCommand"}, requireAll = false)
    public static void onLongClickCommand(View view, final BindingCommand clickCommand) {
        view.setOnClickListener(view1 -> {
            if (clickCommand != null) {
                clickCommand.execute();
            }
        });
    }

    /**
     * Callback control itself.
     */
    @BindingAdapter(value = {"currentView"}, requireAll = false)
    public static void replyCurrentView(View currentView, BindingCommand bindingCommand) {
        if (bindingCommand != null) {
            bindingCommand.execute(currentView);
        }
    }

    /**
     * view need to get focus.
     */
    @BindingAdapter({"requestFocus"})
    public static void requestFocusCommand(View view, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        } else {
            view.clearFocus();
        }
    }

    /**
     * view event binding with changed focus.
     */
    @BindingAdapter({"onFocusChangeCommand"})
    public static void onFocusChangeCommand(View view,
                                            final BindingCommand<Boolean> onFocusChangeCommand) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (onFocusChangeCommand != null) {
                    onFocusChangeCommand.execute(hasFocus);
                }
            }
        });
    }

    /**
     * view isVisible.
     */
    @BindingAdapter(value = {"isVisible"}, requireAll = false)
    public static void isVisible(View view, final Boolean visibility) {
        if (visibility) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * RadioGroup checked changed command.
     *
     * @param radioGroup     RadioGroup.
     * @param bindingCommand BindingCommand.
     */
    @BindingAdapter(value = {"onCheckedChangedCommand"}, requireAll = false)
    public static void onCheckedChangedCommand(final RadioGroup radioGroup,
                                               final BindingCommand<Integer> bindingCommand) {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (null != bindingCommand) {
                bindingCommand.execute(checkedId);
            }
        });
    }
}
