package com.example.statethefacts;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

import java.util.prefs.Preferences;

public class GameSettingsPresenter {
    GameSettings gameSettings;

    private EditText checkBoxCapital;
    private EditText checkBoxFlower;
    private EditText checkBoxRock;
    private EditText checkBoxGovernor;
    private EditText checkBoxBird;
    private EditText radioButtonMultipleChoice;
    private EditText radioButtonTextEntry;

    public void saveSettings(View view) {
        //https://github.com/macbeth-byui/CS246_Class/blob/master/SharedPrefExample/app/src/main/java/macbeth/sharedprefexample/MainActivity.java
        /*checkBoxCapital = findViewById(R.id.checkBoxCapital);
        checkBoxFlower = findViewById(R.id.checkBoxFlower);
        checkBoxRock = findViewById(R.id.checkBoxRock);
        checkBoxGovernor = findViewById(R.id.checkBoxGovernor);
        checkBoxBird = findViewById(R.id.checkBoxBird);
        radioButtonMultipleChoice = findViewById(R.id.radioButtonMultipleChoice);
        radioButtonTextEntry = findViewById(R.id.radioButtonTextEntry);

        String userGameSettingChoice = checkBoxCapital.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name", nameValue);
        editor.commit();*/
    }

    public void getSettings() {
        //https://dev.to/argherna/the-java-preferences-api-is-a-little-thing-thats-a-huge-benefit-13ac

    }

    public void closeSettings() {

    }

    public void startGame() {

    }

    //reference to activity will be needed
    //gamesettingsactitivity.sharedpreferences.activity
    //week 6

}
