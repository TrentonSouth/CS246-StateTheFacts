package com.example.statethefacts;

import java.util.ArrayList;
import java.util.List;

public class Facts {
    ArrayList<State> states;

    public Facts(ArrayList<State> states) {
        this.states = states;
    }

    public List<State> getStates() {return states;}
}
