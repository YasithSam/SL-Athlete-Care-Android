package com.example.slathletecare.model;

public class Conversation {

   private String message;
   private String datetime;
   private String time;

   public Conversation(String message,String datetime,String time) {
      this.message = message;
      this.datetime=datetime;
      this.time=time;

   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }
   public String getDatetime() {
      return datetime;
   }

   public void setDatetime(String datetime) {
      this.datetime = datetime;
   }

   public String getTime() {
      return time;
   }

   public void setTime(String time) {
      this.time = time;
   }



}
