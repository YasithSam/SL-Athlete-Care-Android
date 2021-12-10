package com.example.slathletecare.model;

public class Advice {
    public Advice(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;
}
