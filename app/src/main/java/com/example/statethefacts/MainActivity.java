package com.example.statethefacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

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

    public void startGame(View view){
        Intent intent = new Intent(this, GameQuestionActivity.class);
        intent.putExtra("gameType", GameType.MultipleChoice);
        startActivity(intent);
    }

    public void getFacts(View view) {
        GetFacts gf = new GetFacts();
        Facts facts = gf.Fetch(this);
        Log.d("Message:", "Value is: " + facts.states.get(0).abbreviation);
    }
}