package com.example.statethefacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MissedQuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missed_questions);

        GameResult gr = new GameResult().loadCurrentGame(this);
        List<GameAnswer> gameAnswers = gr.getAnswers();
        String display = "";
        for(GameAnswer ga : gameAnswers) {
            if(!ga.HasCorrectAnswer()) {
                display += ga.getState() + " - " + ga.getQuestionType() + "\n";
                display += "Your Answer: " + ga.getSubmittedAnswer() + "\n";
                display += "Correct Answer: " + ga.getCorrectAnswer() + "\n\n";
            }
        }
        TextView txtMissedQuestions = this.findViewById(R.id.txtMissedQuestions);
        txtMissedQuestions.setText(display);
    }

    public void mainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

} 