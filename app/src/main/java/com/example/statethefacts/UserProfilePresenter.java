
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

    public UserProfilePresenter(String user, String email) {
        profile = new UserProfile(user, email);
    }
}
