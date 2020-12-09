package com.example.statethefacts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // constants and variables
    private MainUserProfilePresenter userProfile;
    private String msg;
    private Intent intent;
    public static final String TAG = "MainActivity";
    public static final String GAMETYPE = "com.example.statethefacts.GAMETYPE";
    public static final String START_NEW_GAME = "com.example.statethefacts.START_NEW_GAME";
    public static final String GAME_MODE = "com.example.statethefacts.GAME_MODE";
    public static final String USER = "com.example.statethefacts.USER";
    public static final String EMAIL = "com.example.statethefacts.EMAIL";
    public static final String AGE = "com.example.statethefacts.AGE";
    public static final String HASPROFILE = "com.example.statethefacts.HASPROFILE";

    // initial method for launch
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadFacts facts = new LoadFacts(this);
        Thread thread = new Thread(facts);
        thread.start();

        // creates a new MainUserProfilePresenter object with user profile information
        // updates welcome message if there is a profile
        userProfile = new MainUserProfilePresenter(MainActivity.this);
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
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * The onOptionsItemSelected Method
     * Purpose: makes the list found from the 3 dots (ellipsis) on right side of the app clickable
     * @param item
     * @return boolean as true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //https://www.youtube.com/watch?v=zwabHRv2taA
        if (item.getItemId() == R.id.profile) {
            intent = new Intent(this, UserProfileActivity.class);
            if (userProfile.getProfile().getUserName() != null && !userProfile.getProfile().getUserName().isEmpty()) {
                // log message
                msg = "Profile information: " + userProfile.getProfile().getUserName()
                        + " , " + userProfile.getProfile().getUserEMail();
                Log.d(TAG, msg);

                // add user profile to intent
                intent.putExtra(HASPROFILE, "yes");
                intent.putExtra(USER, userProfile.getProfile().getUserName());
                intent.putExtra(EMAIL, userProfile.getProfile().getUserEMail());
                intent.putExtra(AGE, userProfile.getProfile().getUserAge());
            } else {
                // no user profile
                intent.putExtra(HASPROFILE, "no");
            }
            // start activity
            startActivity(intent);
        } else if (item.getItemId() == R.id.history) {
            intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.score_card) {
            intent = new Intent(this, ScoreCardActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.learn_mode) {
            intent = new Intent(this, FactsListActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.game_mode) {
            intent = new Intent(this, GameSettingsActivity.class);
            intent.putExtra(GAME_MODE, "game");
            startActivity(intent);
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * onClick watches the button selection to direct the user to the proper activity
     * based on their choice.
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_practiceMode:
                intent = new Intent(this, FactsListActivity.class);
                // log information
                msg = "User intent: Practice";
                Log.d(TAG, msg);

                // add practice to Intent
                intent.putExtra(GAME_MODE, "practice");

                // start activity
                startActivity(intent);
                break;
            case R.id.button_gameMode:
                intent = new Intent(this, GameSettingsActivity.class);
                // log information
                msg = "User intent: Game";
                Log.d(TAG, msg);

                // add game to Intent
                intent.putExtra(GAME_MODE, "game");

                // start activity
                startActivity(intent);
                break;
            case R.id.button_history:
                intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_change:
                intent = new Intent(this, UserProfileActivity.class);
                if (userProfile.getProfile().getUserName() != null && !userProfile.getProfile().getUserName().isEmpty()) {
                    // log message
                    msg = "Profile information: " + userProfile.getProfile().getUserName()
                            + " , " + userProfile.getProfile().getUserEMail() + ", "
                            + userProfile.getProfile().getUserAge();
                    Log.d(TAG, msg);

                    // add user profile to intent
                    intent.putExtra(HASPROFILE, "yes");
                    intent.putExtra(USER, userProfile.getProfile().getUserName());
                    intent.putExtra(EMAIL, userProfile.getProfile().getUserEMail());
                    intent.putExtra(AGE, userProfile.getProfile().getUserAge());
                } else {
                    // no user profile
                    intent.putExtra(HASPROFILE, "no");
                }
                // start activity
                startActivity(intent);
                break;
            default:
                break;
        }
    }


}