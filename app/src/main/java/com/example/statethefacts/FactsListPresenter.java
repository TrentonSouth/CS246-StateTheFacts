package com.example.statethefacts;

import android.content.Context;
import android.widget.TextView;


/**
 * The FactsListPresenter class is the backend for FactsListActivity
 * Its purpose is to handle the logic for items passed in by
 * the FactsListActivity
 *
 * @author Trenton South
 * @version 1.0
 * @since 12/8/2020
 */
public class FactsListPresenter {
    // Declare the private variables that will be used to
    // generate the display based on their values.
    private boolean birdChecked = true;
    private boolean capitalChecked = true;
    private boolean flowerChecked = true;
    private boolean governorChecked = true;
    private boolean rockChecked = true;

    // Create a variable for FactsListActivity that will be
    // set when the class is instantiated.
    FactsListActivity activity;

    /**
     * the constructor
     * Purpose: Populates the activity property with
     * the activity that is passed into it from the
     * FactsListActivity class (which is itself).
     *
     * @param activity
     */

    public FactsListPresenter(FactsListActivity activity) {
        this.activity = activity;
    }


    /**
     * the buildList Method
     * Purpose: The buildList function is used to generate the
     * display of all the selected fact types and then
     * passes that text back.
     */
    public String buildList() {
        // strFacts will be declared and used to hold the display.
        String strFacts = "";
        // getFacts will be the variable that holds the class
        // of the same name which will be used to fetch the facts.
        GetFacts getFacts = new GetFacts();
        // facts is the variable that will be used to hold all
        // of the facts obtained from getFacts.
        Facts facts = getFacts.Fetch(activity);
        // facts has an array called states that holds all
        // of the state information. Loop through each
        // state and update the display if the boolean
        // for each fact is true.
        for (State state : facts.states) {
            strFacts += "     " + state.state + "\n";
            if (birdChecked)
                strFacts += "          Bird: " + state.bird + "\n";
            if (capitalChecked)
                strFacts += "          Capital: " + state.capital + "\n";
            if (flowerChecked)
                strFacts += "          Flower: " + state.flower + "\n";
            if (governorChecked)
                strFacts += "          Governor: " + state.governor + "\n";
            if (rockChecked)
                strFacts += "          Rock: " + state.rock + "\n";
            strFacts += "\n";
        }
        return strFacts;
    }

    /**
     * the setBirdChecked Method
     * Purpose: set the private variable birdChecked
     * to the value that is passed in.
     *
     * @param status
     */
    public void setBirdChecked(boolean status) {
        birdChecked = status;
    }

    /**
     * the setCapitalChecked Method
     * Purpose: set the private variable capitalChecked
     * to the value that is passed in.
     *
     * @param status
     */
    public void setCapitalChecked(boolean status) {
        capitalChecked = status;
    }

    /**
     * the setFlowerChecked Method
     * Purpose: set the private variable flowerChecked
     * to the value that is passed in.
     *
     * @param status
     */
    public void setFlowerChecked(boolean status) {
        flowerChecked = status;
    }

    /**
     * the setGovernorChecked Method
     * Purpose: set the private variable governorChecked
     * to the value that is passed in.
     *
     * @param status
     */
    public void setGovernorChecked(boolean status) {
        governorChecked = status;
    }

    /**
     * the setRockChecked Method
     * Purpose: set the private variable rockChecked
     * to the value that is passed in.
     *
     * @param status
     */
    public void setRockChecked(boolean status) {
        rockChecked = status;
    }
}
