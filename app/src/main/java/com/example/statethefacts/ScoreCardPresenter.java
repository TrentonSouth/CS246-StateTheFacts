package com.example.statethefacts;

import android.content.Context;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.text.DecimalFormat;
import java.util.List;

/**
 * The ScoreCardPresenter class is the backend for ScoreCardActivity
 * Its purpose is to handle the logic for items passed in by
 * the ScoreCardActivity
 *
 * @author Trenton South
 * @version 1.0
 * @since 12/9/2020
 */
public class ScoreCardPresenter {
    // Declare the private variables to hold values
    // of scores and their string representations
    private float score;
    private String scoreAsString;
    private float average;
    private String averageAsString;

    // Create a variable for ScoreCardActivity that will be
    // set when the class is instantiated.
    ScoreCardActivity activity;

    /**
     * the constructor
     * Purpose: Populates the activity property with
     * the activity that is passed into it from the
     * ScoreCardActivity class (which is itself).
     *
     * @param activity
     */
    public ScoreCardPresenter(ScoreCardActivity activity) {
        this.activity = activity;
    }

    /**
     * the calculateScores Method
     * Purpose: This is the primary method of the class
     * A future enhancement might be to put this in the
     * constructor. It calculates the scores and makes
     * string representations of them.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void calculateScores(Context ctx) {
        // Get the current game results from GameResult
        GameResult gameResult = new GameResult().loadCurrentGame(ctx);
        // getScore will return a float with the score from the game passed in.
        score = getScore(gameResult);
        // Set a format for decimals to round the scores to two places.
        DecimalFormat decimalFormat = new DecimalFormat("##0.0");
        // Apply the formatting to scoreAsString
        scoreAsString = decimalFormat.format(score);
        // If the score is 100, we don't want the decimal places.
        if (score == 100.0) {
            scoreAsString = "100";
        }
        // Get a list of all the game results from the GameResult class
        List<GameResult> listOfGameResults = gameResult.getAllGames(ctx);
        // gCount will keep track of the number of games played.
        int gCount = 0;
        // total will keep track of the total score from all the games.
        float total = 0;
        // Loop through all of the games and increment the gCount and
        // add to the total.
        for (GameResult game : listOfGameResults) {
            gCount = gCount + 1;
            total += getScore(game);
        }
        // average will hold the value of total divided by gCount.
        float average;
        // Make sure we are not going to divide by 0.
        if (gCount > 0) {
            average = total / gCount;
        } else {
            average = 0;
        }
        // Set the string value of average using the decimalFormat again.
        averageAsString = decimalFormat.format(average);
        // If the average is 100, we don't want the extra zeros.
        if (average == 100.0) {
            averageAsString = "100";
        }
    }

    /**
     * the getScoreAsString Method
     * Purpose: Load the game and loop through all of the answers.
     * Find the the score which is the number of questions divided
     * by the number of correct answers.
     *
     * @param gameResult
     * @return score as float
     */
    private float getScore(GameResult gameResult) {
        // questionCount will keep track of the number of questions
        float questionCount = 0;
        // correctAnswers will keep track of the number correct answers.
        float correctAnswers = 0;
        // Loop through all of the game answers.
        for (GameAnswer answer : gameResult.getAnswers()) {
            // For every answer increment the question count because
            // there is one answer for each question.
            questionCount += 1;
            // If the answer is correct, increment correctAnswers by 1
            if (answer.HasCorrectAnswer())
                correctAnswers += 1;
        }
        // score will hold the value of correctAnswers divided by
        // the number of questions.
        float score;
        // Make sure we don't divide by 0
        if (questionCount > 0) {
            score = (correctAnswers / questionCount) * 100;
        } else {
            score = 0;
        }
        return score;
    }

    /**
     * the getScoreAsString Method
     * Purpose: Return the private variable score as string
     *
     * @return scoreAsString as string
     */
    public String getScoreAsString() {
        return scoreAsString;
    }

    /**
     * the getAverageAsString Method
     * Purpose: Return the private variable score as string
     *
     * @return averageAsString as string
     */
    public String getAverageAsString() {
        return averageAsString;
    }
}
