package com.example.slathletecare.model;

public class ForumEvent {
    private String Id;
    private String title;
    private String injury;
    private String date;
    private String status;
    private String doctor;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;
    private String comment;

    public ForumEvent(String id, String title, String injury, String date, String status, String doctor,String comment,String desc) {
        Id = id;
        this.title = title;
        this.injury = injury;
        this.date = date;
        this.status = status;
        this.doctor = doctor;
        this.comment=comment;
        this.desc=desc;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInjury() {
        return injury;
    }

    public void setInjury(String injury) {
        this.injury = injury;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
