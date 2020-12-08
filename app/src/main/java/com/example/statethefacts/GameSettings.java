
package com.example.statethefacts;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * The GameSettings Class
 * represents the game settings
 */
public class GameSettings {

    private boolean capital;
    private boolean rock;
    private boolean bird;
    private boolean flower;
    private boolean governor;
    private int numberOfGames;

    private GameType gameType;
    //private GameSettings gameSettings;
    //private GameSettingsPresenter gameSettingsPresenter;

    /**
     * Construct a default game settings
     */
    public GameSettings() {
    }

    /**
     * the saveGameSettings Method
     * Purpose: to save capital, rock, bird, flower, governor, and gameType as a Boolean
     * @param context
     */
    public void saveGameSettings(Context context){
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
        editor.putInt("GameType", gameType.ordinal());
        editor.commit();
    }

    /**
     * the loadGameSettings Method
     * Purpose: to get the boolean of capital, rock, bird, flower, governor, and gameType
     * @param context
     */
    public void loadGameSettings(Context context){
        //https://github.com/macbeth-byui/CS246_Class/blob/master/SharedPrefExample/app/src/main/java/macbeth/sharedprefexample/MainActivity.java
        SharedPreferences sharedPreferences = context.getSharedPreferences("GameSettings", Context.MODE_PRIVATE);
        capital = sharedPreferences.getBoolean("capital",true);
        rock = sharedPreferences.getBoolean("rock", false);
        bird = sharedPreferences.getBoolean("bird", false);
        flower = sharedPreferences.getBoolean("flower", false);
        governor = sharedPreferences.getBoolean("governor", false);
        gameType = GameType.values()[sharedPreferences.getInt("GameType", GameType.MultipleChoice.ordinal())];
    }


    /**
     * the getCapital Method
     * Purpose: to get the capital as a boolean
     * @return capital as a boolean
     */
    public boolean getCapital() { return capital; }

    /**
     * the setCapital Method
     * Purpose: to set the capital as a boolean
     * @param capital
     */
    public void setCapital(boolean capital) { this.capital = capital; }

    /**
     * the getRock Method
     * Purpose: to get the rock as a boolean
     * @return rock as a boolean
     */
    public boolean getRock() {
        return rock;
    }

    /**
     * the setRock Method
     * Purpose: to set the rock as a boolean
     * @param rock
     */
    public void setRock(boolean rock) {
        this.rock = rock;
    }

    /**
     * the getBird Method
     * Purpose: to get the bird as a boolean
     * @return bird as a boolean
     */
    public boolean getBird() { return bird; }

    /**
     * the setBird Method
     * Purpose: to set the bird as a boolean
     * @param bird
     */
    public void setBird(boolean bird) { this.bird = bird; }

    /**
     * the getFlower Method
     * Purpose: to get the flower as a boolean
     * @return flower as a boolean
     */
    public boolean getFlower() { return flower; }

    /**
     * the setFlower Method
     * Purpose: to set the flower as a boolean
     * @param flower
     */
    public void setFlower(boolean flower) { this.flower = flower; }

    /**
     * the getGovernor Method
     * Purpose: to get the governor as a boolean
     * @return governor as a boolean
     */
    public boolean getGovernor() { return governor; }

    /**
     * the setGovernor Method
     * Purpose: to set the governor as a boolean
     * @param governor
     */
    public void setGovernor(boolean governor) { this.governor = governor; }

    /**
     * the getGameTypeMethod
     * Purpose: to get whether the game is multiple choice or text entry
     * @return gameType
     */
    public GameType getGameType() { return gameType; }

    /**
     * the setGameType Method
     * Purpose: set the type of game. Multiple choice or text entry
     * @param gameType
     */
    public void setGameType(GameType gameType) { this.gameType = gameType; }

    /**
     * the getNumberOfGames Method
     * Purpose: to get the number of games as an integer
     * @return number of games as an integer
     */
    public int getNumberOfGames() { return numberOfGames; }

    /**
     * the setNumberOfGames Method
     * Purpose: to set the number of games as an integer
     * @param numberOfGames
     */
    public void setNumberOfGames(int numberOfGames) { this.numberOfGames = numberOfGames; }

}
