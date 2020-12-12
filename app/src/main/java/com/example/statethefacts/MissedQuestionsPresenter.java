package com.example.statethefacts;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

/**
 * The MissedQuestionsPresenter class is the backend for MissedQuestionsActivity
 * Its purpose is to provide functionality for the MissedQuestionsActivity
 *
 * @author Trenton South
 * @version 1.0
 * @since 12/8/2020
 */
public class MissedQuestionsPresenter {
    MissedQuestionsActivity activity;

    public MissedQuestionsPresenter(MissedQuestionsActivity activity) {
        this.activity = activity;
    }

    /**
     * getDisplay function
     * Purpose: Builds the display for all of the missed questions
     * in the last quiz taken.
     *
     * @param ctx
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getDisplay(Context ctx) {
        // Set a variable gameResult from the class with the same name
        // and immediately call the function loadCurrentGame which
        // itself is an instance of the GameResult class.
        GameResult gameResult = new GameResult().loadCurrentGame(ctx);
        // Create a variable gameAnswers and set it to a list of
        // all of the answers from the gameResult.
        List<GameAnswer> gameAnswers = gameResult.getAnswers();
        // Set the variable disply to hold the concatenation of all
        // the display parts.
        String display = "";
        // Loop through all of the answers and if the answer is
        // incorrect, add to the display the user's answer and
        // the correct answer.
        for (GameAnswer gameAnswer : gameAnswers) {
            if (!gameAnswer.hasCorrectAnswer()) {
                display += gameAnswer.getState() + " - " + gameAnswer.getQuestionType() + "\n";
                display += "Your Answer: " + gameAnswer.getSubmittedAnswer() + "\n";
                display += "Correct Answer: " + gameAnswer.getCorrectAnswer() + "\n\n";
            }
        }
        return display;
    }
}