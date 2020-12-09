package com.example.statethefacts;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

public class MainUserProfilePresenter {
    private static final String TAG = "MainUserProfile";
    private MainActivity activity;
    private UserProfilePresenter profile;

    public UserProfilePresenter getProfile() {
        return profile;
    }

    public MainUserProfilePresenter(MainActivity activity) {
        this.activity = activity;
        SharedPreferences preferences = activity.getSharedPreferences("STFUserProfile", 0);
        profile = new UserProfilePresenter(preferences.getString("user_name", null),
                preferences.getString("user_email", null));
        Log.i(TAG, "Loaded user information from STFUserProfile");

        if (profile.getUserName() != null) {
            TextView editUserWelcome = activity.findViewById(R.id.user_welcome);
            String welcomeText = "Welcome back, " + profile.getUserName() + "!";
            editUserWelcome.setText(welcomeText);
        }
    }
}
