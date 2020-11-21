package com.bp.hmi.citytour.widget.pop;


import androidx.annotation.DrawableRes;

import java.io.Serializable;

public class PopModel implements Serializable {

    private int drawableId;
    private String itemDesc;

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(@DrawableRes int drawableId) {
        this.drawableId = drawableId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

}
