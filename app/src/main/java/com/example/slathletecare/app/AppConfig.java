package com.example.slathletecare.app;

public class AppConfig {

    //Server base url
    //ipconfig getifaddr en0
    public static String URL_BASE = "http://192.168.8.106/SL-athlete-care/api/v1";

    // Server user register url
    public static String URL_REGISTER = URL_BASE+"/SignUp.php";

    //Server OTP Request url
    public static String OTP_REQUEST = URL_BASE+"/OTP.php";

    // Server user login url
    public static String URL_LOGIN= URL_BASE+"/Login.php";

    // Server user forget password url
    public static String URL_FORGET= URL_BASE+"/changePassword.php";

    // Server chat url
    public static String URL_CHAT= URL_BASE+"/chat.php";

    // Server data injury
    public static String URL_INJURYDATA= URL_BASE+"/injuryData.php";
    // Server data injury
    public static String URL_INJURY= URL_BASE+"/injury.php";
    // Server data health
    public static String URL_ADD_HEALTH= URL_BASE+"/addhealth.php";
    // Server data health
    public static String URL_GHEALTH= URL_BASE+"/gethealth.php";
    // Server data health
    public static String URL_USER= URL_BASE+"/getUserData.php";

    // Server data health
    public static String URL_SPORTS= URL_BASE+"/getUserSports.php";

    // Server data sport
    public static String URL_ADD_SPORT= URL_BASE+"/addSportsData.php";
    // Server data sport
    public static String URL_ADD_Q= URL_BASE+"/addQuestion.php";

    // Update user data
    public static String URL_UPDATE_USER= URL_BASE+"/updateUserData.php";

    // get case study data
    public static String URL_CASE_STUDY_ALL= URL_BASE+"/getAllCaseStudy.php";


    // get case study data
    public static String URL_CASE_STUDY_UPDATES= URL_BASE+"/getUpdates.php";


    // get medicine data
    public static String URL_CASE_STUDY_MEDICINE= URL_BASE+"/getMedicine.php";

    // get diet data
    public static String URL_CASE_STUDY_DIET= URL_BASE+"/getDiet.php";

    // get workout data
    public static String URL_CASE_STUDY_WORKOUT= URL_BASE+"/getWorkout.php";

    // get image data
    public static String URL_CASE_STUDY_IMAGE= URL_BASE+"/getImage.php";

    // get advices data
    public static String URL_CASE_STUDY_ADVICE= URL_BASE+"/getAdvices.php";


 // get case study data
    public static String URL_SCHEDULES= URL_BASE+"/getSchedules.php";
//
  // get case study data
    public static String URL_WORKOUT_EVENTS= URL_BASE+"/getWorkoutEvents.php";
    // get case study data
    public static String URL_DIET_EVENTS= URL_BASE+"/getDietEvents.php";

    public static String URL_ADD_FEEDBACKS =URL_BASE+"/addFeedbacks.php";

    public static String URL_GET_FEEDBACKS =URL_BASE+"/getFeedbacks.php";
    public static String URL_GET_ALL =URL_BASE+"/getAll.php";

    public static String URL_GET_FORUM =URL_BASE+"/getUserForum.php";

    public static String URL_ADD_COMMENTS =URL_BASE+"/addComment.php";
    public static String URL_GET_COMMENTS =URL_BASE+"/getComments.php";

}
