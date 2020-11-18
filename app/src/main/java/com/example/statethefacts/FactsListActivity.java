package com.example.statethefacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class FactsListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts_list);
        TextView txtFacts = (TextView) findViewById(R.id.txtFacts);
        String strFacts = "";
        GetFacts gf = new GetFacts();
        Facts facts = gf.Fetch(this);
        for (State state : facts.states)
        {
            strFacts += "     " + state.state + "\n";
            strFacts += "          Bird: " + state.bird + "\n";
            strFacts += "          Capital: " + state.capital + "\n";
            strFacts += "          Flower: " + state.flower + "\n";
            strFacts += "          Governor: " + state.governor + "\n";
            strFacts += "          Rock: " + state.rock + "\n\n";

        }
        txtFacts.setText(strFacts);
    }
}

