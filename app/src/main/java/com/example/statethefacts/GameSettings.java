package com.example.statethefacts;

import java.util.List;

public class GameSettings {
    Boolean multipleChoice;
    //List<State> states;
    GetFacts gf;



    public GameSettings(Boolean multipleChoice, List<State> states) {
        this.multipleChoice = multipleChoice;
        //this.states = states;
    }

    //Facts facts = gf.Fetch(this);


}
