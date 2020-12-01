package com.example.statethefacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // constants and variables
    private UserProfilePresenter profile;
    private String msg;
    public static final String TAG = "MainActivity";
    public static final String GAMETYPE = "com.example.statethefacts.GAMETYPE";
    public static final String START_NEW_GAME = "com.example.statethefacts.START_NEW_GAME";
    public static final String GAME_MODE = "com.example.statethefacts.GAME_MODE";
    public static final String USER = "com.example.statethefacts.USER";
    public static final String EMAIL = "com.example.statethefacts.EMAIL";
    public static final String HASPROFILE = "com.example.statethefacts.HASPROFILE";

    // initial method for launch
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadFacts facts = new LoadFacts(this);
        Thread thread = new Thread(facts);
        thread.start();

        // load preference file
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("userProfile", 0);
        String userName = preferences.getString("user_name", null);
        String userEMail = preferences.getString("user_email", null);

        profile = new UserProfilePresenter(userName, userEMail);
        // Access field in form
        TextView editUserWelcome = findViewById(R.id.user_welcome);

        // update field in form
        if(profile.getUserName() != null) {
            String welcomeText = "Welcome back, " + profile.getUserName() + "!";
            editUserWelcome.setText(welcomeText);
        }

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
                Intent intentPractice = new Intent(this, GameSettingsActivity.class);
                // log information
                msg = "User intent: Practice";
                Log.d(TAG, msg);

                // add practice to Intent
                intentPractice.putExtra(GAME_MODE, "practice");

                // start activity
                startActivity(intentPractice);
                break;
            case R.id.button_gameMode:
                Intent intentGame = new Intent(this, GameSettingsActivity.class);
                // log information
                msg = "User intent: Game";
                Log.d(TAG, msg);

                // add game to Intent
                intentGame.putExtra(GAME_MODE, "game");

                // start activity
                startActivity(intentGame);
                break;
            case R.id.button_history:
                break;
            case R.id.profile_change:
                Intent intent1 = new Intent(this, UserProfileActivity.class);
                if (profile.getUserName() != null && !profile.getUserName().isEmpty()) {
                    // log message
                    msg = "Profile information: " + profile.getUserName()
                            + " , " + profile.getUserEMail();
                    Log.d(TAG, msg);

                    // add user profile to intent
                    intent1.putExtra(HASPROFILE, "yes");
                    intent1.putExtra(USER, profile.getUserName());
                    intent1.putExtra(EMAIL, profile.getUserEMail());
                } else {
                    // no user profile
                    intent1.putExtra(HASPROFILE, "no");
                }
                // start activity
                startActivity(intent1);
                break;
            case R.id.button:
                Intent intent2 = new Intent(this, ScoreCardActivity.class);
                startActivity(intent2);
                break;
            case R.id.facts_list:
                Intent intent3 = new Intent(this, FactsListActivity.class);
                startActivity(intent3);
                break;
            case R.id.button_charts_example:
                Intent intent4 = new Intent(this, ChartsExampleActivity.class);
                startActivity(intent4);
                break;
            case R.id.button_show_answer:
                Intent intent5 = new Intent(this, GameActivity.class);
                intent5.putExtra(GAMETYPE, GameType.TextEntry.ordinal());
                intent5.putExtra(START_NEW_GAME, false);
                startActivity(intent5);
                break;
            case R.id.button4:
                Intent intent6 = new Intent(this, GameSettingsActivity.class);
                startActivity(intent6);
                break;
            default:
                break;
        }
    }

    /**
    public void launchGameMode(View view) {
        Intent intent = new Intent(this, GameSettingsActivity.class);

        // get game mode

    } */

}