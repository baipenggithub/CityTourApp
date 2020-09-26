package com.bp.hmi.citytour.bean;

public class PavilionBean
{
    private String title;
    private String desc;
    private String distance;
    private int    icon;

    public PavilionBean(String title, String desc, String distance, int icon)
    {
        this.title = title;
        this.desc = desc;
        this.distance = distance;
        this.icon = icon;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getDistance()
    {
        return distance;
    }

    public void setDistance(String distance)
    {
        this.distance = distance;
    }

    public int getIcon()
    {
        return icon;
    }

    public void setIcon(int icon)
    {
        this.icon = icon;
    }
}
