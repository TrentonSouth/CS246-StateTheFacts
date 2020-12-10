
package com.example.statethefacts;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfilePresenter {
    private static final String TAG = "UserProfileActivity";
    private UserProfile profile;
    private UserProfileActivity activity;

    // getters and setters
    public String getUserName() {
        return profile.getUserName();
    }
    public String getUserEMail() {
        return profile.getUserEMail();
    }
    public String getUserAge() { return profile.getUserAge(); }

    /**
     * UserProfilePresenter constructor creates an object using saved
     * data from SharedPreferences and updates the text entry fields
     * if the user has already saved the information.
     * @param activity
     */
    public UserProfilePresenter(UserProfileActivity activity){
        this.activity = activity;
        SharedPreferences preferences = activity.getSharedPreferences("STFUserProfile", 0);
        profile = new UserProfile(preferences.getString("user_name", null),
                preferences.getString("user_email", null),
                preferences.getString("user_age", null));
        Log.i(TAG, "Loaded user information from SharedPreferences");

        if (profile.getUserName() != null) {
            TextView editName = activity.findViewById(R.id.name_entry);
            editName.setText(profile.getUserName());
        }
        if (profile.getUserEMail() != null) {
            TextView editEmail = activity.findViewById(R.id.email_entry);
            editEmail.setText(profile.getUserEMail());
        }
        if (profile.getUserAge() != null) {
            TextView editAge = activity.findViewById(R.id.age_entry);
            editAge.setText(profile.getUserAge());
        }
    }

    public UserProfilePresenter(String user, String email) {
        profile = new UserProfile(user, email);
    }

    public UserProfilePresenter(String user, String email, String age) {
        profile = new UserProfile(user, email, age);
    }
}
