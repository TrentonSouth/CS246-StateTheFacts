package com.example.statethefacts;


import java.util.prefs.Preferences;

public class GameSettingsPresenter {
    GameSettings gameSettings;

    public void saveSettings() {
        //https://docs.oracle.com/javase/8/docs/technotes/guides/preferences/overview.html
        Preferences preferences = Preferences.userNodeForPackage(GameSettingsActivity.class);
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
