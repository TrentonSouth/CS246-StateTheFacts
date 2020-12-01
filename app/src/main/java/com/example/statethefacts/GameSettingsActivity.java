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
    private boolean isGame;
    private String mode;
    public static final String TAG = "GameSettingActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_settings);

        // get intent - determine whether game or practice mode
        Intent intent = getIntent();
        String game_mode = intent.getStringExtra(MainActivity.GAME_MODE);
        if (game_mode.equals("game")) {
            mode = "Start Game";
            isGame = true;
        } else {
            mode = "Start Practice";
            isGame = false;
        }
        // log receipt of intent
        String msg = "Received intent: " + game_mode;
        Log.d(TAG, msg);

        // set button text to match mode
        TextView button = findViewById(R.id.buttonStartGame);
        button.setText(mode);

        GameSettings gs = new GameSettings();
        gs.LoadSettings(this);

        CheckBox capital = findViewById(R.id.checkBoxCapital);
        capital.setChecked(gs.getCapital());

        CheckBox flower = findViewById(R.id.checkBoxFlower);
        flower.setChecked(gs.getFlower());

        CheckBox rock = findViewById(R.id.checkBoxRock);
        rock.setChecked(gs.getRock());

        CheckBox governor = findViewById(R.id.checkBoxGovernor);
        governor.setChecked(gs.getGovernor());

        CheckBox bird = findViewById(R.id.checkBoxBird);
        bird.setChecked(gs.getBird());

        RadioButton multipleChoice = findViewById(R.id.radioButtonMultipleChoice);

        RadioButton textEntry = findViewById(R.id.radioButtonTextEntry);

        if (gs.getMultipleChoice()) {
            multipleChoice.setChecked(true);
            textEntry.setChecked(false);
        } else {
            textEntry.setChecked(true);
            multipleChoice.setChecked(false);
        }
    }


    /**
     *
     * @param view
     */
    public void handleCheckBox(View view) {
        //https://developer.android.com/guide/topics/ui/controls/checkbox#:~:text=To%20define%20the%20click%20event,then%20implement%20the%20corresponding%20method.
        boolean checked = ((CheckBox) view).isChecked();
        Log.d("message: ", "testing to see if we got here");


        GameSettings gs = new GameSettings();
        gs.LoadSettings(this);

        switch (view.getId()) {
            case R.id.checkBoxCapital:
                gs.setCapital(checked);
                break;
            case R.id.checkBoxFlower:
                gs.setFlower(checked);
                break;
            case R.id.checkBoxRock:
                gs.setRock(checked);
                break;
            case R.id.checkBoxGovernor:
                gs.setGovernor(checked);
                break;
            case R.id.checkBoxBird:
                gs.setBird(checked);
                break;

        }

        gs.SaveSettings(this);
    }

    /**
     *
     * @param view
     */
    public void handleRadioButtons(View view) {
        //https://developer.android.com/guide/topics/ui/controls/radiobutton
        boolean checked = ((RadioButton)view).isChecked();

        GameSettings gs = new GameSettings();
        gs.LoadSettings(this);

        switch (view.getId()) {
            case R.id.radioButtonMultipleChoice:
                if (checked) {
                    gs.setMultipleChoice(true);
                }
                break;
            case R.id.radioButtonTextEntry:
                if (checked) {
                    gs.setMultipleChoice(false);
                }
                break;
        }
        gs.SaveSettings(this);
    }
}