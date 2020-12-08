package com.example.statethefacts;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class ScoreCardActivity extends AppCompatActivity {
    private ScoreCardPresenter presenter;
    public static final String GAME_MODE = "com.example.statethefacts.GAME_MODE";
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        presenter = new ScoreCardPresenter(this);
        presenter.calculateScores(this);
        TextView txtScore = this.findViewById(R.id.txtScore);
        txtScore.setText(presenter.getScoreAsString() + "%");
        TextView txtAverage = this.findViewById(R.id.txtAverage);
        txtAverage.setText(presenter.getAverageAsString() + "%");

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