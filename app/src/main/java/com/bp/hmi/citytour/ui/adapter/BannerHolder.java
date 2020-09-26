package com.bp.hmi.citytour.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BannerHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public BannerHolder(@NonNull View view) {
        super(view);
        this.imageView = (ImageView) view;
    }
}