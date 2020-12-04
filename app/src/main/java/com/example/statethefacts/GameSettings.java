
package com.example.statethefacts;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class GameSettings {

    private boolean capital;
    private boolean rock;
    private boolean bird;
    private boolean flower;
    private boolean governor;

    private boolean multipleChoice;

    private GameType gameType;
    private GameSettings gameSettings;
    private GameSettingsPresenter gameSettingsPresenter;

    public GameSettings() {

    }

    public void SaveSettings(Context context){
        //https://github.com/macbeth-byui/CS246_Class/blob/master/SharedPrefExample/app/src/main/java/macbeth/sharedprefexample/MainActivity.java
        //https://developer.android.com/training/data-storage/shared-preferences
        //https://gist.github.com/yochiro/de99920a9ad3ab37c88c63e3409bdaf4

        SharedPreferences sharedPreferences = context.getSharedPreferences("GameSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("capital", capital);
        editor.putBoolean("rock", rock);
        editor.putBoolean("bird", bird);
        editor.putBoolean("flower", flower);
        editor.putBoolean("governor", governor);
        //editor.putBoolean("multipleChoice", multipleChoice);
        editor.putInt("GameType", gameType.ordinal());
        editor.commit();
    }

    public void LoadSettings(Context context){
        // todo add my loading code
        //https://github.com/macbeth-byui/CS246_Class/blob/master/SharedPrefExample/app/src/main/java/macbeth/sharedprefexample/MainActivity.java

        String loadSettings = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("GameSettings", Context.MODE_PRIVATE);
        capital = sharedPreferences.getBoolean("capital",true);
        rock = sharedPreferences.getBoolean("rock", false);
        bird = sharedPreferences.getBoolean("bird", false);
        flower = sharedPreferences.getBoolean("flower", false);
        governor = sharedPreferences.getBoolean("governor", false);
        gameType = GameType.values()[sharedPreferences.getInt("GameType", GameType.MultipleChoice.ordinal())];

        //multipleChoice = sharedPreferences.getBoolean("multipleChoice", true);

        // convert a enum to an int and back
        ///bad, don't try this int myInt = GameType.TextEntry;
//        int myGameTypeInteger = GameType.MultipleChoice.ordinal();
//        GameType gameType =  GameType.values()[myGameTypeInteger];
//        myGameTypeInteger = gameType.ordinal();


    }



    public boolean getCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public boolean getRock() {
        return rock;
    }

    public void setRock(boolean rock) {
        this.rock = rock;
    }

    public boolean getBird() {
        return bird;
    }

    public void setBird(boolean bird) {
        this.bird = bird;
    }

    public boolean getFlower() {
        return flower;
    }

    public void setFlower(boolean flower) {
        this.flower = flower;
    }

    public boolean getGovernor() {
        return governor;
    }

    public void setGovernor(boolean governor) {
        this.governor = governor;
    }

    /*public void setMultipleChoice(boolean multipleChoice) {
        this.multipleChoice = multipleChoice;
    }*/

    /*public boolean getMultipleChoice() {
        return multipleChoice;
    }*/

    /*use for GamePreferences
    public int getGameTypeInt() {
        return gameType.ordinal();
    }*/

    /*use for GamePreferences
    public void setGameTypeInt(int gameType) {
        this.gameType = GameType.values()[gameType];
    }*/

    //use for Game
    public GameType getGameType() {
        return gameType;
    }

    //use for Game
    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

}
