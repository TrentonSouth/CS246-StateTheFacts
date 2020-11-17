
package com.example.statethefacts;

import android.content.SharedPreferences;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfilePresenter extends AppCompatActivity {
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
    public void setUserName(String userName) {
        profile.setUserName(userName);
    }
    public void setUserEMail(String userEMail) {
        profile.setUserEMail(userEMail);
    }
    // save profile information to shared preferences
    public void saveProfile(View view) {

    }
    // get profile information from shared preferences
    public void loadProfile() {
        // load preference file
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("userProfile",0);
        UserProfile newProfile = new UserProfile(preferences.getString("user_name", null),preferences.getString("user_email", null));
        setProfile(newProfile);
    }
}