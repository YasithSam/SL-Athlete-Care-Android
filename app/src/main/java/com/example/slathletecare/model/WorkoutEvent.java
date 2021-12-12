package com.example.slathletecare.model;

public class WorkoutEvent {
    private String title;
    private String desc;
    private String type;
    private String typeDesc;

    public WorkoutEvent(String title, String desc, String type, String typeDesc) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.typeDesc = typeDesc;
    }
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }
}
