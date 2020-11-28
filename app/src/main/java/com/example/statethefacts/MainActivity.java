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
    public static final String GAMETYPE = "com.example.statethefacts.GAMETYPE";
    public static final String START_NEW_GAME = "com.example.statethefacts.START_NEW_GAME";
    public static final String GAME_MODE = "com.example.statethefacts.GAME_MODE";

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
        if(userName != null) {
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
                break;
            case R.id.button_gameMode:
                break;
            case R.id.button_history:
                break;
            case R.id.profile_change:
                Intent intent1 = new Intent(this, UserProfileActivity.class);
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