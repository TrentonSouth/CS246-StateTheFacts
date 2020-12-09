package com.example.statethefacts;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

/**
 * MainUserProfilePresenter cast is the user profile presenter for
 * MainActivity. Provides MainActivity with methods and access to fields for
 * the User's name and Email address.
 */
public class MainUserProfilePresenter {
    private static final String TAG = "MainUserProfile";
    private MainActivity activity;
    private UserProfilePresenter profile;

    public UserProfilePresenter getProfile() {
        return profile;
    }

    /**
     * MainUserProfilePresenter constructor creates an object that contains user profile
     * information, if it exists and updates the welcome screen with the user's name
     * @param activity
     */
    public MainUserProfilePresenter(MainActivity activity) {
        this.activity = activity;
        SharedPreferences preferences = activity.getSharedPreferences("STFUserProfile", 0);
        profile = new UserProfilePresenter(preferences.getString("user_name", null),
                preferences.getString("user_email", null),
                preferences.getString("user_age", null));
        Log.i(TAG, "Loaded user information from STFUserProfile");

        // if the user has input their name as part of the profile, the welcome screen
        // gets updated with their name.
        if (profile.getUserName() != null) {
            TextView editUserWelcome = activity.findViewById(R.id.user_welcome);
            String welcomeText = "Welcome back, " + profile.getUserName() + "!";
            editUserWelcome.setText(welcomeText);
        }
    }
}
