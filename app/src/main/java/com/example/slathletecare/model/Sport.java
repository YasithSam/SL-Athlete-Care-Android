package com.example.slathletecare.model;

public class Sport {
    private String sport;

    public Sport(String sport, String institution, String level) {
        this.sport = sport;
        this.institution = institution;
        this.level = level;
    }

    private String institution;
    private String level;

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }



}
