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
        float score = getScore(gr);

        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        String scoreAsString = decimalFormat.format(score);
        TextView txtScore = this.findViewById(R.id.txtScore);

        if (score == 100.0) {
            scoreAsString = "100";
        }
        txtScore.setText(scoreAsString + "%");
        List<GameResult>  loag = gr.getAllGames(this);
        int gCount = 0;
        float total = 0;
        for (GameResult g : loag) {
            gCount = gCount + 1;
            total += getScore(g);
        }
        float totalScore;
        if (gCount > 0) {
            totalScore = total / gCount;
        } else {
            totalScore = 0;
        }
        scoreAsString = decimalFormat.format(totalScore);
        TextView txtAverage = this.findViewById(R.id.txtAverage);
        if (totalScore == 100.0) {
            scoreAsString = "100";
        }
        txtAverage.setText(scoreAsString + "%");
    }
    private float getScore(GameResult gr) {
        float qCount = 0;
        float qRight = 0;
        for (GameAnswer a : gr.getAnswers())
        {
            qCount += 1;
            if(a.HasCorrectAnswer())
                qRight += 1;
        }
        float score;
        if (qCount > 0) {
            score = (qRight / qCount) * 100;
        } else {
            score = 0;
        }
        return score;
    }

    public void loadHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
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