package com.example.statethefacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetFacts facts = new GetFacts(this);
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

}