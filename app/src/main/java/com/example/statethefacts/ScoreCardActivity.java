package com.example.statethefacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ScoreCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
    }
    public void loadHistory() {
        //Intent intent = new Intent(this, HistoryActivity.class);
        //startActivity(intent);
    }
    public void loadMissedQuestions() {
        Intent intent = new Intent(this, MissedQuestionsActivity.class);
        startActivity(intent);
    }
    public void loadPlayAgain() {
        Intent intent = new Intent(this, GameSettingsActivity.class);
        startActivity(intent);
    }
    public void loadMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}