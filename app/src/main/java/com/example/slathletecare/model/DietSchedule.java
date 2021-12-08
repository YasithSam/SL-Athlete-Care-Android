package com.example.slathletecare.model;

public class DietSchedule {
   private int id;
   private int dietId;
   private String title;
   private String description;

   private String meal;
   private String caseStudy;


   public DietSchedule(int id, int dietId, String title, String description, String meal,String caseStudy) {
      this.id = id;
      this.dietId = dietId;
      this.title = title;
      this.description = description;
      this.meal=meal;
      this.caseStudy = caseStudy;
   }
   public int getId() {
      return id;
   }
   public String getMeal() {
      return meal;
   }

   public void setMeal(String meal) {
      this.meal = meal;
   }
   public void setId(int id) {
      this.id = id;
   }

   public int getDietId() {
      return dietId;
   }

   public void setDietId(int dietId) {
      this.dietId = dietId;
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
