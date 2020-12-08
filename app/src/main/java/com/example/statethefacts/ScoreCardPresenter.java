package com.example.statethefacts;

import android.content.Context;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.text.DecimalFormat;
import java.util.List;

public class ScoreCardPresenter {
    private float score;
    private String scoreAsString;
    private float average;
    private String averageAsString;

    ScoreCardActivity activity;
    public ScoreCardPresenter(ScoreCardActivity activity) {
        this.activity = activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void calculateScores(Context ctx) {
        // Get the current game results
        GameResult gameResult = new GameResult().loadCurrentGame(ctx);
        score = getScore(gameResult);

        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        scoreAsString = decimalFormat.format(score);

        if (score == 100.0) {
            scoreAsString = "100";
        }

        List<GameResult> listOfGameResults = gameResult.getAllGames(ctx);
        int gCount = 0;
        float total = 0;
        for (GameResult game : listOfGameResults) {
            gCount = gCount + 1;
            total += getScore(game);
        }
        float average;
        if (gCount > 0) {
            average = total / gCount;
        } else {
            average = 0;
        }
        averageAsString = decimalFormat.format(average);
        if (average == 100.0) {
            averageAsString = "100";
        }
    }

    private float getScore(GameResult gr) {
        float qCount = 0;
        float qRight = 0;
        for (GameAnswer a : gr.getAnswers())
        {
            qCount += 1;
            if(a.HasCorrectAnswer())
                qRight += 1;
        }
        float score;
        if (qCount > 0) {
            score = (qRight / qCount) * 100;
        } else {
            score = 0;
        }
        return score;
    }

    public String getScoreAsString() {
        return scoreAsString;
    }
    public String getAverageAsString() {
        return averageAsString;
    }
}
