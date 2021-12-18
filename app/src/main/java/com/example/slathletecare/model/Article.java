package com.example.slathletecare.model;

import java.io.Serializable;

public class Article implements Serializable {

    private String Id;
    private String heading;
    private String description;
    private String datetime;
    private String likes;
    private String comments;
    private String url;

    private String status;

    public Article(String heading, String description, String datetime, String likes, String comments, String url,String status,String id) {
        this.heading = heading;
        this.description = description;
        this.datetime = datetime;
        this.likes = likes;
        this.comments = comments;
        this.url = url;
        this.status=status;
        this.Id=id;
    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
