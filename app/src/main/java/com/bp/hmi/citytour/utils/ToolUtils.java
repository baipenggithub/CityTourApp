package com.bp.hmi.citytour.utils;

import android.content.Context;

/**
 * <pre>
 *     author : BaiPengMac
 *     e-mail : xxx@xx
 *     time   : 2020/10/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ToolUtils {

    public static boolean isOdd(int a) {
        if (a % 2 == 1) {   //是奇数
            return true;
        }
        return false;
    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
