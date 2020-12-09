
package com.example.statethefacts;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfilePresenter extends AppCompatActivity {
    private UserProfile profile;

    public String getUserName() {
        return profile.getUserName();
    }

    public String getUserEMail() {
        return profile.getUserEMail();
    }

    public String getUserAge() { return profile.getUserAge(); }

    public UserProfilePresenter(String user, String email) {
        profile = new UserProfile(user, email);
    }

    public UserProfilePresenter(String user, String email, String age) {
        profile = new UserProfile(user, email, age);
    }
}
