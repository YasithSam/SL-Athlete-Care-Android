package com.example.slathletecare.app;

public class AppConfig {

    //Server base url
    public static String URL_BASE = "http://192.168.8.107/SL-athlete-care/api/v1";

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

}
