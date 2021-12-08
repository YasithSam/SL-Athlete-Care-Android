package com.example.slathletecare.model;

public class WorkoutSchedule {
    private int id;
    private int wId;
    private String title;
    private String description;
    private String caseStudy;

    public WorkoutSchedule(int id, int dietId, String title, String description, String caseStudy) {
        this.id = id;
        this.wId= dietId;
        this.title = title;
        this.description = description;
        this.caseStudy = caseStudy;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDietId() {
        return wId;
    }

    public void setDietId(int dietId) {
        this.wId = dietId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCaseStudy() {
        return caseStudy;
    }

    public void setCaseStudy(String caseStudy) {
        this.caseStudy = caseStudy;
    }




}
