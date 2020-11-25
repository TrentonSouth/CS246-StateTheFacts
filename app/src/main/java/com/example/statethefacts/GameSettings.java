
package com.example.statethefacts;

import java.util.List;

public class GameSettings {


    private boolean capitol;
    private boolean rock;
    private boolean bird;
    private boolean flower;
    private boolean governor;

    private GameType gameType;


    public GameSettings() {

    }

    public void SaveSettings(){
        // todo add my loading code
    }

    public void LoadSettings(){
        // todo add my loading code

        flower = true;
        rock = false;

        // convert a enum to and int and back
        ///bad, don't try this int myInt = GameType.TextEntry;
        int myGameTypeInteger = GameType.MultipleChoice.ordinal();
        GameType gameType =  GameType.values()[myGameTypeInteger];
        myGameTypeInteger = gameType.ordinal();
    }



    public boolean getCapitol() {
        return capitol;
    }

    public void setCapitol(boolean capitol) {
        this.capitol = capitol;
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

    //use for GamePreferences
    public int getGameTypeInt() {
        return gameType.ordinal();
    }

    //use for GamePreferences
    public void setGameTypeInt(int gameType) {
        this.gameType = GameType.values()[gameType];
    }

    //use for Game
    public GameType getGameType() {
        return gameType;
    }

    //use for Game
    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

}
