package com.example.statethefacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;

public class FactsListActivity extends AppCompatActivity {
    private FactsListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts_list);
        presenter = new FactsListPresenter(this);
        buildList();
    }

    public void birdChange(View view) {
        presenter.setBirdChecked(((CheckBox) view).isChecked());
        buildList();
    }

    public void capitalChange(View view) {
        presenter.setCapitalChecked(((CheckBox) view).isChecked());
        buildList();
    }

    public void flowerChange(View view) {
        presenter.setFlowerChecked(((CheckBox) view).isChecked());
        buildList();
    }

    public void governorChange(View view) {
        presenter.setGovernorChecked(((CheckBox) view).isChecked());
        buildList();
    }

    public void rockChange(View view) {
        presenter.setRockChecked(((CheckBox) view).isChecked());
        buildList();
    }

    public void mainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void buildList() {
        TextView txtFacts = (TextView) findViewById(R.id.txtFacts);
        String strFacts = presenter.buildList();
        txtFacts.setText(strFacts);
    }
}

