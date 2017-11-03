package com.jr.finenews;

/**
 * Created by alfo6-6 on 2017-07-14.
 */

public class NaviItem {

     int naviIcon;
     String naviName;

    public NaviItem(int naviIcon, String naviName) {
        this.naviIcon = naviIcon;
        this.naviName = naviName;
    }

    public int getNaviIcon() {
        return naviIcon;
    }

    public void setNaviIcon(int naviIcon) {
        this.naviIcon = naviIcon;
    }

    public String getNaviName() {
        return naviName;
    }

    public void setNaviName(String naviName) {
        this.naviName = naviName;
    }
}
