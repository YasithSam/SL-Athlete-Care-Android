package com.example.slathletecare.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.slathletecare.activity.LoginActivity;

import java.util.HashMap;

public class SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();
    private static SharedPreferences.Editor editor;

    // Shared Preferences
    SharedPreferences pref;

    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "SLAthleteCareLogin";

    private static final String IS_LOGIN = "isLoggedIn";

    public static String username="Mahesh123";

    public static String userId="sl-ac-617e516484ac0";



    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static void createLoginSession(String id, String name) {
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(userId, id);
        // Storing name in pref
        editor.putString(username, name);

        // commit changes
        editor.commit();
    }



    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);

    }
    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(username, pref.getString(username, null));
        user.put(userId, pref.getString(userId, null));
        // return user
        return user;
    }

    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
