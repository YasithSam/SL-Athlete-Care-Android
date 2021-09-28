package com.example.slathletecare.model;

public class Articles {
    private String header;
    private String desc;
    //private String imgUrl;
    private int likes;
    private int comments;
    //private String date;

    public Articles(String header, String desc, int likes, int comments) {
        this.header = header;
        this.desc = desc;
        this.likes = likes;
        this.comments = comments;

    }
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }



}
