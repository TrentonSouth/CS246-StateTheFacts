package com.example.statethefacts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameSettingsActivity extends AppCompatActivity {

    private GameSettingsPresenter gameSettingsPresenter;

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

    public void onClickStartGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(MainActivity.GAMETYPE, gameSettingsPresenter.gameSettings.getGameType());
        intent.putExtra(MainActivity.START_NEW_GAME, true);
        startActivity(intent);
    }


    /**
     *
     * @param view
     */
    public void handleCheckBox(View view) {
        gameSettingsPresenter.updateFactTypes(view);
    }

    /**
     *
     * @param view
     */
    public void handleRadioButtons(View view) {
        gameSettingsPresenter.updateGameType(view);
    }
}