package com.example.slathletecare.model;

public class Comment {
    private String userName;
    private String Comment;
    private String datetime;

    public Comment(String userName, String comment, String datetime) {
        this.userName = userName;
        Comment = comment;
        this.datetime = datetime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }



}
