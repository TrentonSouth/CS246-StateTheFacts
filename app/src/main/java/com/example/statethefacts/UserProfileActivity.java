package com.example.statethefacts;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * UserProfileActivity: provides a place for the user to input
 * and save their profile information, including name, email, and age
 */
public class UserProfileActivity extends AppCompatActivity {
    /** define constants */
    private static final String TAG = "UserProfileActivity";
    private UserProfilePresenter profile;

    // getter
    public UserProfilePresenter getProfile() {
        return profile;
    }

    /**
     * Loads Activity using SharedPreferences
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profile = new UserProfilePresenter(UserProfileActivity.this);
    }

    /**
     * The onCreateOptionsMenu Method
     * Purpose: create the 3 dots (ellipsis) on right side of the app in the top bar
     * @param menu
     * @return boolean as true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //https://www.youtube.com/watch?v=kknBxoCOYXI
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    /**
     * saveProfile: called when user clicks Save Profile button
     */
    public void saveProfile(View view) {
        profile.saveProfile(UserProfileActivity.this);
    }
}