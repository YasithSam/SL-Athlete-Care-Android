package com.example.slathletecare.model;

public class feedback {
    private String type;
    private String feedback;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public feedback(String type, String feedback,String date) {
        this.type = type;
        this.feedback = feedback;
        this.date=date;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
