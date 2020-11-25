package com.example.statethefacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public static final String GAMETYPE = "com.example.statethefacts.GAMETYPE";
    public static final String START_NEW_GAME = "com.example.statethefacts.START_NEW_GAME";

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

        // Access field in form
        TextView editUserWelcome = (TextView) findViewById(R.id.user_welcome);

        // update field in form
        if(userName != null) {
            String welcomeText = "Welcome back, " + userName + "!";
            editUserWelcome.setText(welcomeText);
        }

    }

    public void userProfile(View view) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void showChartsExample(View view) {
        Intent intent = new Intent(this, ChartsExampleActivity.class);
        startActivity(intent);
    }

    public void showAnswer(View view){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(GAMETYPE, GameType.TextEntry.ordinal());
        intent.putExtra(START_NEW_GAME, false);
        startActivity(intent);
    }


    public void loadScoreCard(View view){
        Intent intent = new Intent(this, ScoreCardActivity.class);
        startActivity(intent);
    }

    public void showFactsList(View view) {
        Intent intent = new Intent(this, FactsListActivity.class);
        startActivity(intent);
    }

    public void loadSettings(View view){
        Intent intent = new Intent(this, GameSettingsActivity.class);
        startActivity(intent);
    }


}