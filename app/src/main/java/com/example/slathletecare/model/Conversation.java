package com.example.slathletecare.model;

public class Conversation {
   private boolean status;
   private int id;
   private String fromUser;
   private String topMessage;
   private String imgUrl;

   public Conversation(int id,String from, String topMsg) {
      this.fromUser = from;
      this.topMessage =topMsg;

   }

   public void setFromUser(String fromUser) {
      this.fromUser = fromUser;
   }

   public void setTopMessage(String topMessage) {
      this.topMessage = topMessage;
   }

   public boolean isStatus() {
      return status;
   }

   public String getFromUser() {
      return fromUser;
   }

   public String getTopMessage() {
      return topMessage;
   }


   public String getImgUrl() {
      return imgUrl;
   }

}
