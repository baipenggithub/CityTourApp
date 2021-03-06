package com.bp.hmi.citytour.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bp.hmi.citytour.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtils {

    /*
     * 禁用内存缓存功能
     * diskCacheStrategy()方法基本上就是Glide硬盘缓存功能的一切，它可以接收五种参数：
     * DiskCacheStrategy.NONE： 表示不缓存任何内容。
     * DiskCacheStrategy.DATA： 表示只缓存原始图片。
     * DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。
     * DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
     * DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
     */


    /**
     * 加载图片(默认)
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        RequestOptions mRequestOptions = new RequestOptions()
                //.placeholder(R.drawable.wingaid)
                //.error(R.drawable.wingaid)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(mRequestOptions).into(imageView);


    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadRoundCircleImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(5))
                        //  .placeholder(R.drawable.wingaid)
                        //.error(R.drawable.wingaid)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(imageView);
    }


    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadRoundCircleImage(Context context, String url, ImageView imageView, int p) {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(5))
                        .placeholder(p)
                        .error(p)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadRoundCircleRes(Context context, int url, ImageView imageView, int radius) {
        if (radius == 0) {
            Glide.with(context)
                    .load(url)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(url)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius)))
                    .into(imageView);
        }

    }


    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        RequestOptions mRequestOptions = new RequestOptions()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(context).load(url).apply(mRequestOptions).into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImage_17(Context context, String url, ImageView imageView) {
        RequestOptions mRequestOptions = new RequestOptions()
                .centerInside()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(17)))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(context).load(url).apply(mRequestOptions).into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView, int radius) {
        RequestOptions mRequestOptions;
        if (radius == 0) {
            mRequestOptions = new RequestOptions()
                    .centerInside()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        } else {
            mRequestOptions = new RequestOptions()
                    .centerInside()
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius)))
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        }

        Glide.with(context).load(url).apply(mRequestOptions).into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImage_20(Context context, String url, ImageView imageView) {
        RequestOptions mRequestOptions = new RequestOptions()
                .centerInside()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(context).load(url).apply(mRequestOptions).into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImage_15(Context context, String url, ImageView imageView) {
        RequestOptions mRequestOptions = new RequestOptions()
                .centerInside()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(15)))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(context).load(url).apply(mRequestOptions).into(imageView);
    }


    /**
     * 加载本地图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadFileImage(Context context, String url, ImageView imageView) {
        RequestOptions mRequestOptions = new RequestOptions()
                .placeholder(R.color.white)
                .error(R.color.white)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(context).load("file:///" + url).apply(mRequestOptions).into(imageView);
    }
}
