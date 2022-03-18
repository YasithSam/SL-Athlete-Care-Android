package com.example.slathletecare;

import android.app.Application;

import com.onesignal.OneSignal;

public class ApplicationClass extends Application {
    private static final String ONESIGNAL_APP_ID = "b80f6774-4e66-4724-849d-330fccdf7cb1";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable verbose OneSignal logging to debug issues if needed.
       // OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        //OneSignal.initWithContext(this);
        //OneSignal.setAppId(ONESIGNAL_APP_ID);
    }

}
