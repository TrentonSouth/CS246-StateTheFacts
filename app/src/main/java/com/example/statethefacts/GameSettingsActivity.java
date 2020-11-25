package com.example.statethefacts;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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

        switch (view.getId()) {
            case R.id.checkBoxCapital:
                if (checked) {
                    checkBoxCapital = true;
                } else checkBoxCapital = false;
                break;
            case R.id.checkBoxFlower:
                if (checked) {
                    checkBoxFlower = true;
                } else checkBoxFlower = false;
                break;
            case R.id.checkBoxRock:
                if (checked) {
                    checkBoxRock = true;
                } else checkBoxRock = false;
                break;
            case R.id.checkBoxGovernor:
                if (checked) {
                    checkBoxGovernor = true;
                } else checkBoxGovernor = false;
                break;
            case R.id.checkBoxBird:
                if (checked) {
                    checkBoxBird = true;
                } else checkBoxBird = false;
                break;
        }
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