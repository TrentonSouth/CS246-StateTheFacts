package com.example.statethefacts;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadFacts facts = new LoadFacts(this);
        Thread thread = new Thread(facts);
        thread.start();
    }

    public void showChartsExample(View view) {
        Intent intent = new Intent(this, ChartsExampleActivity.class);
        startActivity(intent);
    }

    public void startGame(View view){
        Intent intent = new Intent(this, GameQuestionActivity.class);
        intent.putExtra("gameType", GameType.MultipleChoice);
        startActivity(intent);
    }

    public void getFacts(View view) {
        GetFacts gf = new GetFacts();
        Facts facts = gf.Fetch(this);
        Log.d("Message:", "Value is: " + facts.states.get(0).abbreviation);
    }
}