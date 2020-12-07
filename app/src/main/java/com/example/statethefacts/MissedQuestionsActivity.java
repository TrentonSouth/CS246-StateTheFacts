package com.example.statethefacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MissedQuestionsActivity extends AppCompatActivity {
    private MissedQuestionsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missed_questions);
        presenter = new MissedQuestionsPresenter(this);

        TextView txtMissedQuestions = this.findViewById(R.id.txtMissedQuestions);
        txtMissedQuestions.setText(presenter.getDisplay(this));
    }

    public void mainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

} 