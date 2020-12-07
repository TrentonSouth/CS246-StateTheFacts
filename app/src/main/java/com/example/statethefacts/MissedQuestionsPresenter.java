package com.example.statethefacts;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

public class MissedQuestionsPresenter {
    MissedQuestionsActivity activity;

    public MissedQuestionsPresenter(MissedQuestionsActivity activity){
        this.activity = activity;

    }

    public String getDisplay(Context ctx) {
        GameResult gr = new GameResult().loadCurrentGame(ctx);
        List<GameAnswer> gameAnswers = gr.getAnswers();
        String display = "";
        for(GameAnswer ga : gameAnswers) {
            if(!ga.HasCorrectAnswer()) {
                display += ga.getState() + " - " + ga.getQuestionType() + "\n";
                display += "Your Answer: " + ga.getSubmittedAnswer() + "\n";
                display += "Correct Answer: " + ga.getCorrectAnswer() + "\n\n";
            }
        }
        return display;
    }
}