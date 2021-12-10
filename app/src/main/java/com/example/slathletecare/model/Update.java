package com.example.slathletecare.model;

public class Update {
    private String person;
    private String section;
    private String date;

    public Update(String person, String section, String date) {
        this.person = person;
        this.section = section;
        this.date = date;
    }


    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
