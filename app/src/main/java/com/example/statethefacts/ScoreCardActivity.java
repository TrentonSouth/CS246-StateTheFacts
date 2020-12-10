package com.example.statethefacts;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * The ScoreCardActivity class is the backend for activity_core_card.xml
 * Its purpose is to call on the presenter to generate a score for
 * the last quiz taken and for an average of all quizes.
 *
 * @author Trenton South
 * @version 1.0
 * @since 12/9/2020
 */
public class ScoreCardActivity extends AppCompatActivity {
    // Create a presenter to handle any major logic.
    private ScoreCardPresenter presenter;
    public static final String GAME_MODE = "com.example.statethefacts.GAME_MODE";
    Intent intent;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        presenter = new ScoreCardPresenter(this);
        // the calculateScores function in the presenter will
        // find the latest score and the average and set them
        // to variable values in the presenter.
        presenter.calculateScores(this);
        // Get the textView for score
        TextView txtScore = this.findViewById(R.id.txtScore);
        // Use the presenter to get the score in string format.
        txtScore.setText(presenter.getScoreAsString() + "%");
        // Get the textView for average
        TextView txtAverage = this.findViewById(R.id.txtAverage);
        // Use the presenter to get the average in string format.
        txtAverage.setText(presenter.getAverageAsString() + "%");
    }

    /**
     * The onCreateOptionsMenu Method
     * Purpose: create the 3 dots (ellipsis) on right side of the app in the top bar
     *
     * @param menu
     * @return boolean as true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //https://www.youtube.com/watch?v=kknBxoCOYXI
        getMenuInflater().inflate(R.menu.scorecard_menu, menu);
        return true;
    }
    /**
     * onOptionsItemSelected Method
     * Purpose: to listen to the user action when they select a menu item from the 3 dots
     * (ellipsis) on the upper right side of the app, in the top bar. Navigates to specific
     * activity based on user selection
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.profile:
                intent = new Intent(this, UserProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.history:
                intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.learn_mode:
                intent = new Intent(this, FactsListActivity.class);
                startActivity(intent);
                break;
            case R.id.game_mode:
                intent = new Intent(this, GameSettingsActivity.class);
                intent.putExtra(GAME_MODE, "game");
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * The loadHistory Method
     * Purpose: Go to the history activity
     *
     * @param view
     */
    public void loadHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    /**
     * The loadMissedQuestions Method
     * Purpose: Go to the missed questions activity
     *
     * @param view
     */
    public void loadMissedQuestions(View view) {
        Intent intent = new Intent(this, MissedQuestionsActivity.class);
        startActivity(intent);
    }

    /**
     * The loadPlayAgain Method
     * Purpose: Go to the game settings activity which is the
     * gateway to playing again.
     *
     * @param view
     */
    public void loadPlayAgain(View view) {
        Intent intentGame = new Intent(this, GameSettingsActivity.class);
        // add game to Intent
        intentGame.putExtra(GAME_MODE, "game");

        // start activity
        startActivity(intentGame);
    }

    /**
     * The loadMenu Method
     * Purpose: Go to the main activity
     *
     * @param view
     */
    public void loadMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}