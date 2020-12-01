package com.example.statethefacts;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class GameSettingsActivity extends AppCompatActivity {

    private GameSettingsPresenter gameSettingsPresenter;
    boolean checkBoxCapital;
    boolean checkBoxFlower;
    boolean checkBoxRock;
    boolean checkBoxGovernor;
    boolean checkBoxBird;
    boolean radioButtonMultipleChoice;
    boolean radioButtonTextEntry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_settings);
    }

    public void test(View view) {
        Log.d("message: ", "just logging a message");
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

        gameSettingsPresenter.saveSettings(this);
    }

    /**
     *
     * @param view
     */
    public void handleRadioButtons(View view) {
        //https://developer.android.com/guide/topics/ui/controls/radiobutton
        boolean checked = ((RadioButton)view).isChecked();

        switch (view.getId()) {
            case R.id.radioButtonMultipleChoice:
                if (checked) {
                    radioButtonMultipleChoice = true;
                    break;
                }
            case R.id.radioButtonTextEntry:
                if (checked) {
                    radioButtonTextEntry = true;
                    break;
                }
        }
    }
}