package com.example.statethefacts;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class GameSettingsActivity extends AppCompatActivity {

    boolean checkBoxCapital;
    boolean checkBoxFlower;
    boolean checkBoxRock;
    boolean checkBoxGovernor;
    boolean checkBoxBird;
    boolean radioButtonMultipleChoice;
    boolean radioButtonTextEntry;
    GameSettingsPresenter gameSettingsPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_settings);
    }

    //https://developer.android.com/guide/topics/ui/controls/checkbox#:~:text=To%20define%20the%20click%20event,then%20implement%20the%20corresponding%20method.
    public void handleCheckBox(View view) {
        boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()) {
            case R.id.checkBoxCapital:
                if (checked) {
                    checkBoxCapital = true;
                    break;
                }
            case R.id.checkBoxFlower:
                if (checked) {
                    checkBoxFlower = true;
                    break;
                }
            case R.id.checkBoxRock:
                if (checked) {
                    checkBoxRock = true;
                }
            case R.id.checkBoxGovernor:
                if (checked) {
                    checkBoxGovernor = true;
                    break;
                }
            case R.id.checkBoxBird:
                if (checked) {
                    checkBoxBird = true;
                    break;
                }


        }
    }

    //https://developer.android.com/guide/topics/ui/controls/radiobutton
    public void handleRadioButtons(View view) {
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

    //will go to practice mode or game mode

}