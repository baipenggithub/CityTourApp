package com.bp.hmi.citytour.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import com.bp.hmi.citytour.R;

import java.util.Objects;


/**
 * CustomProgressDialog.
 */

public class CustomProgressDialog extends ProgressDialog {
    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_custom_progress_dialog);
        this.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = Objects.requireNonNull(getWindow()).getAttributes();
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setAttributes(params);

    }
}
