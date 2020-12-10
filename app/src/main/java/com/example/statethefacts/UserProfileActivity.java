package com.example.statethefacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * UserProfileActivity: provides a place for the user to input
 * and save their profile information, including name, email, and age
 */
public class UserProfileActivity extends AppCompatActivity {
    /** define variables and constants */
    private static final String TAG = "UserProfileActivity";
    private UserProfilePresenter profile;
    private Intent intent;

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
     * onOptionsItemSelected Method
     * Purpose: to listen to the user action when they select a menu item from the 3 dots
     * (ellipsis) on the upper right side of the app, in the top bar. Navigates to specific
     * activity based on user selection
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.history:
                intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.score_card:
                intent = new Intent(this, ScoreCardActivity.class);
                startActivity(intent);
                break;
            case R.id.learn_mode:
                intent = new Intent(this, FactsListActivity.class);
                startActivity(intent);
                break;
            case R.id.game_mode:
                intent = new Intent(this, GameSettingsActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * saveProfile: called when user clicks Save Profile button
     */
    public void saveProfile(View view) {
        profile.saveProfile(UserProfileActivity.this);
    }
}