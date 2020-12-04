package com.example.statethefacts;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class ScoreCardActivity extends AppCompatActivity {
    public static final String GAME_MODE = "com.example.statethefacts.GAME_MODE";
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        // Get the current game results
        GameResult gr = new GameResult().loadCurrentGame(this);
        List<GameAnswer> loga = gr.getAnswers();
        int qCount = 0;
        int qRight = 0;
        for (GameAnswer a : loga)
        {
            qCount += 1;
            if(a.HasCorrectAnswer())
                qRight += 1;
        }
        float score = qRight / qCount;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String scoreAsString = decimalFormat.format(score);
        TextView txtScore = this.findViewById(R.id.txtScore);
        txtScore.setText(txtScore.getText());
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