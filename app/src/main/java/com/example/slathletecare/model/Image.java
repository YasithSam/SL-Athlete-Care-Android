package com.example.slathletecare.model;

public class Image {
    private String heading;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public Image(String heading, String url) {
        this.heading = heading;
        this.url = url;
    }



}
