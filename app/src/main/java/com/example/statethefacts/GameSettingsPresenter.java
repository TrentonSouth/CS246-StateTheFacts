package com.example.statethefacts;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.prefs.Preferences;

public class GameSettingsPresenter {

    GameSettings gameSettings;
    GameSettingsActivity gameSettingsActivity;

    public void saveSettings() {
        gameSettings.setBird(gameSettingsActivity.checkBoxBird);
        gameSettings.setCapital(gameSettingsActivity.checkBoxCapital);
        gameSettings.setFlower(gameSettingsActivity.checkBoxFlower);
        gameSettings.setGovernor(gameSettingsActivity.checkBoxGovernor);
        gameSettings.setRock(gameSettingsActivity.checkBoxRock);

        gameSettings.SaveSettings();
    }

    public void getSettings() {
        gameSettings.LoadSettings();

    }


}
