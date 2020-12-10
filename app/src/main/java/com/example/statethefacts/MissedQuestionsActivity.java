package com.example.statethefacts;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * The MissedQuestionsActivity class is the backend for activity_missed_questions.xml
 * Its purpose is to call on the presenter to generate a display string that
 * can then be used to populate the text field on the form.
 *
 * @author Trenton South
 * @version 1.0
 * @since 12/8/2020
 */
public class MissedQuestionsActivity extends AppCompatActivity {
    public static final String GAME_MODE = "com.example.statethefacts.GAME_MODE";
    private MissedQuestionsPresenter presenter;
    Intent intent;

    // When the class is instantiated, the presenter is created
    // and the display is built from getDisplay()
    // that value is used to set the text of txtMissedQuestions
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missed_questions);

        presenter = new MissedQuestionsPresenter(this);
        // set txtMissedQuestions to the result of getDisplay from the presenter
        TextView txtMissedQuestions = this.findViewById(R.id.txtMissedQuestions);
        txtMissedQuestions.setText(presenter.getDisplay(this));
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
        getMenuInflater().inflate(R.menu.missedquestion_menu, menu);
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
            case R.id.score_card:
                intent = new Intent(this, ScoreCardActivity.class);
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

    // When the user clicks the main menu button, the main
    // activitiy is opened.
    public void mainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

} 