package com.example.statethefacts;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

/**
 * MainUserProfilePresenter: The interface between UserProfile class and
 * MainActivity. Provides MainActivity with methods and access to fields for
 * the User's name, Email address, and age.
 *
 *  @author Michael Gibson
 *  @version 1.0
 *  @since 12/8/2020
 */
public class MainUserProfilePresenter {
    private static final String TAG = "MainUserProfile";
    private MainActivity activity;
    private UserProfile profile;

    public UserProfile getProfile() {
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
        profile = new UserProfile(preferences.getString("user_name", null),
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
