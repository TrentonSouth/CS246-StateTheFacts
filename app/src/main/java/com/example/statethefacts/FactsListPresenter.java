package com.example.statethefacts;

import android.content.Context;
import android.widget.TextView;

public class FactsListPresenter {

    private boolean birdChecked = true;
    private boolean capitalChecked = true;
    private boolean flowerChecked = true;
    private boolean governorChecked = true;
    private boolean rockChecked = true;


    FactsListActivity activity;

    public FactsListPresenter(FactsListActivity activity){
        this.activity = activity;

    }

    public String buildList() {


        TextView txtFacts = (TextView) activity.findViewById(R.id.txtFacts);
        String strFacts = "";
        GetFacts gf = new GetFacts();
        Facts facts = gf.Fetch(activity);
        for (State state : facts.states)
        {
            strFacts += "     " + state.state + "\n";
            if(birdChecked)
                strFacts += "          Bird: " + state.bird + "\n";
            if(capitalChecked)
                strFacts += "          Capital: " + state.capital + "\n";
            if(flowerChecked)
                strFacts += "          Flower: " + state.flower + "\n";
            if(governorChecked)
                strFacts += "          Governor: " + state.governor + "\n";
            if(rockChecked)
                strFacts += "          Rock: " + state.rock + "\n";
            strFacts += "\n";

        }
        return strFacts;
    }

    public void setBirdChecked(boolean status) {
        birdChecked = status;
    }
    public void setCapitalChecked(boolean status) {
        capitalChecked = status;
    }
    public void setFlowerChecked(boolean status) {
        flowerChecked = status;
    }
    public void setGovernorChecked(boolean status) {
        governorChecked = status;
    }
    public void setRockChecked(boolean status) {
        rockChecked = status;
    }
}
