package com.example.statethefacts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity: Start screen and launch activity. User accesses the rest of the app
 * from this activity.
 *
 * @author Michael Gibson
 * @version 1.0
 * @since 12/8/2020
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // constants and variables
    private MainUserProfilePresenter userProfile;
    private String msg;
    private Intent intent;
    public static final String TAG = "MainActivity";
    public static final String START_NEW_GAME = "com.example.statethefacts.START_NEW_GAME";
    public static final String GAME_MODE = "com.example.statethefacts.GAME_MODE";

    // initial method for launch
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadFacts facts = new LoadFacts(this);
        Thread thread = new Thread(facts);
        thread.start();

        // creates a new MainUserProfilePresenter object with user profile information
        // updates welcome message if there is a profile
        userProfile = new MainUserProfilePresenter(MainActivity.this);
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
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * The onOptionsItemSelected Method
     * Purpose: makes the list found from the 3 dots (ellipsis) on right side of the app clickable
     *
     * @param item
     * @return boolean as true
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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

    /**
     * onClick watches the button selection to direct the user to the proper activity
     * based on their choice.
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_practiceMode:
                intent = new Intent(this, FactsListActivity.class);
                // log information
                msg = "User intent: Practice";
                Log.d(TAG, msg);

                // add practice to Intent
                intent.putExtra(GAME_MODE, "practice");

                // start activity
                startActivity(intent);
                break;
            case R.id.button_gameMode:
                intent = new Intent(this, GameSettingsActivity.class);
                // log information
                msg = "User intent: Game";
                Log.d(TAG, msg);

                // add game to Intent
                intent.putExtra(GAME_MODE, "game");

                // start activity
                startActivity(intent);
                break;
            case R.id.button_history:
                intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_change:
                intent = new Intent(this, UserProfileActivity.class);

                // start activity
                startActivity(intent);
                break;
            default:
                break;
        }
    }


}