package com.bp.hmi.citytour.bean;

/**
 * <pre>
 *     author : BaiPengMac
 *     e-mail : xxx@xx
 *     time   : 2021/03/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MarkerBean {
    private double mLon;
    private double mLat;

    public double getmLon() {
        return mLon;
    }

    public void setmLon(double mLon) {
        this.mLon = mLon;
    }

    public double getmLat() {
        return mLat;
    }

    public void setmLat(double mLat) {
        this.mLat = mLat;
    }

    public String getmTitle() {
        return mTitle == null ? "" : mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmId() {
        return mId == null ? "" : mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    private String mTitle;
    private String mId;
}
