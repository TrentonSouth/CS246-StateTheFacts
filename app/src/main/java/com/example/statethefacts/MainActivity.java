package com.example.statethefacts;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetFacts facts = new GetFacts(this);
        Thread thread = new Thread(facts);
        thread.start();
    }

}