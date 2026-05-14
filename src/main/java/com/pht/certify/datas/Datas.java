package com.pht.certify.datas;

public class Datas {

    private String title;
    private String desc;
    private String icon;

    public Datas(String title, String desc, String icon) {
        this.title = title;
        this.desc = desc;
        this.icon = icon;
    }

    public Datas(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getIcon() {
        return icon;
    }


}