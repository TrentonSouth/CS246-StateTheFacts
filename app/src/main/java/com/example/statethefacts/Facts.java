package com.example.statethefacts;

import java.util.ArrayList;
import java.util.List;

/**
 * The Facts class holds an array of State objects
 * It is designed to be populated by the json that is retrieved
 * from firebase using gson.
 *
 * @author Trenton South
 * @version 1.0
 * @since 12/8/2020
 */
public class Facts {
    ArrayList<State> states;

    /**
     * the constructor
     * Purpose: Populate the states attribute with the value
     * passed in states. This has to be here for gson to work.
     *
     * @param states
     */
    public Facts(ArrayList<State> states) {
        this.states = states;
    }

    /**
     * Facts constructor
     * Purpose: Populates the activity property with
     * the activity that is passed into it from the
     * ScoreCardActivity class (which is itself).
     *
     * @return states as a list of State
     */
    public List<State> getStates() {
        return states;
    }
}
