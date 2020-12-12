package com.example.statethefacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 *  This class initializes the History view and pipes events to the presenter
 *
 * @author Gene Higgins
 * @since 12/1/2020
 */
public class HistoryActivity extends AppCompatActivity {

    public static final String GAME_MODE = "com.example.statethefacts.GAME_MODE";
    private HistoryPresenter presenter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        presenter = new HistoryPresenter(this); // Create presenter
        presenter.loadGameResult(); // Load historical game data
        presenter.getStats(); // calculate stats and chart data
    }

    /**
     * The onCreateOptionsMenu Method
     * Purpose: create the 3 dots (ellipsis) on right side of the app in the top bar
     *
     * @param menu menu to be displayed
     * @return boolean as true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //https://www.youtube.com/watch?v=kknBxoCOYXI
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }

    /**
     * onOptionsItemSelected Method
     * Purpose: to listen to the user action when they select a menu item from the 3 dots
     * (ellipsis) on the upper right side of the app, in the top bar. Navigates to specific
     * activity based on user selection
     *
     * @param item selected menu item
     * @return true if successful
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

    /**
     * sendEmailButton - method checks for a valid user name and email before launching the
     * sendMessage() method, which sends the user's history statistics.
     *
     * @param view
     */
    public void sendEmailButton(View view) {
        EmailPresenter email = new EmailPresenter(HistoryActivity.this);
        if (email.getProfile().getUserName() != null && email.getProfile().getUserEMail() != null) {
            email.sendMessage();
        } else {
            // toast to notify user there isn't a saved profile
            Context context = getApplicationContext();
            CharSequence text = "No User Profile. Please enter user profile.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public HistoryPresenter getPresenter() {
        return presenter;
    }

}