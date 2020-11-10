package com.example.statethefacts;

import java.util.List;

public class GameSettings {
    Boolean multipleChoice;
    List<State> states;

    public GameSettings(Boolean multipleChoice, List<State> states) {
        this.multipleChoice = multipleChoice;
        this.states = states;
    }




}
