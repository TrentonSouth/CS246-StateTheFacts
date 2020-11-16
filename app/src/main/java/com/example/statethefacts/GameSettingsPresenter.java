package com.example.statethefacts;


import java.util.prefs.Preferences;

public class GameSettingsPresenter {
    GameSettings gameSettings;

    public void saveSettings() {
        //https://docs.oracle.com/javase/8/docs/technotes/guides/preferences/overview.html
        Preferences preferences = Preferences.userNodeForPackage(GameSettingsPresenter.class);
    }

    public void getSettings() {
        //https://dev.to/argherna/the-java-preferences-api-is-a-little-thing-thats-a-huge-benefit-13ac
        //can do for each setting possible?
    }

    public void closeSettings() {

    }

    public void startGame() {

    }


}
