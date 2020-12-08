package com.example.statethefacts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class HistoryActivity extends AppCompatActivity {

    private HistoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        presenter = new HistoryPresenter(this);
        presenter.loadGameResult();
        presenter.getStats();
    }

}