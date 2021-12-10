package com.example.slathletecare.model;

public class Medicine {
    private String title;
    private String desc;

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



    public Medicine(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }



}
