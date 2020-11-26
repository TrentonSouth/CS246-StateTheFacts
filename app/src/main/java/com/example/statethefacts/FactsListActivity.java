package com.example.statethefacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;

public class FactsListActivity extends AppCompatActivity {
    private boolean birdChecked = true;
    private boolean capitalChecked = true;
    private boolean flowerChecked = true;
    private boolean governorChecked = true;
    private boolean rockChecked = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts_list);
        buildList();
    }

    public void birdChange(View view) {
        birdChecked = ((CheckBox) view).isChecked();
        buildList();
    }

    public void capitalChange(View view) {
        capitalChecked = ((CheckBox) view).isChecked();
        buildList();
    }

    public void flowerChange(View view) {
        flowerChecked = ((CheckBox) view).isChecked();
        buildList();
    }

    public void governorChange(View view) {
        governorChecked = ((CheckBox) view).isChecked();
        buildList();
    }

    public void rockChange(View view) {
        rockChecked = ((CheckBox) view).isChecked();
        buildList();
    }

    public void mainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void buildList() {


        TextView txtFacts = (TextView) findViewById(R.id.txtFacts);
        String strFacts = "";
        GetFacts gf = new GetFacts();
        Facts facts = gf.Fetch(this);
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
        txtFacts.setText(strFacts);
    }
}

