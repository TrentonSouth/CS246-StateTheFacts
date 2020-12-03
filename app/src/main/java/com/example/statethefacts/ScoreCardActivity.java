package com.example.statethefacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ScoreCardActivity extends AppCompatActivity {
    public static final String GAME_MODE = "com.example.statethefacts.GAME_MODE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
    }
    public void loadHistory(View view) {
        //Intent intent = new Intent(this, HistoryActivity.class);
        //startActivity(intent);
    }
    public void loadMissedQuestions(View view) {
        Intent intent = new Intent(this, MissedQuestionsActivity.class);
        startActivity(intent);
    }
    public void loadPlayAgain(View view) {
        Intent intentGame = new Intent(this, GameSettingsActivity.class);
        // add game to Intent
        intentGame.putExtra(GAME_MODE, "game");

        // start activity
        startActivity(intentGame);
    }
    public void loadMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}