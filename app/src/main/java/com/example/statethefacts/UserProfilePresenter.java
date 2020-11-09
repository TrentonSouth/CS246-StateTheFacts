package com.example.statethefacts;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

public class UserProfilePresenter {
    private UserProfile profile;

    public UserProfile getProfile() {
        return profile;
    }
    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
    public String getUserName() {
        return profile.getUserName();
    }
    public String getUserEMail() {
        return profile.getUserEMail();
    }
    // save profile information to shared preferences
    public void saveProfile(View view) {

    }
    // get profile information from shared preferences
    public void loadProfile(View view) {
        // load preference file
        // SharedPreferences preferences =
    }
}
