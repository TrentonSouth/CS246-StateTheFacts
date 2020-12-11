package com.example.statethefacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class GameSettingsActivity extends AppCompatActivity {

    private GameSettingsPresenter gameSettingsPresenter;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_settings);

        // get intent - determine whether game or practice mode
        Intent intent = getIntent();
        String game_mode = intent.getStringExtra(MainActivity.GAME_MODE);
        String mode;
        if (game_mode.equals("game")) {
            mode = "Start Game";
        } else {
            mode = "Start Practice";
        }

        gameSettingsPresenter = new GameSettingsPresenter(this);
        gameSettingsPresenter.loadSettings(mode);
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
        getMenuInflater().inflate(R.menu.setting_menu, menu);
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
            case R.id.history:
                intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.score_card:
                intent = new Intent(this, ScoreCardActivity.class);
                startActivity(intent);
                break;
            case R.id.profile:
                intent = new Intent(this, UserProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.learn_mode:
                intent = new Intent(this, FactsListActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * the onClickStartGame Method
     * Purpose: if the user chooses a number between 1 and 50, the game will start
     * @param view
     */
    public void onClickStartGame(View view) {
        TextView numberOfFactsView = this.findViewById(R.id.numberOfFacts);
        String stringNumberOfFacts = numberOfFactsView.getText().toString();
        if(stringNumberOfFacts.length() == 0) {
            stringNumberOfFacts = "0";
        }
        int numberOfFacts = Integer.parseInt(stringNumberOfFacts);

        if (gameSettingsPresenter.updateNumberOfFacts(numberOfFacts) == true) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra(MainActivity.START_NEW_GAME, true);
            startActivity(intent);
        }
    }

    /**
     * the handleCheckBox Method
     * @param view the display of the checkboxes
     */
    public void handleCheckBox(View view) { gameSettingsPresenter.updateFactTypes(view); }

    /**
     * the handleRadioButtons Method
     * @param view the display of the radio buttons
     */
    public void handleRadioButtons(View view) { gameSettingsPresenter.updateGameType(view); }
}